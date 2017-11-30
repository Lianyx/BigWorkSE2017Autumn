package po;


import java.time.LocalDateTime;

public class TotalPromotionPO extends PromotionPO {
    private double totalAmount; // required total amount
    private double tokenAmount;
    private PromotionGoodsItemPO[] gifts;

    public TotalPromotionPO() {
    }

    public TotalPromotionPO(int id, LocalDateTime create, LocalDateTime lastModified, LocalDateTime begin, LocalDateTime end, double totalAmount, double tokenAmount, PromotionGoodsItemPO[] gifts) {
        super(id, create, lastModified, begin, end);
        this.totalAmount = totalAmount;
        this.tokenAmount = tokenAmount;
        this.gifts = gifts;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
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
