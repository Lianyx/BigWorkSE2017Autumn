package businesslogic.promotionbl.testprmt;

import businesslogic.promotionbl.CombinePromotionbl;
import po.promotionPO.PromotionGoodsItemPO;
import po.promotionPO.CombinePromotionPO;
import vo.promotionVO.CombinePromotionVO;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class TestPromotionbl {
    public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException {
        CombinePromotionbl combinePromotionbl = new CombinePromotionbl();

        // test. first po to vo, then vo to po
        CombinePromotionPO cpo1 = new CombinePromotionPO();
        cpo1.setDayId(combinePromotionbl.getDayId());
        cpo1.setCreateTime(LocalDateTime.now());
        cpo1.setBeginTime(LocalDateTime.of(2017, 11, 1, 0, 0));
        cpo1.setEndTime(LocalDateTime.of(2100, 12, 30, 0, 0));
        cpo1.setDiscountAmount(40);
        cpo1.setGoodsCombination(new PromotionGoodsItemPO[]{new PromotionGoodsItemPO("0", 10), new PromotionGoodsItemPO("1", 2)});

        CombinePromotionVO cv1 = new CombinePromotionVO(cpo1);

        combinePromotionbl.insert(cv1);

        // test po to vo with reflection
        ArrayList<CombinePromotionVO> cs = combinePromotionbl.selectInEffect();
        cs.forEach(System.out::println);

    }
}
