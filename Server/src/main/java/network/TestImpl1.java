package network;

import dataService.test.TestdataService1;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class TestImpl1 extends UnicastRemoteObject implements TestdataService1 {
    protected TestImpl1() throws RemoteException {
        super();
    }
    @Override
    public String test() throws RemoteException {
        return "This is data 1";
    }
}
