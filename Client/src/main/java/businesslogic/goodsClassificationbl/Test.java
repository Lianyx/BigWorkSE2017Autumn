package businesslogic.goodsClassificationbl;

import vo.inventoryVO.GoodsClassificationVO;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;

public class Test {
    public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException {
        GoodsClassificationbl goodsClassificationbl = new GoodsClassificationbl();

        List<GoodsClassificationVO> list = goodsClassificationbl.show();

        System.out.println(list);
    }
}
