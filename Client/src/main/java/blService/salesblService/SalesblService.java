package blService.salesblService;

import vo.SalesReceiptListVO;
import vo.SalesSearchVO;
import util.ResultMessage;
import vo.receiptVO.SalesReceiptVO;

import java.util.ArrayList;
import java.util.Set;

public interface SalesblService {
    public ResultMessage add(SalesReceiptVO salesReceiptVO);
    public ResultMessage delete(int id);
    public ResultMessage delete(ArrayList<Integer> list);

    public ResultMessage update(SalesReceiptVO salesReceiptVO);
    public Set<SalesReceiptListVO> search(SalesSearchVO salesSearchVO);
    public Set<SalesReceiptListVO> search(String keyword);
    public SalesReceiptVO showDetail(int id);
    public Set<SalesReceiptListVO> getAll();

}
