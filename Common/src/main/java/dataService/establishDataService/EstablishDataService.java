package dataService.establishDataService;

import util.ResultMessage;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface EstablishDataService extends Remote {
    public ResultMessage init()throws RemoteException;
}
