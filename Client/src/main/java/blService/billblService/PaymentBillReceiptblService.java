package blService.billblService;

import util.ResultMessage;
import vo.SalesSearchVO;
import vo.billReceiptVO.BillReceiptSearchVO;
import vo.billReceiptVO.PaymentReceiptListVO;
import vo.billReceiptVO.PaymentReceiptVO;

import java.util.Set;

public interface PaymentBillReceiptblService {

    public int getDayId();
    public ResultMessage add(PaymentReceiptVO paymentReceiptVO);
    public ResultMessage delete(String id);
    public ResultMessage update(PaymentReceiptVO paymentReceiptVO);
    public Set<PaymentReceiptListVO> search(BillReceiptSearchVO billReceiptSearchVO, boolean isPur);
    public PaymentReceiptVO showDetail(String id);
    public Set<PaymentReceiptListVO> getALL(boolean isPur);
}
