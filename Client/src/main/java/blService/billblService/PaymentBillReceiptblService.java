package blService.billblservice;

import blService.checkblService.ReceiptblService;
import po.receiptPO.PaymentBillReceiptPO;
import util.ResultMessage;
import vo.billReceiptVO.BillReceiptSearchVO;
import vo.billReceiptVO.PaymentReceiptListVO;
import vo.billReceiptVO.PaymentReceiptVO;

import java.util.Set;

public interface PaymentBillReceiptblService extends ReceiptblService<PaymentReceiptVO> {

    public int getDayId();
    public ResultMessage add(PaymentReceiptVO paymentReceiptVO);
    public ResultMessage delete(String id);
    public Set<PaymentReceiptListVO> search(BillReceiptSearchVO billReceiptSearchVO);
    public PaymentReceiptVO showDetail(String id);
    public Set<PaymentReceiptListVO> getALL();
}
