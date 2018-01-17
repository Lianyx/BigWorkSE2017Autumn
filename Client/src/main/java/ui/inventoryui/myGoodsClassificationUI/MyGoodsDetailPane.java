package ui.inventoryui.myGoodsClassificationUI;

import blService.goodsblService.GoodsblService;
import businesslogic.blServiceFactory.MyblServiceFactory;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import exceptions.ItemNotFoundException;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import ui.common.BoardController;
import ui.common.bigPane.GatePane;
import ui.common.dialog.MyOneButtonDialog;
import ui.common.dialog.MyTwoButtonDialog;
import ui.util.GetTask;
import ui.util.ModifyButton;
import vo.inventoryVO.GoodsVO;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class MyGoodsDetailPane extends GatePane {
    @FXML
    private JFXTextField goodName;
    @FXML
    private TextField goodTextId;
    @FXML
    private TextField goodType;
    @FXML
    private TextField inventoryNum;
    @FXML
    private TextField classifyTextId;
    @FXML
    private TextField alarmNum;
    @FXML
    private TextField purPrice;
    @FXML
    private TextField salePrice;
    @FXML
    private TextField recentPurPrice;
    @FXML
    private TextField recentSalePrice;

    @FXML
    private JFXButton save;
    @FXML
    private JFXButton reject;
    @FXML
    private JFXButton delete;
    @FXML
    private JFXButton reset;

    @FXML
    protected ModifyButton modify;

    @FXML
    private Label date;

    private GoodsblService goodsblService;
    private GoodsVO vo;

    private boolean isAdd;

    public MyGoodsDetailPane(GoodsVO goodsVO) {
        this.vo = goodsVO;
        isAdd = false;

        initiate();
    }

    public MyGoodsDetailPane(String fatherId) {
        this.vo = new GoodsVO();
        vo.setClassifyId(fatherId);
        isAdd = true;

        initiate();
    }

    private void initiate() {
        reset.visibleProperty().bind(modify.modifyProperty());
        save.visibleProperty().bind(modify.modifyProperty());
    }

    @Override
    protected void refreshAfterMath() {

    }

    @Override
    protected void initiateService() throws RemoteException, NotBoundException, MalformedURLException {
        goodsblService = MyblServiceFactory.getService(GoodsblService.class);
    }

    @Override
    protected void updateDataFromBl() throws RemoteException {
        if (!isAdd) {
            vo = goodsblService.showDetail(vo.getId());
        }
    }

    @Override
    protected String getURL() {
        return "/inventoryui/goodui/myGoodsDetailPane.fxml";
    }

    /**
     * FXML
     */

    @FXML
    private void reset() {
        // TODO 汉乙写的reset不懂什么意思
        goodName.setText(vo.getGoodName());
        goodTextId.setText(vo.getId());
        classifyTextId.setText(vo.getClassifyId());
        goodType.setText(vo.getGoodType());
        inventoryNum.setText(String.valueOf(vo.getInventoryNum()));
        salePrice.setText(String.valueOf(vo.getSalePrice()));
        purPrice.setText(String.valueOf(vo.getPurPrice()));
        recentPurPrice.setText(String.valueOf(vo.getRecentPurPrice()));
        recentSalePrice.setText(String.valueOf(vo.getRecentSalePrice()));
        alarmNum.setText(String.valueOf(vo.getAlarmNumber()));
    }

    @FXML
    private void delete() {
        new MyTwoButtonDialog("请确认删除", () -> {
            BoardController boardController = BoardController.getBoardController();
            boardController.Loading();

            StringProperty prompt = new SimpleStringProperty(); // 为了避免lambda的final限制。
            new Thread(new GetTask(() -> {
                new MyOneButtonDialog("删除成功", boardController::goBack).show();
            }, new MyTwoButtonDialog(prompt.get(), boardController::goBack), woid -> {
                try {
                    goodsblService.deleteGoods(vo);
                    // TODO 这个里面写的感觉有问题。为什么不能直接删，还要设成空string，留后患。
                    return true;
                } catch (ItemNotFoundException e) {
                    e.printStackTrace();
                    prompt.set("商品不存在，是否返回列表");
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
    private void save() {
        if (validate()) {
            updateVO();

            BoardController boardController = BoardController.getBoardController();
            boardController.Loading();

            StringProperty prompt = new SimpleStringProperty(); // 为了避免lambda的final限制。
            new Thread(new GetTask(() -> {
                new MyTwoButtonDialog("保存成功", boardController::goBack).show(); // 这个和delete不一样
            }, new MyTwoButtonDialog(prompt.get(), boardController::goBack), woid -> {
                try {
                    if (isAdd) {
                        goodsblService.addGoods(vo);
                    } else {
                        goodsblService.updateGoods(vo);
                    }
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
        } else {
            // TODO show alert box
        }
    }


    /**
     * private
     * */
    private boolean validate() {
        return true;
        // TODO
    }

    private void updateVO() {
        vo.setGoodName(goodName.getText());
        vo.setGoodType(goodType.getText());
//        vo.setClassifyId(); // 这个不需要set
        vo.setInventoryNum(Integer.parseInt(inventoryNum.getText()));
        vo.setPurPrice(Double.parseDouble(purPrice.getText()));
        vo.setSalePrice(Double.parseDouble(salePrice.getText()));
//        vo.setRecentPurPrice(Double.parseDouble(recentPurPrice.getText())); // 这个感觉不是自己set的
//        vo.setRecentSalePrice(Double.parseDouble(recentSalePrice.getText())); // 这个同上
        vo.setAlarmNumber(Integer.parseInt(alarmNum.getText()));
    }
}
