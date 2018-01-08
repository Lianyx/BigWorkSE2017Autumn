package businesslogic.promotionbl.testprmt;

import blService.goodsblService.goodsSearchInfo;
import businesslogic.checkbl.MyServiceFactory;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class TestGoodsSearch {
    public static void main(String[] args) {
        try {
            goodsSearchInfo goods = MyServiceFactory.getGoodsSearchInfo();
            System.out.println(goods.getGoodById("12432").getGoodName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
