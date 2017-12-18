package data.salesdata;

import data.checkdata.ReceiptData;
import mapper.SalesSellReceiptPOMapper;
import po.receiptPO.SalesSellReceiptPO;

import java.rmi.RemoteException;

public class SimpleTest {

    public static void test() throws RemoteException {
        ReceiptData<SalesSellReceiptPO> rdao = new ReceiptData<>(SalesSellReceiptPOMapper.class);


        // insert
//        SalesSellReceiptPO ssrp = new SalesSellReceiptPO();
//        ssrp.setDayId(rdao.getDayId());
//        ssrp.setOperatorId(1);
//        ssrp.setReceiptState(ReceiptState.PENDING);
//        ssrp.setClerkName("Wang Er Xiao");
//        ssrp.setGoodsList(new ReceiptGoodsItemPO[]{new ReceiptGoodsItemPO(1, 1, 2, "first")});
//        ssrp.setOriginSum(100);
//        ssrp.setComment("insert");
//        ssrp.setGifts(new PromotionGoodsItemPO[]{new PromotionGoodsItemPO(0, 10), new PromotionGoodsItemPO(1, 2)});
//
//        rdao.insert(ssrp);
//
//        System.out.println("insert: ");
//        System.out.println(ssrp);
//        System.out.println();


        // get by state and update
//        SalesSellReceiptPO ssrpA = rdao.selectByState(ReceiptState.PENDING).get(0);
//        ssrpA.setComment("get and update");
//
//        rdao.update(ssrpA);
//
//        System.out.println("get and update: ");
//        System.out.println(ssrpA);
//        System.out.println();
//
//
//        // get by time span and delete
//        LocalDateTime begin = LocalDateTime.now().minusDays(1);
//        LocalDateTime end = LocalDateTime.now().plusDays(2);
//
////        SalesSellReceiptPO ssrpB = rdao.selectBetween(begin, end).get(0);
//
//        SalesSellReceiptPO ssrpC = rdao.search(new ReceiptSearchCondition(null, LocalDateTime.now(), null, "Wang Er Xiao", null, null)).get(0);
//
//        System.out.println("get and delete: ");
//        System.out.println(ssrpC);
//
//        // delete
////        rdao.delete(ssrpC);
//
//
//
//        // prove delete
//        System.out.println(rdao.getDayId());
    }


    public static void main(String[] args) throws RemoteException{

        // TODO 为什么这个test跑完以后好像还是没有停？
        test();
        System.out.println("the end");
    }
}
