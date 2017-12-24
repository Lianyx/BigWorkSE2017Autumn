package vo.promotionVO;

import po.promotionPO.PromotionGoodsItemPO;
import po.promotionPO.CombinePromotionPO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

public class CombinePromotionVO extends PromotionVO {
    // TODO 应该还要对PromotionGoodsItemPO修改
    private ArrayList<PromotionGoodsItemPO> goodsCombination;
    private double discountAmount;

    public CombinePromotionVO(CombinePromotionPO promotionPO) {
        super(promotionPO);
        id = String.format("TJBCX-" + String.valueOf(createTime.getYear()) + createTime.getMonthValue() + createTime.getDayOfMonth() + "-%05d", promotionPO.getDayId());
        discountAmount = promotionPO.getDiscountAmount();
        goodsCombination = new ArrayList<>(Arrays.asList(promotionPO.getGoodsCombination()));
    }

    // for test
    public CombinePromotionVO() {

    }

    @Override // unchecked overriding…
    public CombinePromotionPO toPO() {
        return new CombinePromotionPO(Integer.parseInt(getId().substring(getId().length() - 5)),
                createTime,
                lastModifiedTime,
                beginTime,
                endTime,
                goodsCombination.toArray(new PromotionGoodsItemPO[goodsCombination.size()]),
                discountAmount);
    }

    public ArrayList<PromotionGoodsItemPO> getGoodsCombination() {
        return goodsCombination;
    }

    public void setGoodsCombination(ArrayList<PromotionGoodsItemPO> goodsCombination) {
        this.goodsCombination = goodsCombination;
    }

    public double getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(double discountAmount) {
        this.discountAmount = discountAmount;
    }

    @Override
    public String toString() {
        return "CombinePromotion";
    }

    public static void main(String[] args) {
        LocalDateTime createTime = LocalDateTime.now();
        String id = "TJBCX-" + String.valueOf(createTime.getYear()) + createTime.getMonthValue() + createTime.getDayOfMonth() + "-%05d";
        System.out.println((String.format(id, 54214)));

        System.out.println("00001".substring(3));
        System.out.println(Integer.parseInt("00001".substring(3)));

    }
}
