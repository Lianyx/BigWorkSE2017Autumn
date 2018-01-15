package blService.goodsClassificationblService;

import util.ResultMessage;
import vo.inventoryVO.GoodsClassificationVO;
import vo.inventoryVO.MyGoodsClassificationVO;

import java.rmi.RemoteException;

public interface MyGoodsClassificationblService {
    MyGoodsClassificationVO selectRoot() throws RemoteException;

    MyGoodsClassificationVO selectById(String id) throws RemoteException;

    void add(MyGoodsClassificationVO vo) throws RemoteException;

    void delete(MyGoodsClassificationVO vo) throws RemoteException;

    void update(MyGoodsClassificationVO vo) throws RemoteException;

}
