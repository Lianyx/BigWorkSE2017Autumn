package po;

import java.util.Date;

public class MemberPromotionPO extends PromotionPO {
    private int level;
    private double discournt; // 0 ~ 1的分数
    private double tokenAmount;

    public MemberPromotionPO(Date begin, Date end, int level, double discournt, double tokenAmount) {
        super(begin, end);
        this.level = level;
        this.discournt = discournt;
        this.tokenAmount = tokenAmount;
    }
}
