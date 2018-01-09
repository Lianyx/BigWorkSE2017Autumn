package dataService.userdataService;

import po.UserPO;
import util.RespectiveReceiptSearchCondition;
import util.ResultMessage;
import util.UserSearchCondition;


import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Set;

public interface UserDataService extends Remote {
    public int getId() throws RemoteException;
    public ResultMessage insert(UserPO UserPO) throws RemoteException;
    public ResultMessage delete(int id) throws RemoteException;
    public ResultMessage update(UserPO UserPO) throws RemoteException;
    public UserPO showDetail(int id) throws RemoteException;
    public ArrayList<UserPO> search(UserSearchCondition userSearchCondition) throws RemoteException;
    public UserPO checkPassword(String username,String password) throws RemoteException;

}
