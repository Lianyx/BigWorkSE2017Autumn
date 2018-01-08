package businesslogic.goodsbl.goodsUpdate;

import blService.goodsblService.GoodsUpdateInfo;
import businesslogic.goodsbl.Goodsbl;
import po.GoodsPO;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;

public class GoodsUpdate implements GoodsUpdateInfo {
    Goodsbl goodsbl;
    public GoodsUpdate() throws RemoteException, NotBoundException, MalformedURLException {goodsbl = new Goodsbl();}


    @Override
    public void goodsUpdate(List<GoodsPO> goodsList) throws RemoteException {
        for (GoodsPO po:goodsList) {
            goodsbl.updateGoods(po);
        }
    }


}
