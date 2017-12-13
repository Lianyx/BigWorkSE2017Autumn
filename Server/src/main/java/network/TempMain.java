package network;

import data.checkdata.ReceiptData;
import data.salesdata.SalesSellReceiptPOMapper;
import po.SalesSellReceiptPO;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class TempMain {
    public static void main(String[] args) {
        try {
            String registry = "localhost";
            int port = 1099;
            String registrationpre = "rmi://" + registry + ":" + port;


            ReceiptData<SalesSellReceiptPO> salesSellReceiptData = new ReceiptData<SalesSellReceiptPO>(SalesSellReceiptPOMapper.class);

            LocateRegistry.createRegistry(port);

            Naming.rebind(registrationpre + "/SalesSellReceiptData", salesSellReceiptData);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
