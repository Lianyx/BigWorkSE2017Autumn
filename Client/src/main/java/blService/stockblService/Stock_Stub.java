package blService.stockblService;

import util.ResultMessage;
import vo.StockReceiptListVO;
import vo.StockReceiptVO;
import vo.StockSearchVO;

import java.util.ArrayList;
import java.util.Set;

public class Stock_Stub implements StockblService{

    public ResultMessage add(StockReceiptVO stockReceiptVO){return ResultMessage.SUCCESS;}
    public ResultMessage delete(int id){return ResultMessage.SUCCESS;}
    public ResultMessage delete(ArrayList<Integer> list){return ResultMessage.SUCCESS;}

    public ResultMessage update(StockReceiptVO stockReceiptVO){return ResultMessage.SUCCESS;}
    public Set<StockReceiptListVO> search(StockSearchVO stockSearchVO){return null;}
    public Set<StockReceiptListVO> search(String keyword){return null;}
    public StockReceiptVO showDetail(int id){return null;}
    public Set<StockReceiptListVO> getALL(){return null;}
}
