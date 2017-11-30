package po;


import java.time.LocalDateTime;

public class TotalPromotionPO extends PromotionPO {
    private double requiredTotal;
    private double tokenAmount;
    private PromotionGoodsItemPO[] gifts;

    public TotalPromotionPO() {
    }

    public TotalPromotionPO(int id, LocalDateTime create, LocalDateTime lastModified, LocalDateTime begin, LocalDateTime end, double requiredTotal, double tokenAmount, PromotionGoodsItemPO[] gifts) {
        super(id, create, lastModified, begin, end);
        this.requiredTotal = requiredTotal;
        this.tokenAmount = tokenAmount;
        this.gifts = gifts;
    }

    public double getRequiredTotal() {
        return requiredTotal;
    }

    public void setRequiredTotal(double requiredTotal) {
        this.requiredTotal = requiredTotal;
    }

    public double getTokenAmount() {
        return tokenAmount;
    }

    public void setTokenAmount(double tokenAmount) {
        this.tokenAmount = tokenAmount;
    }

    public PromotionGoodsItemPO[] getGifts() {
        return gifts;
    }

    public void setGifts(PromotionGoodsItemPO[] gifts) {
        this.gifts = gifts;
    }
}
