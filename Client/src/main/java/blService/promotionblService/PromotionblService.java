package blService.promotionblService;

import util.ResultMessage;
import vo.promotionVO.PromotionVO;

import java.rmi.RemoteException;
import java.util.ArrayList;

public interface PromotionblService<T extends PromotionVO> {
    int getDayId() throws RemoteException;
    ResultMessage insert(T promotionVO) throws RemoteException;
    ResultMessage update(T promotionVO) throws RemoteException;
    ResultMessage delete(T promotionVO) throws RemoteException;
    ArrayList<T> selectInEffect() throws RemoteException;
    // TODO 肯定还需要有其他select的方法
}
