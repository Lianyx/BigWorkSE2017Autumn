package ui.managerui.common;

import java.rmi.RemoteException;

@FunctionalInterface
public interface ExceptionRunnable {
    void run() throws Exception;
}
