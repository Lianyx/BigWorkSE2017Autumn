package dataServiceDriver;

import dataService.StockDataService;
import po.StockRelatedReceiptPO;
import utilitybl.ResultMessage;

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
        return new StockRelatedReceiptPO("00001", "00002", "�ֿ��", "�", null, "", 1000);
    }
}
