package ui.inventoryui.inventoryReceiptui;

import blService.genericblService.ReceiptblService;
import blService.inventoryblService.InventoryDamageReceiptblService;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import ui.inventoryui.goodsui.GoodChoose;
import ui.common.bigPane.MyReceiptDetailPane;
import vo.inventoryVO.inventoryReceiptVO.InventoryDamageReceiptVO;
import vo.inventoryVO.inventoryReceiptVO.ReceiptGoodsItemVO;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;

public class InventoryDamageDetailPane extends MyReceiptDetailPane<InventoryDamageReceiptVO> {
    @FXML
    InventoryOverflowDamageTreeTable damageItemTreeTable;

    ObservableList<ReceiptGoodsItemVO> observableList = FXCollections.observableArrayList();

    @FXML
    private TextArea commentArea;
    @FXML
    private TextField operator;
    @FXML
    private TextField stateField;


    public InventoryDamageDetailPane() {
        damageItemTreeTable.setEditable(true);

    }

    public InventoryDamageDetailPane(InventoryDamageReceiptVO receiptVO) {
        super(receiptVO);
        stateField.setDisable(true);
        damageItemTreeTable.setList(receiptVO.getItems());
        damageItemTreeTable.setEditable(true);
    }

    @Override
    protected String getURL() {
        return "/inventoryui/inventoryreceiptui/inventoryDamageDetailPane.fxml";
    }

    @Override
    protected Class<? extends ReceiptblService<InventoryDamageReceiptVO>> getServiceClass() {
        return InventoryDamageReceiptblService.class;
    }

    @Override
    protected boolean validate() {
        return super.validate();
    }

    @Override
    protected void updateReceiptVO() { // 这里不需要再检查了
        List<ReceiptGoodsItemVO> goodsList = damageItemTreeTable.getList();
        System.out.println("OperatorId:"+operator.getText());
        receiptVO.setOperatorId(Integer.parseInt(operator.getText()));
        receiptVO.setComment(commentArea.getText());
        receiptVO.setItems(goodsList);
        for (ReceiptGoodsItemVO vo:goodsList) {
            System.out.println("GoodName:"+ vo.getGoodsName()+" "+vo.getSendNum());
        }
        System.out.println("goodsLidt:"+goodsList.toString());

        // TODO 我没管transferList相关的（下面的reset同理）。而且这个vo里面没有comment………
    }

    @Override
    protected void setRedCredit(InventoryDamageReceiptVO redCreditVO) {
        List<ReceiptGoodsItemVO> list = redCreditVO.getItems();

        int index = 0;
        for (ReceiptGoodsItemVO vo:list) {
            vo.setInventoryNum(vo.getInventoryNum()*(-1));
            vo.setFactNum(vo.getFactNum()*(-1));
            list.set(index++,vo);
        }
        redCreditVO.setItems(list);
    }

    @FXML
    @Override
    protected void reset() {
        super.reset();
        operator.setText(String.valueOf(receiptVO.getOperatorId()));
        commentArea.setText(receiptVO.getComment());
        stateField.setText(receiptVO.getReceiptState().toString());

        damageItemTreeTable.setList(receiptVO.getItems());
    }

    @FXML
    public void addGoods() throws RemoteException, NotBoundException, MalformedURLException {
        GoodChoose goodsChooseInfo = new GoodChoose();

        goodsChooseInfo.choose(observableList,new SimpleIntegerProperty());
    }
}
