package ui.inventoryui.inventoryReceiptui;

import blService.checkblService.ReceiptblService;
import blService.inventoryblService.InventoryGiftReceiptblService;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import ui.myAccountantui.common.MyReceiptDetailPane;
import vo.inventoryVO.InventoryGiftReceiptVO;

public class InventoryGiftDetailPane extends MyReceiptDetailPane<InventoryGiftReceiptVO> {
    @FXML
    private TextArea commentArea;

    public InventoryGiftDetailPane() {
    }

    public InventoryGiftDetailPane(InventoryGiftReceiptVO receiptVO) {
        super(receiptVO);
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
        super.updateReceiptVO();
        // TODO 我没管transferList相关的（下面的reset同理）。而且这个vo里面没有comment………
    }

    @FXML
    @Override
    protected void reset() {
        super.reset();
    }
}
