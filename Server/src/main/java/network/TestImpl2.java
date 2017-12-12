package network;

import dataService.test.TestdataService2;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class TestImpl2 extends UnicastRemoteObject implements TestdataService2 {
    protected TestImpl2() throws RemoteException {
        super();
    }
    @Override
    public String test() throws RemoteException {
        return "This is data 2";
    }
}
