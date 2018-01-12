package ui.inventoryui.inventoryReceiptui;

import blService.checkblService.ReceiptblService;
import blService.inventoryblService.InventoryGiftReceiptblService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import ui.inventoryui.GoodsChooseInfo;
import ui.inventoryui.goodsui.GoodChoose;
import ui.myAccountantui.common.MyReceiptDetailPane;
import vo.inventoryVO.inventoryReceiptVO.InventoryGiftReceiptVO;
import vo.inventoryVO.inventoryReceiptVO.ReceiptGoodsItemVO;
import vo.receiptVO.ReceiptVO;

import java.util.ArrayList;
import java.util.List;

public class InventoryGiftDetailPane extends MyReceiptDetailPane<InventoryGiftReceiptVO> {
    @FXML
    InventoryListItemTreeTable giftItemTreeTable;

  //  ObservableList<String> observableList = FXCollections.observableArrayList();

    List<String> goodsId;
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
        operator.setText(String.valueOf(receiptVO.getOperatorId()));
        commentArea.setText(receiptVO.getComment());
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
        // TODO 我没管transferList相关的（下面的reset同理）。而且这个vo里面没有comment………
    }

    @FXML
    @Override
    protected void reset() {
        //super.reset();
       /* operator.setText("");
        commentArea.setText("");
        stateField.setText("");*/
//        giftItemTreeTable.clear();
//        System.out.println(goodsId);

    }

    @FXML
    public void addGoods(){
        GoodsChooseInfo goodsChooseInfo = new GoodChoose();

        goodsId = new ArrayList<>();
        goodsChooseInfo.choose(goodsId);

        System.out.println(goodsId.toString());
        //giftItemTreeTable.addGood(new ReceiptGoodsItemVO("please","please",0,0));
    }
}
