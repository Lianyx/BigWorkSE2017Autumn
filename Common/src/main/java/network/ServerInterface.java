package network;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerInterface extends Remote {
    // TODO 这里传入String，以后八成也还要改。
    void send(String msg) throws RemoteException;
    void addClient(ClientInterface client) throws RemoteException;
    void removeClient(ClientInterface client) throws RemoteException;
}
