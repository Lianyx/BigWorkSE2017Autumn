package blService.checkblService;

import po.ReceiptPO;
import util.ResultMessage;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public interface CheckInfo {
    <TF extends ReceiptPO> ResultMessage update(TF receiptPO) throws RemoteException;
    <TF extends ReceiptPO> ResultMessage approve(TF receiptPO) throws RemoteException;

    ArrayList<? extends ReceiptPO> selectPending() throws RemoteException;
}
