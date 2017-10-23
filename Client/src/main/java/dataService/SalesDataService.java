package dataService;

import po.SalesRelatedReceiptPO;
import util.ResultMessage;

/**
 * Created by tiberius on 2017/10/22.
 */
public interface SalesDataService {
    public ResultMessage insert(SalesRelatedReceiptPO salesReceiptPO);
    public ResultMessage update(SalesRelatedReceiptPO salesReceiptPO);
    public ResultMessage delete(String id);
    public SalesRelatedReceiptPO find(String id);
}
