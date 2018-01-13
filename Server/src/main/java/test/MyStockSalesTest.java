package test;

import data.salesdata.SalesRetReceiptData;
import data.salesdata.SalesSellReceiptData;
import data.stockdata.StockRetReceiptData;
import po.ReceiptGoodsItemPO;
import po.receiptPO.SalesRetReceiptPO;
import po.receiptPO.SalesSellReceiptPO;
import po.receiptPO.StockRetReceiptPO;

import java.rmi.RemoteException;

public class MyStockSalesTest {
    public static void main(String[] args) {
        try {
//            StockPurReceiptData stockPurReceiptData = new StockPurReceiptData();
//
//            StockPurReceiptPO sprp = stockPurReceiptData.getNew();
//            sprp.setMemberId(111);
//            sprp.setStockName("北大仓");
//            sprp.setOriginSum(10);
//            sprp.setComment("城葙冰");
//
//            stockPurReceiptData.update(sprp);


//            StockRetReceiptData stockRetRecepitData = new StockRetReceiptData();
//            StockRetReceiptPO srrp = stockRetRecepitData.getNew();
//            srrp.setComment("试一试StockRet");
//            stockRetRecepitData.update(srrp);


            SalesSellReceiptData salesSellReceiptData = new SalesSellReceiptData();
            SalesSellReceiptPO sspo = salesSellReceiptData.getNew();
            sspo.setComment("试一试SalesSell");
            sspo.setGoodsList(new ReceiptGoodsItemPO[]{new ReceiptGoodsItemPO("122", 3, 10, "没有的"),
                    new ReceiptGoodsItemPO("130", 2, 20, "大灯啊啊")});
            salesSellReceiptData.update(sspo);


//            SalesRetReceiptData salesRetReceiptData = new SalesRetReceiptData();
//            SalesRetReceiptPO srpo = salesRetReceiptData.getNew();
//            srpo.setComment("试一试SlaesRet");
//            salesRetReceiptData.update(srpo);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }
}
