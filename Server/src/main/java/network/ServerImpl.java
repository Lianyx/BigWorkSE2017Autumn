package network;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
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

    @Override
    public Object dataInteration(String classname, String method,Object object) throws Exception {
        Class klass = this.getClass();
        return null;
    }

    @Override
    public void notify(String s) throws RemoteException {

    }


    public void notifyClient() throws RemoteException{
        for(Client client:list){
            client.doSomething("");
        }
    }
    public static void main(String args[]){
        try {
            TestImpl1 testImpl1 = new TestImpl1();

            TestImpl2 testImpl2 = new TestImpl2();

            ServerImpl server = new ServerImpl();
            String registry = "localhost";
            int port=1099;
            LocateRegistry.createRegistry(port);
            String registrationpre = "rmi://" + registry +":" + port;
            String registration = registrationpre + "/Server";
            Naming.bind(registration, server);
            String test1 = registrationpre + "/TestdataService1";
            Naming.bind(test1,testImpl1);
            String test2 = registrationpre + "/TestdataService2";
            Naming.bind(test2,testImpl2);


        } catch (Exception e) {
            System.err.println ("Error - " + e);
        }
    }

}
