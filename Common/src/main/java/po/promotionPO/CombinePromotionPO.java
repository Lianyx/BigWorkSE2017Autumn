package po.promotionPO;

import po.PromotionGoodsItemPO;
import po.promotionPO.PromotionPO;

import java.time.LocalDateTime;

public class CombinePromotionPO extends PromotionPO {
    private PromotionGoodsItemPO[] goodsCombination;
    private double discountAmount;

    public CombinePromotionPO() {
    }

    public CombinePromotionPO(int id, LocalDateTime create, LocalDateTime lastModified, LocalDateTime begin, LocalDateTime end, PromotionGoodsItemPO[] goodsCombination, double discountAmount) {
        super(id, create, lastModified, begin, end);
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
