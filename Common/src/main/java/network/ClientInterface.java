package network;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientInterface extends Remote {
    // TODO 这个getClientInfo我猜迟早要改
    String getClientInfo() throws RemoteException;

    void receive(String msg) throws RemoteException;
}
