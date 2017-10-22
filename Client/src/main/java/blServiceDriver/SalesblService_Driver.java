package blServiceDriver;

import blServiceStub.SalesblService_Stub;
import blService.SalesblService;
import vo.SalesReceiptVO;
import vo.SalesRelatedReceiptVO;

/**
 * Created by tiberius on 2017/10/21.
 */
public class SalesblService_Driver {
    public static void main(String[] args) {
        SalesblService salesblService = new SalesblService_Stub();
        System.out.println(salesblService.getSalesReceiptID());
        System.out.println(salesblService.getSalesReturnReceiptID());
        System.out.println(salesblService.submit(new SalesReceiptVO("00001", "00001", "≤÷ø‚º◊")));
        System.out.println(salesblService.update(new SalesReceiptVO("00001", "00001", "≤÷ø‚““")));
        System.out.println(salesblService.delete("XSD-20171022-00001"));
        System.out.println(salesblService.find("XSD-20171022-00001"));
        salesblService.getGoodsNames().forEach(System.out::println);
        System.out.println(salesblService.getPrice("…Ã∆∑º◊", "–Õ∫≈““"));
        salesblService.getPromotions().forEach(System.out::println);
    }
}
