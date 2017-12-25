package po.promotionPO;

import po.promotionPO.PromotionPO;

import java.time.LocalDateTime;

public class MemberPromotionPO extends PromotionPO {
    private int requiredLevel; // the Required VIP Level
    private double discountFraction; // a fraction between 0 ~ 1
    private double tokenAmount;

    public MemberPromotionPO() {
    }

    public MemberPromotionPO(int dayId, LocalDateTime createTime, LocalDateTime lastModifiedTime, LocalDateTime beginTime, LocalDateTime endTime, String comment, int requiredLevel, double discountFraction, double tokenAmount) {
        super(dayId, createTime, lastModifiedTime, beginTime, endTime, comment);
        this.requiredLevel = requiredLevel;
        this.discountFraction = discountFraction;
        this.tokenAmount = tokenAmount;
    }

    public int getRequiredLevel() {
        return requiredLevel;
    }

    public void setRequiredLevel(int requiredLevel) {
        this.requiredLevel = requiredLevel;
    }

    public double getDiscountFraction() {
        return discountFraction;
    }

    public void setDiscountFraction(double discountFraction) {
        this.discountFraction = discountFraction;
    }

    public double getTokenAmount() {
        return tokenAmount;
    }

    public void setTokenAmount(double tokenAmount) {
        this.tokenAmount = tokenAmount;
    }
}
