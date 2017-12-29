package blService.checkblService;

import util.ReceiptSearchCondition;
import util.RespectiveReceiptSearchCondition;
import util.ResultMessage;
import vo.receiptVO.ReceiptVO;

import java.rmi.RemoteException;
import java.util.ArrayList;

public interface ReceiptblService<T extends ReceiptVO> {
    int getDayId() throws RemoteException;

    ResultMessage insert(T receiptVO) throws RemoteException;

    ResultMessage update(T receiptVO) throws RemoteException;

    ResultMessage delete(T receiptVO) throws RemoteException;

    ArrayList<T> search(ReceiptSearchCondition receiptSearchCondition) throws RemoteException;
    ArrayList<T> search(RespectiveReceiptSearchCondition respectiveReceiptSearchCondition) throws RemoteException;
}
