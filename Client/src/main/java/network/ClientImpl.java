package network;


import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

public class ClientImpl extends UnicastRemoteObject implements Client{


    protected ClientImpl() throws RemoteException {
    }

    @Override
    public void doSomething() throws RemoteException {
         System.out.println("hahaha");
    }

    public static void main(String args[]){
        try
        {
            String registry = "localhost";
            int port=1099;
            String registration = "rmi://" + registry + ":" + port + "/Server";

            Remote remoteService = Naming.lookup ( registration );
            Server server = (Server) remoteService;
            Client client = new ClientImpl();
            server.addClient(client);

        } catch (Exception e) {
            e.printStackTrace();
        }
}
}
