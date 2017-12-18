package blService.checkblService;

import util.ReceiptSearchCondition;
import util.ResultMessage;
import vo.ReceiptVO.ReceiptVO;

import java.rmi.RemoteException;
import java.util.ArrayList;

public interface ReceiptblService<T extends ReceiptVO> {
    int getDayId() throws RemoteException;

    ResultMessage insert(T receiptVO) throws RemoteException;

    ResultMessage update(T receiptVO) throws RemoteException;

    ResultMessage delete(T receiptVO) throws RemoteException;

    ArrayList<T> search(ReceiptSearchCondition receiptSearchCondition) throws RemoteException;
}
