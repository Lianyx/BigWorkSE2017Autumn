package blServiceDriver;

import blServiceStub.SalesblService_Stub;
import blService.salesblService.SalesblService;
import vo.SalesReceiptVO;

/**
 * Created by tiberius on 2017/10/21.
 */
public class SalesblService_Driver {
    public static void main(String[] args) {
        SalesblService salesblService = new SalesblService_Stub();
        System.out.println(salesblService.getSalesReceiptID());
        System.out.println(salesblService.getSalesReturnReceiptID());
        System.out.println(salesblService.submit(new SalesReceiptVO("00001", "00001", "仓库甲")));
        System.out.println(salesblService.update(new SalesReceiptVO("00001", "00001", "仓库乙")));
        System.out.println(salesblService.delete("XSD-20171022-00001"));
        System.out.println(salesblService.find("XSD-20171022-00001"));
        salesblService.getGoodsNames().forEach(System.out::println);
        System.out.println(salesblService.getPrice("商品甲", "型号乙"));
        salesblService.getPromotions().forEach(System.out::println);
    }
}
