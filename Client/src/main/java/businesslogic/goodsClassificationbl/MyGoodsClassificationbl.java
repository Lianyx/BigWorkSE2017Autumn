package businesslogic.goodsClassificationbl;

import blService.goodsClassificationblService.MyGoodsClassificationblService;
import businesslogic.goodsbl.GoodsPOVOChanger;
import dataService.goodsdataService.GoodsClassificationDataService;
import dataService.goodsdataService.GoodsDataService;
import po.GoodsClassificationPO;
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

        if (gcpo.getGoodsId() != null && gcpo.getChildrenId().length != 0) {
            for (String s : gcpo.getGoodsId()) {
                result.getGoods().add(goodsPOVOChanger.oneToVO(goodsDataService.selectById(s)));
            }
            return result;
        }

        if (gcpo.getChildrenId() != null) {
            for (String s : gcpo.getChildrenId()) {
                result.getChildren().add(selectById(s));
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
