package vo.promotionVO;

import blService.promotionblService.PromotionblService;
import businesslogic.promotionbl.PromotionFactory;
import po.promotionPO.PromotionGoodsItemPO;
import po.promotionPO.CombinePromotionPO;
import ui.managerui.promotionui.PromotionDetailPane;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class CombinePromotionVO extends PromotionVO {
    private ArrayList<PromotionGoodsItemVO> goodsCombination;
    private double discountAmount;

    public CombinePromotionVO() {
    }
    public CombinePromotionVO(CombinePromotionPO promotionPO) {
        super(promotionPO);

        discountAmount = promotionPO.getDiscountAmount();
        goodsCombination = Arrays.stream(promotionPO.getGoodsCombination())
                .map(PromotionGoodsItemVO::new)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    protected String getCodeName() {
        return "TJBCX";
    }

    @Override // unchecked overriding…
    public CombinePromotionPO toPO() {
        CombinePromotionPO result = toPromotionPO(CombinePromotionPO.class);
        result.setDiscountAmount(discountAmount);
        result.setGoodsCombination(goodsCombination.stream().map(PromotionGoodsItemVO::toPO).toArray(PromotionGoodsItemPO[]::new));
        return result;
    }

    @Override
    public PromotionblService getService() throws RemoteException, NotBoundException, MalformedURLException {
        return PromotionFactory.getCombinePromotionblSerivce();
    }

    @Override
    public PromotionDetailPane getDetailPane() {
        return null;
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
