package ui.myAccountantui;

import blService.billblservice.CashBillReceiptblService;
import blService.checkblService.ReceiptblService;
import ui.myAccountantui.common.MyReceiptListPane;
import ui.util.ReceiptListPane;
import ui.util.Refreshable;
import vo.billReceiptVO.CashReceiptListVO;
import vo.billReceiptVO.CashReceiptVO;

public class MyCashReceiptListPane extends MyReceiptListPane<CashReceiptListVO, CashReceiptVO> {
    @Override
    protected void initiateTreeTable() {
        receiptListTreeTable = new MyCashTablePane(chosenItems, searchField.textProperty());
    }

    @Override
    protected Class<? extends ReceiptblService<CashReceiptVO>> getServiceClass() {
        return CashBillReceiptblService.class;
    }

    @Override
    protected Refreshable getNewDetailPane() {
        return null;
    }
}
