package ui.myAccountantui;

import blService.billblservice.PaymentBillReceiptblService;
import blService.checkblService.ReceiptblService;
import ui.myAccountantui.common.MyReceiptListPane;
import ui.util.Refreshable;
import vo.billReceiptVO.PaymentReceiptListVO;
import vo.billReceiptVO.PaymentReceiptVO;


public class MyPaymentReceiptListPane extends MyReceiptListPane<PaymentReceiptListVO, PaymentReceiptVO> {
    @Override
    protected void initiateTreeTable() {
        receiptListTreeTable = new MyPaymentTablePane(chosenItems, searchField.textProperty());
    }

    @Override
    protected Class<? extends ReceiptblService<PaymentReceiptVO>> getServiceClass() {
        return PaymentBillReceiptblService.class;
    }

    @Override
    protected Refreshable getNewDetailPane() {
        return new MyPaymentDetailPane();
    }
}
