package dataServiceDriver;

import dataService.stockdataService.StockDataService;
import po.StockReceiptPO;
import po.StockRelatedReceiptPO;
import bl.util.ResultMessage;

/**
 * Created by tiberius on 2017/10/22.
 */
public class StockDataService_Stub implements StockDataService {
    @Override
    public ResultMessage insert(StockRelatedReceiptPO stockReceiptPO) {
        return ResultMessage.SUCCESS;
    }

    @Override
    public ResultMessage update(StockRelatedReceiptPO stockReceiptPO) {
        return ResultMessage.SUCCESS;
    }

    @Override
    public ResultMessage delete(String id) {
        return ResultMessage.FAIL;
    }

    @Override
    public StockRelatedReceiptPO find(String id) {
        return new StockReceiptPO("00001", "00002", "仓库丙", "李康", null, "", 1000);
    }
}
