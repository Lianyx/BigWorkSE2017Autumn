package ui.inventoryui.inventoryReceiptui;

import blService.checkblService.ReceiptblService;
import blService.inventoryblService.InventoryDamageReceiptblService;
import ui.myAccountantui.common.MyReceiptListPane;
import ui.util.Refreshable;
import vo.inventoryVO.inventoryReceiptVO.InventoryDamageListVO;
import vo.inventoryVO.inventoryReceiptVO.InventoryDamageReceiptVO;

public class InventoryDamageListPane  extends MyReceiptListPane<InventoryDamageListVO, InventoryDamageReceiptVO> {
    @Override
    protected void initiateTreeTable() {
        receiptListTreeTable = new InventoryDamageTablePane(chosenItems,searchField.textProperty());
    }

    @Override
    protected Class<? extends ReceiptblService<InventoryDamageReceiptVO>> getServiceClass() {
        return InventoryDamageReceiptblService.class;
    }

    @Override
    protected Refreshable getNewDetailPane() {
        return new InventoryDamageDetailPane();
    }
}