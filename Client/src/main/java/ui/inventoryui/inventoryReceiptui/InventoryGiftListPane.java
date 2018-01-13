package ui.inventoryui.inventoryReceiptui;

import blService.checkblService.ReceiptblService;
import blService.inventoryblService.InventoryGiftReceiptblService;
import ui.common.MyReceiptListPane;
import ui.util.Refreshable;
import vo.inventoryVO.inventoryReceiptVO.InventoryGiftListVO;
import vo.inventoryVO.inventoryReceiptVO.InventoryGiftReceiptVO;

public class InventoryGiftListPane extends MyReceiptListPane<InventoryGiftListVO, InventoryGiftReceiptVO> {
    @Override
    protected void initiateTreeTable() {
        receiptListTreeTable = new InventoryGiftTablePane(chosenItems,searchField.textProperty());
    }

    @Override
    protected Class<? extends ReceiptblService<InventoryGiftReceiptVO>> getServiceClass() {
        return InventoryGiftReceiptblService.class;
    }

    @Override
    protected Refreshable getNewDetailPane() {
        return new InventoryGiftDetailPane();
    }
}
