package vo.promotionVO;

import blService.promotionblService.PromotionblService;
import businesslogic.promotionbl.PromotionFactory;
import po.promotionPO.PromotionGoodsItemPO;
import po.promotionPO.CombinePromotionPO;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class CombinePromotionVO extends PromotionVO {
    private ArrayList<PromotionGoodsItemVO> goodsCombination;
    private double discountAmount;

    public CombinePromotionVO(CombinePromotionPO promotionPO) {
        super(promotionPO);
        id = formatId("TJBCX", promotionPO);

        discountAmount = promotionPO.getDiscountAmount();
        goodsCombination = Arrays.stream(promotionPO.getGoodsCombination())
                .map(PromotionGoodsItemVO::new)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    // for test
    public CombinePromotionVO() {

    }

    @Override // unchecked overriding…
    public CombinePromotionPO toPO() {
        return new CombinePromotionPO(idToDayId(),
                createTime,
                lastModifiedTime,
                beginTime,
                endTime,
                comment,
                goodsCombination.stream().map(PromotionGoodsItemVO::toPO).toArray(PromotionGoodsItemPO[]::new),
                discountAmount);
    }

    @Override
    public PromotionblService getService() throws RemoteException, NotBoundException, MalformedURLException {
        return PromotionFactory.getCombinePromotionblSerivce();
    }

    public ArrayList<PromotionGoodsItemVO> getGoodsCombination() {
        return goodsCombination;
    }

    public void setGoodsCombination(ArrayList<PromotionGoodsItemVO> goodsCombination) {
        this.goodsCombination = goodsCombination;
    }

    public double getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(double discountAmount) {
        this.discountAmount = discountAmount;
    }

//    @Override
//    public String toString() {
//        return "CombinePromotion";
//    }

//    public static void main(String[] args) {
//        LocalDateTime createTime = LocalDateTime.now();
//        String id = "TJBCX-" + String.valueOf(createTime.getYear()) + createTime.getMonthValue() + createTime.getDayOfMonth() + "-%05d";
//        System.out.println((String.format(id, 54214)));
//
//        System.out.println("00001".substring(3));
//        System.out.println(Integer.parseInt("00001".substring(3)));
//
//    }
}
