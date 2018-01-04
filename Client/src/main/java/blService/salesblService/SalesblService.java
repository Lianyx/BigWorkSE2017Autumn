package blService.salesblService;

import vo.receiptVO.SalesReceiptListVO;
import vo.SalesSearchVO;
import util.ResultMessage;
import vo.receiptVO.SalesReceiptVO;

import java.util.Set;

public interface SalesblService {
    public int getDayId();
    public ResultMessage add(SalesReceiptVO salesReceiptVO);
    public ResultMessage delete(String id);
    public ResultMessage update(SalesReceiptVO stockReceiptVO);
    public Set<SalesReceiptListVO> search(SalesSearchVO salesSearchVO,boolean isPur);
    public SalesReceiptVO showDetail(String id);
    public Set<SalesReceiptListVO> getALL(boolean isPur);
}
