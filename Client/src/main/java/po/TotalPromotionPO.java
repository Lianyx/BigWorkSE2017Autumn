package po;

import po.InventoryManagerPO.GoodsPO;
import po.PromotionPO;

import java.util.Date;

public class TotalPromotionPO extends PromotionPO{
    private double totalAmount;
    private double tokenAmount;
    private GoodsPO gift; // TODO inappropriate

    public TotalPromotionPO(Date begin, Date end, double totalAmount, double tokenAmount, GoodsPO gift) {
        super(begin, end);
        this.totalAmount = totalAmount;
        this.tokenAmount = tokenAmount;
        this.gift = gift;
    }
}
