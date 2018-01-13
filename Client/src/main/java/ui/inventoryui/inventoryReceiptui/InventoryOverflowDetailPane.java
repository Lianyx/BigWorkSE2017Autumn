package ui.inventoryui.inventoryReceiptui;

import blService.checkblService.ReceiptblService;
import blService.inventoryblService.InventoryOverflowReceiptblService;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import ui.inventoryui.goodsui.GoodChoose;
import ui.common.MyReceiptDetailPane;
import vo.inventoryVO.inventoryReceiptVO.InventoryOverflowReceiptVO;
import vo.inventoryVO.inventoryReceiptVO.ReceiptGoodsItemVO;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;

public class InventoryOverflowDetailPane extends MyReceiptDetailPane<InventoryOverflowReceiptVO> {
    @FXML
    InventoryOverflowDamageTreeTable overflowItemTreeTable;

    ObservableList<ReceiptGoodsItemVO> observableList = FXCollections.observableArrayList();

    @FXML
    private TextArea commentArea;
    @FXML
    private TextField operator;
    @FXML
    private TextField stateField;

    public InventoryOverflowDetailPane() {
        overflowItemTreeTable.setEditable(true);
    }

    public InventoryOverflowDetailPane(InventoryOverflowReceiptVO receiptVO) {
        super(receiptVO);
        stateField.setDisable(true);
        overflowItemTreeTable.setList(receiptVO.getItems());
        overflowItemTreeTable.setEditable(true);
    }

    @Override
    protected String getURL() {
        return "/inventoryui/inventoryreceiptui/inventoryOverflowDetailPane.fxml";
    }

    @Override
    protected Class<? extends ReceiptblService<InventoryOverflowReceiptVO>> getServiceClass() {
        return InventoryOverflowReceiptblService.class;
    }

    @Override
    protected boolean validate() {
        return super.validate();
    }

    @Override
    protected void updateReceiptVO() { // 这里不需要再检查了
        List<ReceiptGoodsItemVO> goodsList = overflowItemTreeTable.getList();
      //  System.out.println("OperatorId:"+operator.getText());
        receiptVO.setOperatorId(Integer.parseInt(operator.getText()));
        receiptVO.setComment(commentArea.getText());
        receiptVO.setItems(goodsList);

    }

    @Override
    protected void setRedCredit(InventoryOverflowReceiptVO redCreditVO) {
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

        overflowItemTreeTable.setList(receiptVO.getItems());
    }

    @FXML
    public void addGoods() throws RemoteException, NotBoundException, MalformedURLException {
        GoodChoose goodsChooseInfo = new GoodChoose();

        goodsChooseInfo.choose(observableList,new SimpleIntegerProperty());
    }
}
