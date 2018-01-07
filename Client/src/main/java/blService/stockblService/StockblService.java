package blService.stockblService;

import blService.checkblService.ReceiptblService;
import vo.receiptVO.StockReceiptListVO;
import vo.StockSearchVO;
import vo.receiptVO.StockReceiptVO;
import util.ResultMessage;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Set;

public interface StockblService extends ReceiptblService<StockReceiptVO>{
    public  int getDayId();
    public  ResultMessage delete(String id);
    public  Set<StockReceiptListVO> search(StockSearchVO stockSearchVO,boolean isPur);
    public  Set<StockReceiptListVO> search(String keyword,boolean isPur);
    public  StockReceiptVO showDetail(String id);
    public  Set<StockReceiptListVO> getALL(boolean isPur);
}
