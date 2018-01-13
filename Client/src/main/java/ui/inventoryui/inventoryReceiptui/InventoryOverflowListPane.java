package ui.inventoryui.inventoryReceiptui;

import blService.checkblService.ReceiptblService;
import blService.inventoryblService.InventoryOverflowReceiptblService;
import ui.common.MyReceiptListPane;
import ui.util.Refreshable;
import vo.inventoryVO.inventoryReceiptVO.InventoryOverflowListVO;
import vo.inventoryVO.inventoryReceiptVO.InventoryOverflowReceiptVO;

public class InventoryOverflowListPane extends MyReceiptListPane<InventoryOverflowListVO, InventoryOverflowReceiptVO> {
    @Override
    protected void initiateTreeTable() {
        receiptListTreeTable = new InventoryOverflowTablePane(chosenItems,searchField.textProperty());

    }

    @Override
    protected Class<? extends ReceiptblService<InventoryOverflowReceiptVO>> getServiceClass() {
        return InventoryOverflowReceiptblService.class;
    }

    @Override
    protected Refreshable getNewDetailPane() {
        return new InventoryOverflowDetailPane();
    }
}
