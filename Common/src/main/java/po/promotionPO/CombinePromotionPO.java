package po.promotionPO;

import java.time.LocalDateTime;

public class CombinePromotionPO extends PromotionPO {
    private PromotionGoodsItemPO[] goodsCombination;
    private double discountAmount;

    public CombinePromotionPO() {
    }

    public CombinePromotionPO(int dayId, LocalDateTime createTime, LocalDateTime lastModifiedTime, LocalDateTime beginTime, LocalDateTime endTime, String comment, PromotionGoodsItemPO[] goodsCombination, double discountAmount) {
        super(dayId, createTime, lastModifiedTime, beginTime, endTime, comment);
        this.goodsCombination = goodsCombination;
        this.discountAmount = discountAmount;
    }

    public PromotionGoodsItemPO[] getGoodsCombination() {
        return goodsCombination;
    }

    public void setGoodsCombination(PromotionGoodsItemPO[] goodsCombination) {
        this.goodsCombination = goodsCombination;
    }

    public double getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(double discountAmount) {
        this.discountAmount = discountAmount;
    }
}
