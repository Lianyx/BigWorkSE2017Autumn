package businesslogic.blServiceFactory;

import network.ClientInterface;
import network.ClientObject;
import network.ServerInterface;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class MessageObjectFactory {
    private static ServerInterface serverInterface;

    public synchronized static ServerInterface getServerInterface() throws RemoteException, NotBoundException, MalformedURLException {
        if (serverInterface == null) {
            serverInterface = (ServerInterface) Naming.lookup("rmi://localhost:1099/ServerObject");
            // TODO 还需要把用户的信息传进去的
            ClientInterface clientObject = new ClientObject();
            serverInterface.addClient(clientObject);
            return serverInterface;
        }
        return serverInterface;
    }
}
