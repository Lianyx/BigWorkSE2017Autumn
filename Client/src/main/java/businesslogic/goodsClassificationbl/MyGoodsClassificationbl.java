package businesslogic.goodsClassificationbl;

import blService.goodsClassificationblService.MyGoodsClassificationblService;
import businesslogic.goodsbl.GoodsPOVOChanger;
import dataService.goodsdataService.GoodsClassificationDataService;
import dataService.goodsdataService.GoodsDataService;
import po.GoodsClassificationPO;
import po.GoodsPO;
import vo.inventoryVO.GoodsVO;
import vo.inventoryVO.MyGoodsClassificationVO;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class MyGoodsClassificationbl implements MyGoodsClassificationblService {
    private GoodsClassificationDataService goodsClassificationData;
    private GoodsDataService goodsDataService;
    private GoodsPOVOChanger goodsPOVOChanger = new GoodsPOVOChanger();

    public MyGoodsClassificationbl() throws RemoteException, NotBoundException, MalformedURLException {
        goodsClassificationData = (GoodsClassificationDataService) Naming.lookup("rmi://localhost:1099/GoodsClassificationData");
        goodsDataService = (GoodsDataService) Naming.lookup("rmi://localhost:1099/GoodsData");
    }

    @Override
    public MyGoodsClassificationVO selectRoot() throws RemoteException {
        return selectById("root");
    }

    @Override
    public MyGoodsClassificationVO selectById(String id) throws RemoteException {
        GoodsClassificationPO gcpo = goodsClassificationData.getById(id);
        MyGoodsClassificationVO result = new MyGoodsClassificationVO(gcpo);


        if (gcpo.getGoodsId() != null && gcpo.getGoodsId().length != 0) {
            for (String s : gcpo.getGoodsId()) {
                GoodsPO goodsPO = goodsDataService.selectById(s);
                result.getGoods().add(goodsPOVOChanger.oneToVO(goodsPO));
            }
            return result;
        }

        if (gcpo.getChildrenId() != null) {
            for (String s : gcpo.getChildrenId()) {
                MyGoodsClassificationVO node = selectById(s);
                node.setFather(result);
                // 我怎么感觉不set也是可以的
                // 这个方法如果不是root调用那么返回值也不会赋father
                result.getChildren().add(node);
            }
        }
        return result;
    }

    @Override
    public void add(MyGoodsClassificationVO vo) throws RemoteException {
        goodsClassificationData.insert(vo.toPO());
    }

    @Override
    public void delete(MyGoodsClassificationVO vo) throws RemoteException {
        goodsClassificationData.delete(vo.getId());
    }

    @Override
    public void update(MyGoodsClassificationVO vo) throws RemoteException {
        goodsClassificationData.update(vo.toPO());
    }
}
