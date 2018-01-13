package ui.inventoryui.inventoryReceiptui;

import blService.checkblService.ReceiptblService;
import blService.inventoryblService.InventoryGiftReceiptblService;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import ui.inventoryui.GoodsChooseInfo;
import ui.inventoryui.goodsui.GoodChoose;
import ui.myAccountantui.common.MyReceiptDetailPane;
import vo.inventoryVO.inventoryReceiptVO.InventoryDamageReceiptVO;
import vo.inventoryVO.inventoryReceiptVO.InventoryGiftReceiptVO;
import vo.inventoryVO.inventoryReceiptVO.ReceiptGoodsItemVO;
import vo.receiptVO.ReceiptVO;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class InventoryGiftDetailPane extends MyReceiptDetailPane<InventoryGiftReceiptVO> {
    @FXML
    private InventoryListItemTreeTable giftItemTreeTable;

    private ObservableList<ReceiptGoodsItemVO> observableList = FXCollections.observableArrayList();

    @FXML
    private TextArea commentArea;
    @FXML
    private TextField operator;
    @FXML
    private TextField stateField;

    public InventoryGiftDetailPane() {
        giftItemTreeTable.setEditable(true);
    }

    public InventoryGiftDetailPane(InventoryGiftReceiptVO receiptVO) {
        super(receiptVO);
        stateField.setDisable(true);
        giftItemTreeTable.setList(receiptVO.getItems());
        giftItemTreeTable.setEditable(true);
    }

    @Override
    protected String getURL() {
        return "/inventoryui/inventoryreceiptui/inventoryGiftDetailPane.fxml";
    }

    @Override
    protected Class<? extends ReceiptblService<InventoryGiftReceiptVO>> getServiceClass() {
        return InventoryGiftReceiptblService.class;
    }

    @Override
    protected boolean validate() {
        return super.validate();
    }

    @Override
    protected void updateReceiptVO() { // 这里不需要再检查了
        List<ReceiptGoodsItemVO> goodsList = giftItemTreeTable.getList();
        receiptVO.setOperatorId(Integer.parseInt(operator.getText()));
        receiptVO.setComment(commentArea.getText());
        receiptVO.setItems(goodsList);
        giftItemTreeTable.setList(goodsList);
        // TODO 我没管transferList相关的（下面的reset同理）。而且这个vo里面没有comment………
    }

    @Override
    protected void setRedCredit(InventoryGiftReceiptVO redCreditVO) {
        List<ReceiptGoodsItemVO> list = redCreditVO.getItems();

        int index = 0;
        for (ReceiptGoodsItemVO vo:list) {
            vo.setInventoryNum(vo.getInventoryNum()*(-1));
            vo.setSendNum(vo.getSendNum()*(-1));
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

        giftItemTreeTable.setList(receiptVO.getItems());
    }

    @FXML
    public void addGoods() throws RemoteException, NotBoundException, MalformedURLException {
        GoodChoose goodsChooseInfo = new GoodChoose();

        goodsChooseInfo.choose(observableList,new SimpleIntegerProperty());
    }
}
