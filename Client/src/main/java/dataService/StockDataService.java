package dataService;

import po.StockRelatedReceiptPO;
import util.ResultMessage;

/**
 * Created by tiberius on 2017/10/22.
 */
public interface StockDataService {
    public ResultMessage insert(StockRelatedReceiptPO stockReceiptPO);
    public ResultMessage update(StockRelatedReceiptPO stockReceiptPO);
    public ResultMessage delete(String id);
    public StockRelatedReceiptPO find(String id);
}
