package dataService.checkdataService;

import po.ReceiptPO;
import util.ReceiptState;
import util.ResultMessage;
import util.ReceiptSearchCondition;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public interface ReceiptDataService<T extends ReceiptPO> extends Remote {

    int getDayId() throws RemoteException;

    ResultMessage insert(T promotionPO) throws RemoteException;

    ResultMessage update(T promotionPO) throws RemoteException;

    ResultMessage delete(T promotionPO) throws RemoteException;

    ArrayList<T> selectBetween(LocalDateTime begin, LocalDateTime end) throws RemoteException;
    ArrayList<T> selectByState(ReceiptState receiptState) throws RemoteException;
    ArrayList<T> search(ReceiptSearchCondition receiptSearchCondition) throws RemoteException;
}
