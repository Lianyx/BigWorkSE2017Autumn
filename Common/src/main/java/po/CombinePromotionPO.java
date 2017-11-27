package po;

import java.util.ArrayList;
import java.util.Date;

public class CombinePromotionPO extends PromotionPO {
    private ArrayList<GoodsPO> goodsList; // TODO seem inappropriate
    private double discournt; // 折让数额

    public CombinePromotionPO(Date begin, Date end, ArrayList<GoodsPO> goodsList, double discournt) {
        super(begin, end);
        this.goodsList = goodsList;
        this.discournt = discournt;
    }
}
