package businesslogic.promotionbl.testprmt;

import blService.businessblservice.BusinessConditionblService;
import blService.businessblservice.BusinessProgressblService;
import businesslogic.promotionbl.MyblServiceFactory;
import util.ReceiptSearchCondition;

import java.rmi.RemoteException;
import java.time.LocalDateTime;

public class TestBusinessProgress {
    public static void main(String[] args) {
        BusinessProgressblService businessProgressblService = MyblServiceFactory.getService(BusinessProgressblService.class);
        try {
            businessProgressblService.search(new ReceiptSearchCondition());
            System.out.println("ok");
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
