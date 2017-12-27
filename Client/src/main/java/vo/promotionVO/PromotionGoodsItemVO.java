package vo.promotionVO;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import po.promotionPO.PromotionGoodsItemPO;

public class PromotionGoodsItemVO extends RecursiveTreeObject<PromotionGoodsItemVO> {
    private String id;
    private String name;
    private int num;
    private int unitPrice;

    public PromotionGoodsItemVO(){
    }

    public PromotionGoodsItemVO(PromotionGoodsItemPO promotionGoodsItemPO) {
        id = promotionGoodsItemPO.getId();
        // TODO 需要使用service
        name = String.valueOf(promotionGoodsItemPO.getId());
        unitPrice = 50;

        num = promotionGoodsItemPO.getNum();
    }

    public PromotionGoodsItemPO toPO() {
        return new PromotionGoodsItemPO(id, num);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public int getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(int unitPrice) {
        this.unitPrice = unitPrice;
    }
}
