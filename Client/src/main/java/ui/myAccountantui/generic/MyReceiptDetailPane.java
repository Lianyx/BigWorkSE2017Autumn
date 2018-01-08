package ui.myAccountantui.generic;

import blService.checkblService.ReceiptblService;
import businesslogic.promotionbl.PromotionFactory;
import com.jfoenix.controls.JFXButton;
import exceptions.ItemNotFoundException;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import ui.managerui.common.MyBoardController;
import ui.managerui.common.MyOneButtonDialog;
import ui.managerui.common.MyTwoButtonDialog;
import ui.util.GetTask;
import ui.util.Refreshable;
import util.ReceiptState;
import vo.receiptVO.ReceiptVO;

import java.io.IOException;
import java.rmi.RemoteException;

public abstract class MyReceiptDetailPane<TV extends ReceiptVO> extends Refreshable {
    @FXML
    protected JFXButton modify, reset, save, saveAsDraft;
    @FXML
    protected Label idLabel;


    protected ReceiptblService<TV> receiptblService;
    protected TV receiptVO;
    protected BooleanProperty modifyState = new SimpleBooleanProperty(true);

    /**
     * Constructors related
     */
    public MyReceiptDetailPane() {
        initialize();
    }

    public MyReceiptDetailPane(TV receiptVO) {
        this.receiptVO = receiptVO;
        initialize();

        if (receiptVO.getReceiptState() != ReceiptState.DRAFT) {
//            saveAsDraft.setVisible(false);
//            reset.setLayoutX(saveAsDraft.getLayoutX());
        }

        if (receiptVO.getReceiptState() == ReceiptState.PENDING || receiptVO.getReceiptState() == ReceiptState.APPROVED) {
//            modify.setVisible(false); // 这句还是个问题
//            reset.setVisible(false);
//            save.setVisible(false);
//            saveAsDraft.setVisible(false);
        }
    }

    protected void initialize() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(getURL()));
            fxmlLoader.setRoot(this);
            fxmlLoader.setController(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }


        save.visibleProperty().bind(modifyState);
        reset.visibleProperty().bind(modifyState);
    }

    /**
     * abstract methods
     */
    protected abstract String getURL();
    protected abstract Class<? extends ReceiptblService<TV>> getServiceClass();


    /**
     * to be overriden
     * */

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
     * */

    @FXML
    private void save() { // 这里的save相当于提交，但是不想改名了…
        if (validate()) {
            receiptVO.setReceiptState(ReceiptState.PENDING);
            saveTask();
        }
    }

    @FXML
    private void saveAsDraft() {
        if (validate()) {
            saveTask();
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
    private void modify() {
        modifyState.set(!modifyState.get());
    }

    /**
     * refresh
     * */
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
                    receiptblService = PromotionFactory.getService(getServiceClass());
                    if (receiptVO == null) {
                        receiptVO = receiptblService.getNew();
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
