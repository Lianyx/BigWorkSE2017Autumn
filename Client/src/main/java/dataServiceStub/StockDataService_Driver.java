package dataServiceStub;

import dataService.stockdataService.StockDataService;
import dataServiceDriver.StockDataService_Stub;
import po.StockReceiptPO;

/**
 * Created by tiberius on 2017/10/22.
 */
public class StockDataService_Driver {
    public static void main(String[] args) {
        StockDataService stockDataService = new StockDataService_Stub();
        System.out.println(stockDataService.delete("00001"));
        System.out.println(stockDataService.insert(new StockReceiptPO("00001", "00002", "仓库丙", "李康", null, "", 1000)));
        System.out.println(stockDataService.update(new StockReceiptPO("00001", "00002", "仓库丙", "李康", null, "", 1000)));
        System.out.println(stockDataService.find("00001"));
    }
}
