package network;

import javafx.application.Platform;
import ui.common.dialog.MyOneButtonDialog;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ClientObject extends UnicastRemoteObject implements ClientInterface{
    public ClientObject() throws RemoteException {
    }

    @Override
    public String getClientInfo() throws RemoteException {
        return "先随便写吧";
    }

    @Override
    public void receive(String msg) throws RemoteException {
        Platform.runLater(() -> {
            new MyOneButtonDialog("收到消息\n" + msg).show();
        });
    }
}
