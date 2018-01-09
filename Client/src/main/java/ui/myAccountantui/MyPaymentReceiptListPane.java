package ui.myAccountantui;

import blService.billblservice.PaymentBillReceiptblService;
import blService.checkblService.ReceiptblService;
import ui.myAccountantui.generic.MyReceiptListPane;
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
}
