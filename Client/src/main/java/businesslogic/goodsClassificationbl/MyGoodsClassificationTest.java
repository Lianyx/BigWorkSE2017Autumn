package businesslogic.goodsClassificationbl;

import blService.goodsClassificationblService.MyGoodsClassificationblService;
import businesslogic.blServiceFactory.MyblServiceFactory;
import vo.inventoryVO.MyGoodsClassificationVO;

import java.rmi.RemoteException;

public class MyGoodsClassificationTest {
    public static void main(String[] args) {
        MyGoodsClassificationblService myGoodsClassificationblService = MyblServiceFactory.getService(MyGoodsClassificationblService.class);
        try {
            MyGoodsClassificationVO gcvo = myGoodsClassificationblService.selectRoot();
            System.out.println("fdsa");
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        System.out.println("ok");


    }
}
