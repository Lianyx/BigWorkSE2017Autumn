package ui.inventoryui.inventoryReceiptui;

import blService.checkblService.ReceiptblService;
import blService.inventoryblService.InventoryGiftReceiptblService;
import ui.myAccountantui.common.MyReceiptListPane;
import vo.inventoryVO.InventoryGiftListVO;
import vo.inventoryVO.InventoryGiftReceiptVO;

public class InventoryGiftListPane extends MyReceiptListPane<InventoryGiftListVO, InventoryGiftReceiptVO> {
    @Override
    protected void initiateTreeTable() {
        receiptListTreeTable = new InventoryGiftTablePane(chosenItems,searchField.textProperty());
    }

    @Override
    protected Class<? extends ReceiptblService<InventoryGiftReceiptVO>> getServiceClass() {
        return InventoryGiftReceiptblService.class;
    }
}
