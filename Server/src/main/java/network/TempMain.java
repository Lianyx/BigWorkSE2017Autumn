package network;

import annotations.RMIRemote;
import data.checkdata.ReceiptData;
import data.promotiondata.CombinePromotionData;
import data.promotiondata.MemberPromotionData;
import data.promotiondata.PromotionData;
import data.promotiondata.TotalPromotionData;
import data.salesdata.SalesSellReceiptData;
import mapper.CombinePromotionPOMapper;
import mapper.MemberPromotionPOMapper;
import mapper.SalesSellReceiptPOMapper;
import mapper.TotalPromotionPOMapper;
import org.reflections.Reflections;
import po.promotionPO.CombinePromotionPO;
import po.promotionPO.MemberPromotionPO;
import po.receiptPO.SalesSellReceiptPO;
import po.promotionPO.TotalPromotionPO;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.Set;

public class TempMain {
    public static void main(String[] args) {
        try {
            String registry = "localhost";
            int port = 1099;
            String registrationpre = "rmi://" + registry + ":" + port;

            LocateRegistry.createRegistry(port);

            Reflections reflections = new Reflections("data");
            Set<Class<?>> classesList = reflections.getTypesAnnotatedWith(RMIRemote.class);
            classesList.forEach(c -> {
                try {
                    Remote toBeRegistered = (Remote) c.newInstance();
                    String classFullName = c.getName();
//                    System.out.println(classFullName.substring(classFullName.lastIndexOf(".") + 1));
                    Naming.rebind(registrationpre + "/" + classFullName.substring(classFullName.lastIndexOf(".") + 1)
                            , toBeRegistered);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });


            System.out.println("server starts");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
