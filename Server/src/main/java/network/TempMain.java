package network;

import data.checkdata.ReceiptData;
import data.promotiondata.PromotionData;
import mapper.CombinePromotionPOMapper;
import mapper.MemberPromotionPOMapper;
import mapper.SalesSellReceiptPOMapper;
import mapper.TotalPromotionPOMapper;
import po.promotionPO.CombinePromotionPO;
import po.promotionPO.MemberPromotionPO;
import po.receiptPO.SalesSellReceiptPO;
import po.promotionPO.TotalPromotionPO;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class TempMain {
    public static void main(String[] args) {
        try {
            String registry = "localhost";
            int port = 1099;
            String registrationpre = "rmi://" + registry + ":" + port;


            ReceiptData<SalesSellReceiptPO> salesSellReceiptData = new ReceiptData<>(SalesSellReceiptPOMapper.class);
            PromotionData<CombinePromotionPO> combinePromotionData = new PromotionData<>(CombinePromotionPOMapper.class);
            PromotionData<TotalPromotionPO> totalPromotionData = new PromotionData<>(TotalPromotionPOMapper.class);
            PromotionData<MemberPromotionPO> memberPromotionData = new PromotionData<>(MemberPromotionPOMapper.class);

            LocateRegistry.createRegistry(port);

            Naming.rebind(registrationpre + "/SalesSellReceiptData", salesSellReceiptData);
            Naming.rebind(registrationpre + "/CombinePromotionData", combinePromotionData);
            Naming.rebind(registrationpre + "/TotalPromotionData", totalPromotionData);
            Naming.rebind(registrationpre + "/MemberPromotionData", memberPromotionData);


            System.out.println("server starts");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
