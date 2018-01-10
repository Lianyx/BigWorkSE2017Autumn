package businesslogic.promotionbl.testprmt;

import blService.businessblservice.BusinessProgressblService;
import businesslogic.checkbl.MyServiceFactory;
import businesslogic.promotionbl.MyblServiceFactory;

public class TestSundry {
    public static void main(String[] args) {
        BusinessProgressblService b = MyblServiceFactory.getService(BusinessProgressblService.class);
        System.out.println(b);
    }
}
