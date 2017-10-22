package dataServiceStub;

import dataService.StockDataService;
import dataServiceDriver.StockDataService_Stub;
import po.StockReceiptPO;
import po.StockRelatedReceiptPO;

/**
 * Created by tiberius on 2017/10/22.
 */
public class StockDataService_Driver {
    public static void main(String[] args) {
        StockDataService stockDataService = new StockDataService_Stub();
        System.out.println(stockDataService.delete("00001"));
        System.out.println(stockDataService.insert(new StockReceiptPO("00001", "00002", "²Ö¿â±û", "Àî¿µ", null, "", 1000)));
        System.out.println(stockDataService.update(new StockReceiptPO("00001", "00002", "²Ö¿â±û", "Àî¿µ", null, "", 1000)));
        System.out.println(stockDataService.find("00001"));
    }
}
