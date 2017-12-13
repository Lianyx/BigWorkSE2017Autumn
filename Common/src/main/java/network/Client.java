package network;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Client extends Remote {
    void doSomething() throws RemoteException;
    public Object dataInteration(String classname,String method,Object object) throws Exception;
}
