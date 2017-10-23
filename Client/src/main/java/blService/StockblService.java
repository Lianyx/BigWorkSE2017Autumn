package blService;

import util.ResultMessage;
import vo.StockRelatedReceiptVO;

import java.util.ArrayList;

/**
 * Created by tiberius on 2017/10/21.
 */
public interface StockblService {
    String getStockReceiptID();
    String getStockReturnReceiptID();
    ResultMessage submit(StockRelatedReceiptVO stockReceiptVO);
    ResultMessage update(StockRelatedReceiptVO stockReceiptVO);
    ResultMessage delete(String id);
    StockRelatedReceiptVO find(String id);
    ArrayList<String> getGoodsNames();
    int getPrice(String goodsName, String goodsVersion);
}
