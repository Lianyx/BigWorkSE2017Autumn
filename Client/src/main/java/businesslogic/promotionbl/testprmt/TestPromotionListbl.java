package businesslogic.promotionbl.testprmt;

import blService.promotionblService.PromotionListblService;
import businesslogic.promotionbl.PromotionListbl;
import vo.promotionVO.PromotionVO;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class TestPromotionListbl {
    public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException {
        PromotionListblService promotionListblService = new PromotionListbl();
        ArrayList<PromotionVO> ps = promotionListblService.initPromotion();

        // test init / search
        ps.forEach(x -> System.out.println(x.getId()));
        System.out.println();

        // test delete
        PromotionVO p1 = ps.get(0);
        promotionListblService.delete(p1);
        System.out.println("delete");
    }
}
