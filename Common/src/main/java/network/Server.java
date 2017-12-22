package network;

import com.sun.org.apache.regexp.internal.RE;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Server extends Remote{
    void addClient(Client client) throws RemoteException;
    void removeClient(Client client) throws RemoteException;
    public Object dataInteration(String classname,String method,Object object) throws Exception;
    void notify(String s) throws RemoteException;
}
