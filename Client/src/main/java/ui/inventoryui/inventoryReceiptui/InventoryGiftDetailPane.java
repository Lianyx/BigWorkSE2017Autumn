package ui.inventoryui.inventoryReceiptui;

import blService.checkblService.ReceiptblService;
import blService.inventoryblService.InventoryGiftReceiptblService;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import ui.myAccountantui.common.MyReceiptDetailPane;
import vo.inventoryVO.inventoryReceiptVO.InventoryGiftReceiptVO;
import vo.inventoryVO.inventoryReceiptVO.ReceiptGoodsItemVO;

import java.util.List;

public class InventoryGiftDetailPane extends MyReceiptDetailPane<InventoryGiftReceiptVO> {
    @FXML
    InventoryListItemTreeTable giftItemTreeTable;

    @FXML
    private TextArea commentArea;
    @FXML
    private TextField operator;

    //InventoryGiftReceiptblService inventoryGiftReceiptblService = (Inven)getServiceClass();


    public InventoryGiftDetailPane() {
        giftItemTreeTable.setEditable(true);

    }

    public InventoryGiftDetailPane(InventoryGiftReceiptVO receiptVO) {
        super(receiptVO);
        operator.setText(String.valueOf(receiptVO.getOperatorId()));
        commentArea.setText("无");
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
        System.out.println(operator.getText());
      // receiptVO.setOperatorId(Integer.parseInt(operator.getText()));
        receiptVO.setItems(goodsList);
        for (ReceiptGoodsItemVO vo:goodsList) {
            System.out.println(vo.getGoodsName()+" "+vo.getSendNum());
        }
        System.out.println(goodsList.toString());

        // TODO 我没管transferList相关的（下面的reset同理）。而且这个vo里面没有comment………
    }

    @FXML
    @Override
    protected void reset() {
        super.reset();
    }

    @FXML
    public void addGoods(){
        giftItemTreeTable.addGood(new ReceiptGoodsItemVO("please","please",0,0));
    }
}
