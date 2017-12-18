package dataService.businessdataService;

import po.BusinessConditionPO;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface BusinessConditionDataService extends Remote {

    public List<BusinessConditionPO> select(LocalDateTime begin, LocalDateTime end)throws RemoteException;
}
