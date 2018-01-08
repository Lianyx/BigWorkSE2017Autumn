package blService.businessblservice;

import po.BusinessConditionPO;

import java.rmi.RemoteException;
import java.time.LocalDateTime;

public interface BusinessConditionblService {
    void insert(BusinessConditionPO businessConditionPO) throws RemoteException;
    BusinessConditionPO search(LocalDateTime begin, LocalDateTime end) throws RemoteException;
}
