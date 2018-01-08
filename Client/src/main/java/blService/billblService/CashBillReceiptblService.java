package blService.billblservice;

import util.ResultMessage;
import vo.billReceiptVO.BillReceiptSearchVO;
import vo.billReceiptVO.CashReceiptListVO;
import vo.billReceiptVO.CashReceiptVO;

import java.util.Set;

public interface CashBillReceiptblService {

    public int getDayId();
    public ResultMessage add(CashReceiptVO cashReceiptVO);
    public ResultMessage delete(String id);
    public ResultMessage update(CashReceiptVO cashReceiptVO);
    public Set<CashReceiptListVO> search(BillReceiptSearchVO billReceiptSearchVO);
    public CashReceiptVO showDetail(String id);
    public Set<CashReceiptListVO> getALL();

}
