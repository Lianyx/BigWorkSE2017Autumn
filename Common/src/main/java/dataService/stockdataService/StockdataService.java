package dataService.stockdataService;

import po.StockReceiptPO;
import util.ResultMessage;


import java.util.ArrayList;
import java.util.Set;

public interface StockdataService {
    public ResultMessage insert(StockReceiptPO stockReceiptPO);
    public ResultMessage delete(int id);
    public ResultMessage delete(ArrayList<Integer> list);

    public ResultMessage update(StockReceiptPO stockReceiptPO);
    public ArrayList<StockReceiptPO> select(String keyword);
    public StockReceiptPO showDetail(int id);
    public ArrayList<StockReceiptPO> getALL();
}
