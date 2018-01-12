package ui.inventoryui.inventoryReceiptui;

import blService.checkblService.ReceiptblService;
import blService.inventoryblService.InventoryDamageReceiptblService;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import ui.myAccountantui.common.MyReceiptDetailPane;
import vo.inventoryVO.inventoryReceiptVO.InventoryDamageReceiptVO;
import vo.inventoryVO.inventoryReceiptVO.InventoryDamageReceiptVO;
import vo.inventoryVO.inventoryReceiptVO.ReceiptGoodsItemVO;

import java.util.List;

public class InventoryDamageDetailPane extends MyReceiptDetailPane<InventoryDamageReceiptVO> {
    @FXML
    InventoryOverflowDamageTreeTable damageItemTreeTable;

    @FXML
    private TextArea commentArea;
    @FXML
    private TextField operator;
   /* @FXML
    private TextField stateField;*/


    public InventoryDamageDetailPane() {
        damageItemTreeTable.setEditable(true);

    }

    public InventoryDamageDetailPane(InventoryDamageReceiptVO receiptVO) {
        super(receiptVO);
        operator.setText(String.valueOf(receiptVO.getOperatorId()));
        commentArea.setText(receiptVO.getComment());
     //   stateField.setText(receiptVO.getReceiptState().toString());
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

    @FXML
    @Override
    protected void reset() {
        operator.setText("");
        commentArea.setText("");
        //stateField.setText("");
        damageItemTreeTable.clear();
    }

    @FXML
    public void addGoods() {
        ReceiptGoodsItemVO receiptGoodsItemVO = new ReceiptGoodsItemVO();
        receiptGoodsItemVO.setGoodsName("please");
        receiptGoodsItemVO.setGoodsId("please");
        receiptGoodsItemVO.setInventoryNum(0);
        receiptGoodsItemVO.setFactNum(0);
        damageItemTreeTable.addGood(receiptGoodsItemVO);
    }
}
