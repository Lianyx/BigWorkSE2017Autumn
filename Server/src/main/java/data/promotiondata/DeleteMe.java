package data.promotiondata;

import po.MemberPromotionPO;
import po.PromotionGoodsItemPO;
import po.TotalPromotionPO;

import java.time.LocalDateTime;
import java.util.List;

public class DeleteMe {
    static void testM() {
        MemberPromotionData mdao = new MemberPromotionData();

        // insert
        MemberPromotionPO mpo1 = new MemberPromotionPO();
        mpo1.setDayId(mdao.getDayId());
        mpo1.setCreateTime(LocalDateTime.now());
        mpo1.setBeginTime(LocalDateTime.of(2017, 11, 1, 0, 0));
        mpo1.setEndTime(LocalDateTime.of(2017, 12, 10, 0, 0));
        mpo1.setDiscountFraction(0.8);
        mpo1.setRequiredLevel(3);
        mpo1.setTokenAmount(5);
        mdao.insert(mpo1);


        // select
        List<MemberPromotionPO> ml2 = mdao.selectInEffect();
        ml2.forEach(m-> System.out.println(m.getDayId()));


        // getDayId
        System.out.println(mdao.getDayId());



        System.out.println("finish testM");
    }

    static void testT() {
        TotalPromotionData tdao = new TotalPromotionData();

        // insert. (insert twice in a row. then commented)
//        TotalPromotionPO tpo1 = new TotalPromotionPO();
//        tpo1.setDayId(tdao.getDayId());
//        tpo1.setCreateTime(LocalDateTime.now());
//        tpo1.setBeginTime(LocalDateTime.of(2017, 11, 1, 0, 0));
//        tpo1.setEndTime(LocalDateTime.of(2017, 12, 10, 0, 0));
//        tpo1.setRequiredTotal(100);
//        tpo1.setTokenAmount(5);
//        tpo1.setGifts(new PromotionGoodsItemPO[]{new PromotionGoodsItemPO(0, 10), new PromotionGoodsItemPO(1, 2)});
//
//        tdao.insert(tpo1);


        // select. (de-comment after insert)
//        List<TotalPromotionPO> ml2 = tdao.selectInEffect();
//
//        TotalPromotionPO tpo2 = ml2.get(1);
//
//        // test array storing
//        System.out.println("id" + tpo2.getGifts()[0].getId());
//
//        // update
//        tpo2.setRequiredTotal(200);
//        tdao.update(tpo2);
//
//        // delete
//        TotalPromotionPO tpo0 = ml2.get(0);
//        tdao.delete(tpo0);
//
//        // getDayId
//        System.out.println(tdao.getDayId());
//
//
//        System.out.println("finish testM");
    }

    static void testC() {

    }

    public static void main(String[] args) {
//        testM();
        testT();
    }
}
