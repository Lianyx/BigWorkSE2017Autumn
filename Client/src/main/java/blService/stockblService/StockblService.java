package blService.stockblService;

import vo.receiptVO.StockReceiptListVO;
import vo.StockSearchVO;
import vo.receiptVO.StockReceiptVO;
import util.ResultMessage;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Set;

public interface StockblService {
    public int getDayId();
    public ResultMessage add(StockReceiptVO stockReceiptVO);
    public ResultMessage delete(String id);
    public ResultMessage update(StockReceiptVO stockReceiptVO);
    public Set<StockReceiptListVO> search(StockSearchVO stockSearchVO,boolean isPur);
    public Set<StockReceiptListVO> search(String keyword,boolean isPur);
    public StockReceiptVO showDetail(String id);
    public Set<StockReceiptListVO> getALL(boolean isPur);
}
