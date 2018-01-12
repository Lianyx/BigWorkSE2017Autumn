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


    public InventoryDamageDetailPane() {
        damageItemTreeTable.setEditable(true);

    }

    public InventoryDamageDetailPane(InventoryDamageReceiptVO receiptVO) {
        super(receiptVO);
        operator.setText(String.valueOf(receiptVO.getOperatorId()));
        commentArea.setText("无");
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
    public void addGoods() {
        damageItemTreeTable.addGood(new ReceiptGoodsItemVO("please", "please", 0, 0));
    }
}
