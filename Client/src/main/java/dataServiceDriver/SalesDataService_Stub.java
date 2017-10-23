package dataServiceDriver;

import dataService.SalesDataService;
import po.SalesReceiptPO;
import po.SalesRelatedReceiptPO;
import util.ResultMessage;

/**
 * Created by tiberius on 2017/10/22.
 */
public class SalesDataService_Stub implements SalesDataService {
    @Override
    public ResultMessage insert(SalesRelatedReceiptPO salesReceiptPO) {
        return ResultMessage.SUCCESS;
    }

    @Override
    public ResultMessage update(SalesRelatedReceiptPO salesReceiptPO) {
        return ResultMessage.SUCCESS;
    }

    @Override
    public ResultMessage delete(String id) {
        return ResultMessage.FAIL;
    }

    @Override
    public SalesRelatedReceiptPO find(String id) {
        return new SalesReceiptPO("00002", "00001", "仓库乙");
    }
}
