package data.salesdata;

import data.checkdata.ReceiptData;
import po.ReceiptGoodsItemPO;
import po.SalesSellReceiptPO;
import util.ReceiptState;

import java.rmi.RemoteException;
import java.time.LocalDateTime;

public class SimpleTest {


    public static void main(String[] args) throws RemoteException{
        ReceiptData<SalesSellReceiptPO> rdao = new ReceiptData<>(SalesSellReceiptPOMapper.class);


        // insert
        SalesSellReceiptPO ssrp = new SalesSellReceiptPO();
        ssrp.setDayId(rdao.getDayId());
        ssrp.setOperatorId(1);
        ssrp.setReceiptState(ReceiptState.PENDING);
        ssrp.setClerkName("Wang Er Xiao");
        ssrp.setGoodsList(new ReceiptGoodsItemPO[]{new ReceiptGoodsItemPO(1, 1, 2, "first")});
        ssrp.setOriginSum(100);
        ssrp.setComment("insert");

        rdao.insert(ssrp);

        System.out.println("insert: ");
        System.out.println(ssrp);
        System.out.println();


        // get by state and update
        SalesSellReceiptPO ssrpA = rdao.selectByState(ReceiptState.PENDING).get(0);
        ssrpA.setComment("get and update");

        rdao.update(ssrpA);

        System.out.println("get and update: ");
        System.out.println(ssrpA);
        System.out.println();


        // get by time span and delete
        LocalDateTime begin = LocalDateTime.now().minusDays(1);
        LocalDateTime end = LocalDateTime.now().plusDays(2);

        SalesSellReceiptPO ssrpB = rdao.selectBetween(begin, end).get(0);

        rdao.delete(ssrpB);

        System.out.println("get and delete: ");
        System.out.println(ssrpB);

        // prove delete
        System.out.println(rdao.getDayId());
        // TODO 为什么这个test跑完以后好像还是没有停？

    }
}
