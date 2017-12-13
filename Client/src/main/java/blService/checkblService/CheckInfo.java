package blService.checkblService;

import po.ReceiptPO;
import util.ResultMessage;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public interface CheckInfo {
    <T extends ReceiptPO> ResultMessage update(T receiptPO) throws RemoteException;
    <T extends ReceiptPO> ResultMessage approve(T receiptPO) throws RemoteException;

    ArrayList<? extends ReceiptPO> selectPending() throws RemoteException;
}
