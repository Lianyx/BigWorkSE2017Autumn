package blService.stockblService;

import vo.StockReceiptListVO;
import vo.StockSearchVO;
import vo.StockReceiptVO;
import util.ResultMessage;

import java.util.ArrayList;
import java.util.Set;

public interface StockblService {
    public ResultMessage add(StockReceiptVO stockReceiptVO);
    public ResultMessage delete(int id);
    public ResultMessage delete(ArrayList<Integer> list);

    public ResultMessage update(StockReceiptVO stockReceiptVO);
    public Set<StockReceiptListVO> search(StockSearchVO stockSearchVO);
    public Set<StockReceiptListVO> search(String keyword);
    public StockReceiptVO showDetail(int id);
    public Set<StockReceiptListVO> getALL();
}
