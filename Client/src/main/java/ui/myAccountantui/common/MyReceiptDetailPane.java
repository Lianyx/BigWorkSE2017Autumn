package ui.myAccountantui.common;

import blService.checkblService.CheckInfo;
import blService.checkblService.ReceiptblService;
import businesslogic.checkbl.MyServiceFactory;
import businesslogic.promotionbl.MyblServiceFactory;
import com.jfoenix.controls.JFXButton;
import exceptions.ItemNotFoundException;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import ui.managerui.common.ExceptionRunnable;
import ui.managerui.common.MyBoardController;
import ui.managerui.common.MyOneButtonDialog;
import ui.managerui.common.MyTwoButtonDialog;
import ui.util.GetTask;
import ui.util.Refreshable;
import ui.util.UserInfomation;
import util.ReceiptState;
import util.UserCategory;
import vo.receiptVO.ReceiptVO;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public abstract class MyReceiptDetailPane<TV extends ReceiptVO> extends Refreshable {
    @FXML
    protected JFXButton modify, reset, save, saveAsDraft, delete, approve, reject;
    @FXML
    protected Label idLabel;


    protected ReceiptblService<TV> receiptblService;
    protected CheckInfo<TV> checkInfo; // TODO 最好提子类
    protected TV receiptVO;
    protected BooleanProperty modifyState = new SimpleBooleanProperty(true);

    /**
     * Constructors related
     */
    public MyReceiptDetailPane() {
        initiate();
    }

    public MyReceiptDetailPane(TV receiptVO) {
        this.receiptVO = receiptVO;
        initiate();
    }

    protected void initiate() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(getURL()));
            fxmlLoader.setRoot(this);
            fxmlLoader.setController(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        approve.setVisible(false);
        reject.setVisible(false);

        if (receiptVO != null) {
            if (receiptVO.getReceiptState() != ReceiptState.DRAFT) {
                saveAsDraft.setVisible(false);
                reset.setLayoutX(saveAsDraft.getLayoutX());
            }

            // 如果不是总经理提交或者就只能看
            if (receiptVO.getReceiptState() == ReceiptState.APPROVED
                    || UserInfomation.usertype != UserCategory.GeneralManager && receiptVO.getReceiptState() == ReceiptState.PENDING) {
                modify.setVisible(false); // 这句还是个问题
                reset.setVisible(false);
                save.setVisible(false);
                saveAsDraft.setVisible(false);
                delete.setVisible(false);
            }

            if (UserInfomation.usertype == UserCategory.GeneralManager && receiptVO.getReceiptState() == ReceiptState.PENDING) { // 如果是总经理肯定只能是pending的
                reset.setVisible(false);
                save.setVisible(false);
                saveAsDraft.setVisible(false);
                delete.setVisible(false);

                // modify, reset, save, saveAsDraft, delete;

                approve.setVisible(true);
                reject.setVisible(true);
            }
        }
    }

    /**
     * abstract methods
     */
    protected abstract String getURL();

    protected abstract Class<? extends ReceiptblService<TV>> getServiceClass();


    /**
     * to be overriden
     */

    protected boolean validate() {
        return true;
    }

    protected void updateReceiptVO() {
    }

    @FXML
    protected void reset() {
        idLabel.setText(receiptVO.getId());
    }

    /**
     * FXML
     */

    @FXML
    private void save() { // 这里的save相当于提交，但是不想改名了…
        if (validate()) {
            receiptVO.setReceiptState(ReceiptState.PENDING);
            saveTask();
        } else {
            new MyOneButtonDialog("不合法数据").show();
        }
    }

    @FXML
    private void saveAsDraft() {
        if (validate()) {
            saveTask();
        } else {
            new MyOneButtonDialog("不合法数据").show();
        }
    }

    private void saveTask() {
        MyBoardController boardController = MyBoardController.getMyBoardController();
        boardController.Loading();

        updateReceiptVO();

        StringProperty prompt = new SimpleStringProperty();
        new Thread(new GetTask(() -> {
            new MyTwoButtonDialog("保存成功", boardController::goBack).show();
        }, new MyTwoButtonDialog(prompt.get(), boardController::goBack), woid -> {
            try {
                receiptblService.update(receiptVO);
                return true;
            } catch (ItemNotFoundException e) {
                e.printStackTrace();
                prompt.set("策略不存在，是否返回单据列表");
                return false;
            } catch (RemoteException e) {
                e.printStackTrace();
                prompt.set("连接错误");
                return false;
            }
        })).start();
    }

    @FXML
    private void delete() {
        new MyTwoButtonDialog("请确认删除", () -> {
            MyBoardController boardController = MyBoardController.getMyBoardController();
            boardController.Loading();

            StringProperty prompt = new SimpleStringProperty(); // 为了避免lambda的final限制。
            new Thread(new GetTask(() -> {
                new MyOneButtonDialog("删除成功", boardController::goBack).show();
            }, new MyTwoButtonDialog(prompt.get(), boardController::goBack), woid -> {
                try {
                    receiptblService.delete(receiptVO);
                    return true;
                } catch (ItemNotFoundException e) {
                    e.printStackTrace();
                    prompt.set("单据不存在，是否返回单据列表");
                    return false;
                } catch (RemoteException e) {
                    e.printStackTrace();
                    prompt.set("连接错误");
                    return false;
                }
            })).start();
        }).show();
    }

    @FXML
    private void approve() { // approve的时候顺便update单据
        if (validate()) {
            System.out.println("MyReceiptDetailPane approve called");
            receiptVO.setReceiptState(ReceiptState.APPROVED); // TODO 这个其实不应该在这里set的

            MyBoardController myBoardController = MyBoardController.getMyBoardController();
            myBoardController.Loading();

            updateReceiptVO();

            MyTwoButtonDialog dialog = new MyTwoButtonDialog("连接错误", () -> refresh(false), myBoardController::Ret);

            new Thread(new GetTask(() -> {
                new MyOneButtonDialog("审批通过", myBoardController::goBack).show();
            }, dialog, woid -> {
                try {
                    checkInfo.approve(receiptVO);
                    return true;
                } catch (RemoteException e) {
                    e.printStackTrace();
                    return false;
                }
            })).start();
        } else {
            new MyOneButtonDialog("不合法数据").show();
        }
    }

    @FXML
    private void reject() { // reject就不需要update数据
        if (validate()) {
            receiptVO.setReceiptState(ReceiptState.REJECTED);

            MyBoardController myBoardController = MyBoardController.getMyBoardController();
            myBoardController.Loading();

            MyTwoButtonDialog dialog = new MyTwoButtonDialog("连接错误", () -> refresh(false), myBoardController::Ret);

            new Thread(new GetTask(() -> {
                new MyOneButtonDialog("驳回单据", myBoardController::goBack).show();
            }, dialog, woid -> {
                try {
                    checkInfo.reject(receiptVO);
                    System.out.println(" reject success");
                    return true;
                } catch (RemoteException e) {
                    e.printStackTrace();
                    return false;
                }
            })).start();
        } else {
            new MyOneButtonDialog("不合法数据").show();
        }
    }

    @FXML
    private void modify() {
        modifyState.set(!modifyState.get());
    }

    /**
     * refresh
     */
    @Override
    public void refresh(boolean toSwitch) {
        MyBoardController myBoardController = MyBoardController.getMyBoardController();
        myBoardController.Loading();

        MyTwoButtonDialog dialog = new MyTwoButtonDialog("连接错误", () -> refresh(false), myBoardController::Ret);

        GetTask task = new GetTask(() -> {
            myBoardController.switchTo(this); // 感觉这个分两次来明显就是为了可以有等待…
            reset();
        }, dialog, woid -> {
            try {
                if (receiptblService == null) { // 这说明肯定是第一次
                    receiptblService = MyblServiceFactory.getService(getServiceClass());
                    if (receiptVO == null) {
                        receiptVO = receiptblService.getNew();
                    }
                    if (checkInfo == null) {
                        checkInfo = receiptVO.getService();
                    }
                } else {
                    receiptVO = receiptblService.selectByMold(receiptVO);
                }
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        });
        new Thread(task).start();
    }
}
