package blServiceDriver;

import blServiceStub.StockblService_Stub;
import blService.StockblService;
import vo.StockReceiptVO;
import vo.StockRelatedReceiptVO;
import vo.StockReturnReceiptVO;

/**
 * Created by tiberius on 2017/10/21.
 */
public class StockblService_Driver {
    public static void main(String[] args) {
        StockblService stockblService = new StockblService_Stub();
        System.out.println(stockblService.getStockReceiptID());
        System.out.println(stockblService.getStockReturnReceiptID());
        System.out.println(stockblService.submit(new StockReceiptVO("00001", "��Ӧ�̼�", "�ֿ��",  "����Ա��", null, "no remark", 1000)));
        System.out.println(stockblService.update(new StockReturnReceiptVO("00001", "��Ӧ�̼�", "�ֿ��",  "����Ա��", null, "no remark", 1000)));
        System.out.println(stockblService.delete("JHD-20171022-00001"));
        System.out.println(stockblService.find("JHD-20171022-00001"));
        stockblService.getGoodsNames().forEach(System.out::println);
        System.out.println(stockblService.getPrice("��Ʒ��", "�ͺ���"));
    }
}
