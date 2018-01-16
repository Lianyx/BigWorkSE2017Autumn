package network;

import annotations.RMIRemote;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

@RMIRemote
public class ServerObject extends UnicastRemoteObject implements ServerInterface{
    private ArrayList<ClientInterface> clients = new ArrayList<>();
//    private ClientInterface client;

    public ServerObject() throws RemoteException {
    }

    @Override
    public void send(String msg) throws RemoteException {
        for (ClientInterface client: clients) {
            client.receive(msg);
        }
    }

    @Override
    public void addClient(ClientInterface client) throws RemoteException {
        clients.add(client);
//        this.client = client;
    }

    @Override
    public void removeClient(ClientInterface client) throws RemoteException {
        clients.remove(client);
    }
}
