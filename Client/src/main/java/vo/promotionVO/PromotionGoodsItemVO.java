package vo.promotionVO;

import po.promotionPO.PromotionGoodsItemPO;

public class PromotionGoodsItemVO {
    private int id;
    private String name;
    private int num;

    public PromotionGoodsItemVO(){
    }

    public PromotionGoodsItemVO(PromotionGoodsItemPO promotionGoodsItemPO) {
        id = promotionGoodsItemPO.getId();
        // TODO 需要使用service
        name = String.valueOf(promotionGoodsItemPO.getId());
        num = promotionGoodsItemPO.getNum();
    }

    public PromotionGoodsItemPO toPO() {
        return new PromotionGoodsItemPO(id, num);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
