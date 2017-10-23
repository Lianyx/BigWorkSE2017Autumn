package dataServiceStub;

import dataService.SalesDataService;
import dataServiceDriver.SalesDataService_Stub;
import po.SalesReceiptPO;
import po.SalesRelatedReceiptPO;

/**
 * Created by tiberius on 2017/10/22.
 */
public class SalesDataService_Driver {
    public static void main(String[] args) {
        SalesDataService salesDataService = new SalesDataService_Stub();
        System.out.println(salesDataService.delete("00001"));
        System.out.println(salesDataService.insert(new SalesReceiptPO("00001", "00002", "仓库甲")));
        System.out.println(salesDataService.update(new SalesReceiptPO("00001", "00002", "仓库甲")));
        System.out.println(salesDataService.find("00001"));
    }
}
