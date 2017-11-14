package po;

import java.util.Date;

public abstract class PromotionPO {
    private Date begin;
    private Date end;

    public PromotionPO(Date begin, Date end) {
        this.begin = begin;
        this.end = end;
    }
}
