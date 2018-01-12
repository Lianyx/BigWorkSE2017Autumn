package ui.inventoryui.inventoryReceiptui;

import blService.checkblService.ReceiptblService;
import blService.inventoryblService.InventoryWarningReceiptblService;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import ui.myAccountantui.common.MyReceiptDetailPane;
import vo.inventoryVO.inventoryReceiptVO.InventoryWarningReceiptVO;
import vo.inventoryVO.inventoryReceiptVO.InventoryWarningReceiptVO;
import vo.inventoryVO.inventoryReceiptVO.InventoryWarningReceiptVO;
import vo.inventoryVO.inventoryReceiptVO.ReceiptGoodsItemVO;

import java.util.List;

public class InventoryWarningDetailPane extends MyReceiptDetailPane<InventoryWarningReceiptVO> {
    @FXML
    InventoryWarningTreeTable warningItemTreeTable;

    @FXML
    private TextArea commentArea;
    @FXML
    private TextField operator;


    public InventoryWarningDetailPane() {
        warningItemTreeTable.setEditable(true);

    }
    
    public InventoryWarningDetailPane(InventoryWarningReceiptVO receiptVO) {
        super(receiptVO);
        operator.setText(String.valueOf(receiptVO.getOperatorId()));
        commentArea.setText("无");
        warningItemTreeTable.setList(receiptVO.getItems());
        warningItemTreeTable.setEditable(true);
    }

    @Override
    protected String getURL() {
        return "/inventoryui/inventoryreceiptui/inventoryWarningDetailPane.fxml";
    }

    @Override
    protected Class<? extends ReceiptblService<InventoryWarningReceiptVO>> getServiceClass() {
        return InventoryWarningReceiptblService.class;
    }

    @Override
    protected boolean validate() {
        return super.validate();
    }

    @Override
    protected void updateReceiptVO() { // 这里不需要再检查了
        List<ReceiptGoodsItemVO> goodsList = warningItemTreeTable.getList();
        //System.out.println("OperatorId:"+operator.getText());
        receiptVO.setOperatorId(Integer.parseInt(operator.getText()));
        receiptVO.setComment(commentArea.getText());
        receiptVO.setItems(goodsList);

        // TODO 我没管transferList相关的（下面的reset同理）。而且这个vo里面没有comment………
    }

    @FXML
    @Override
    protected void reset() {
        operator.setText("");
        commentArea.setText("");
       // stateField.setText("");
        warningItemTreeTable.clear();
    }

    @FXML
    public void addGoods(){
        ReceiptGoodsItemVO receiptGoodsItemVO = new ReceiptGoodsItemVO();
        receiptGoodsItemVO.setGoodsName("please");
        receiptGoodsItemVO.setGoodsId("please");
        receiptGoodsItemVO.setInventoryNum(0);
        receiptGoodsItemVO.setFactNum(0);
        warningItemTreeTable.addGood(receiptGoodsItemVO);
    }
}
