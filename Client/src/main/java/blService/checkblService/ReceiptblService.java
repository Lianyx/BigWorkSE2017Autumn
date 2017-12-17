package blService.checkblService;

import po.ReceiptPO;
import util.ResultMessage;
import vo.ReceiptVO;

import java.rmi.RemoteException;

public interface ReceiptblService<T extends ReceiptVO> {
    int getDayId() throws RemoteException;

    ResultMessage insert(T receiptVO) throws RemoteException;

    ResultMessage update(T receiptVO) throws RemoteException;

    ResultMessage delete(T receiptVO) throws RemoteException;

    // TODO 这里至少还要加Search的。
}
