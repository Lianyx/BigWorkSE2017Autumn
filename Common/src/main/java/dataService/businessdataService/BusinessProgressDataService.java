package dataService.businessdataService;


import po.receiptPO.ReceiptPO;
import util.BusinessProgressInfo;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface BusinessProgressDataService extends Remote {

    public List<ReceiptPO> select(BusinessProgressInfo businessProgressInfo)throws RemoteException;
}
