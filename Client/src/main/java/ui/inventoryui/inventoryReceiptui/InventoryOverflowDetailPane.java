package ui.inventoryui.inventoryReceiptui;

import blService.checkblService.ReceiptblService;
import blService.inventoryblService.InventoryOverflowReceiptblService;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import ui.myAccountantui.common.MyReceiptDetailPane;
import vo.inventoryVO.inventoryReceiptVO.InventoryOverflowReceiptVO;
import vo.inventoryVO.inventoryReceiptVO.ReceiptGoodsItemVO;

import java.util.List;

public class InventoryOverflowDetailPane extends MyReceiptDetailPane<InventoryOverflowReceiptVO> {
    @FXML
    InventoryOverflowDamageTreeTable overflowItemTreeTable;

    @FXML
    private TextArea commentArea;
    @FXML
    private TextField operator;

    public InventoryOverflowDetailPane() {
        overflowItemTreeTable.setEditable(true);
    }

    public InventoryOverflowDetailPane(InventoryOverflowReceiptVO receiptVO) {
        super(receiptVO);
        operator.setText(String.valueOf(receiptVO.getOperatorId()));
        commentArea.setText("无");
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
        overflowItemTreeTable.addGood(new ReceiptGoodsItemVO("please","please",0,0));
    }
}
