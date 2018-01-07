package blService.salesblService;

import blService.checkblService.ReceiptblService;
import vo.receiptVO.SalesReceiptListVO;
import vo.SalesSearchVO;
import util.ResultMessage;
import vo.receiptVO.SalesReceiptVO;

import java.util.Set;

public interface SalesblService extends ReceiptblService<SalesReceiptVO>{
    public int getDayId();
    public ResultMessage delete(String id);
    public Set<SalesReceiptListVO> search(SalesSearchVO salesSearchVO,boolean isPur);
    public SalesReceiptVO showDetail(String id);
    public Set<SalesReceiptListVO> getALL(boolean isPur);
}
