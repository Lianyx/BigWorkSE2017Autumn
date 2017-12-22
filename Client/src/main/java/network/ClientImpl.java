package network;


import dataService.test.TestdataService1;
import dataService.test.TestdataService2;
import ui.mainui.Test;

import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

public class ClientImpl extends UnicastRemoteObject implements Client{


    static Server server;

    protected ClientImpl() throws RemoteException {

    }


    @Override
    public void doSomething(String s) throws RemoteException {
        System.out.println(s);

    }


    public Object dataInteration(String klassname,String method,Object object) throws Exception{
        return server.dataInteration(klassname,method,object);
    }




    public static void main(String args[]){
        try
        {
            String registry = "localhost";
            int port=1099;
            String registration = "rmi://" + registry + ":" + port + "/Server";

            Remote remoteService = Naming.lookup ( registration );
            server = (Server) remoteService;

            Client client = new ClientImpl();
            server.addClient(client);
            server.notify("hahaha");
            TestdataService1 testdataService1 = (TestdataService1) Naming.lookup("rmi://" + registry + ":" + port + "/TestdataService1");
            String test1 = testdataService1.test();
            System.out.println(test1);
            TestdataService2 testdataService2 = (TestdataService2) Naming.lookup("rmi://" + registry + ":" + port + "/TestdataService2");
            String test2 = testdataService2.test();
            System.out.println(test2);

        } catch (Exception e) {
            e.printStackTrace();
        }
}
}
