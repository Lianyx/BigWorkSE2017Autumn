package po;

public class PromotionGoodsItemPO {
    private String id;
    private int num;

    public PromotionGoodsItemPO(String id, int num) {
        this.id = id;
        this.num = num;
    }

    public PromotionGoodsItemPO() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}