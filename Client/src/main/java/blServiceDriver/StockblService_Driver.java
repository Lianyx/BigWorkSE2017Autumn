package blServiceDriver;

import blServiceStub.StockblService_Stub;
import blService.stockblService.StockblService;
import vo.StockReceiptVO;
import vo.StockReturnReceiptVO;

/**
 * Created by tiberius on 2017/10/21.
 */
public class StockblService_Driver {
    public static void main(String[] args) {
        StockblService stockblService = new StockblService_Stub();
        System.out.println(stockblService.getStockReceiptID());
        System.out.println(stockblService.getStockReturnReceiptID());
        System.out.println(stockblService.submit(new StockReceiptVO("00001", "供应商甲", "仓库甲",  "操作员甲", null, "no remark", 1000)));
        System.out.println(stockblService.update(new StockReturnReceiptVO("00001", "供应商甲", "仓库甲",  "操作员甲", null, "no remark", 1000)));
        System.out.println(stockblService.delete("JHD-20171022-00001"));
        System.out.println(stockblService.find("JHD-20171022-00001"));
        stockblService.getGoodsNames().forEach(System.out::println);
        System.out.println(stockblService.getPrice("商品甲", "型号乙"));
    }
}
