package ui.salesui.salesRetui;

import blService.checkblService.ReceiptblService;
import blService.salesblService.SalesRetblService;
import blService.salesblService.SalesSellblService;
import ui.myAccountantui.MyPaymentTablePane;
import ui.myAccountantui.common.MyReceiptListPane;
import ui.salesui.SalesListPane;
import ui.util.Refreshable;
import vo.receiptVO.SalesReceiptListVO;
import vo.receiptVO.SalesRetListVO;
import vo.receiptVO.SalesRetReceiptVO;

public class SalesRetListPane extends SalesListPane<SalesRetListVO,SalesRetReceiptVO>{


    @Override
    protected void initiateTreeTable() {
        receiptListTreeTable = new SalesRetTablePane(chosenItems, searchField.textProperty());

    }

    @Override
    protected Class<? extends ReceiptblService<SalesRetReceiptVO>> getServiceClass() {
        return SalesRetblService.class;
    }

    @Override
    protected Refreshable getNewDetailPane() {
        return null;
    }
}
