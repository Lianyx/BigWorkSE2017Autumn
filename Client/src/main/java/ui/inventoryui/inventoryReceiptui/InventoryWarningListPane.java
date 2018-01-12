package ui.inventoryui.inventoryReceiptui;

import blService.checkblService.ReceiptblService;
import blService.inventoryblService.InventoryWarningReceiptblService;
import businesslogic.inventorybl.InventoryWarningReceiptbl;
import ui.myAccountantui.common.MyReceiptListPane;
import ui.util.Refreshable;
import vo.inventoryVO.inventoryReceiptVO.InventoryWarningListVO;
import vo.inventoryVO.inventoryReceiptVO.InventoryWarningReceiptVO;

public class InventoryWarningListPane extends MyReceiptListPane<InventoryWarningListVO, InventoryWarningReceiptVO> {
    @Override
    protected void initiateTreeTable() {
        receiptListTreeTable = new InventoryWarningTablePane(chosenItems,searchField.textProperty());

    }

    @Override
    protected Class<? extends ReceiptblService<InventoryWarningReceiptVO>> getServiceClass() {
        return InventoryWarningReceiptblService.class;
    }

    @Override
    protected Refreshable getNewDetailPane() {
        return new InventoryWarningDetailPane();
    }
}
