package businesslogic.goodsbl.goodsUpdate;

import blService.goodsblService.GoodsSalesUpdateInfo;
import businesslogic.goodsbl.Goodsbl;
import po.GoodsPO;
import vo.ListGoodsItemVO;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;

public class GoodsSalesUpdate implements GoodsSalesUpdateInfo {
    Goodsbl goodsbl;
    public GoodsSalesUpdate() throws RemoteException, NotBoundException, MalformedURLException {goodsbl = new Goodsbl();}


    @Override
    public void goodsUpdate(List<ListGoodsItemVO> goodsList) throws RemoteException {

    }


}
