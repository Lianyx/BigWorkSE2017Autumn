package network;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ReCall extends Remote {
    public void newMessage(String message) throws RemoteException;
}
