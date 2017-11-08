package network;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface MessageRemote extends Remote{
    public String echo(String name, String message) throws RemoteException;
    public void send(String name, String message) throws RemoteException;
    public void registerCallback(ReCall callback) throws RemoteException;
    public void removeCallBack(ReCall c) throws RemoteException;



}
