package blService;
import po.PromotionPO;
public interface PromotionblService {
    public void init();
    public void showDetail(PromotionPO promotionPO);
    public void add(PromotionPO promotionPO);
    public void modify(PromotionPO promotionPO);
    public void delete(PromotionPO promotionPO);
}
