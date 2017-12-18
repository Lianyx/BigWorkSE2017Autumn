package dataService.promotiondataService;

import po.promotionPO.PromotionPO;
import util.ResultMessage;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface PromotionDataService<T extends PromotionPO> extends Remote {
    int getDayId() throws RemoteException;

    ResultMessage insert(T promotionPO) throws RemoteException;

    ResultMessage update(T promotionPO) throws RemoteException;

    ResultMessage delete(T promotionPO) throws RemoteException;

    List<T> selectInEffect() throws RemoteException;

}
