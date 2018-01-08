package businesslogic.goodsbl;

import dataService.goodsdataService.GoodsDataService;
import po.GoodsPO;
import vo.inventoryVO.GoodsVO;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Set;

public class Test {
    public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException {
        Goodsbl goodsbl = new Goodsbl();
        Set<GoodsVO> list = goodsbl.show();
        System.out.println(list.toString());
    }
}
