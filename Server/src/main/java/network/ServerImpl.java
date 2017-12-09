package network;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashSet;

public class ServerImpl extends UnicastRemoteObject implements Server {

    private ArrayList<Client> list = new ArrayList<>();

    protected ServerImpl() throws RemoteException {
    }

    @Override
    public void addClient(Client client) throws RemoteException {
         list.add(client);
         notifyClient();
    }

    @Override
    public void removeClient(Client client) throws RemoteException {
        list.add(client);
    }


    public void notifyClient() throws RemoteException{
        for(Client client:list){
            client.doSomething();
        }
    }
    public static void main(String args[]){
        try {
            ServerImpl server = new ServerImpl();
            String registry = "localhost";
            int port=1099;
            LocateRegistry.createRegistry(port);
            String registration = "rmi://" + registry +":" + port + "/Server";
            Naming.rebind(registration, server);

        } catch (Exception e) {
            System.err.println ("Error - " + e);
        }
    }

}
