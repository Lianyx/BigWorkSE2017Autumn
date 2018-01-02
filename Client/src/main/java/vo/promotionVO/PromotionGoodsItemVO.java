package vo.promotionVO;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import po.promotionPO.PromotionGoodsItemPO;

public class PromotionGoodsItemVO extends RecursiveTreeObject<PromotionGoodsItemVO> {
    private String id;
    private String name;
    private int unitPrice;
    private IntegerProperty num;

    public PromotionGoodsItemVO(){
    }

    public PromotionGoodsItemVO(PromotionGoodsItemPO promotionGoodsItemPO) {
        id = promotionGoodsItemPO.getId();
        // TODO 需要使用service
        name = String.valueOf(promotionGoodsItemPO.getId());
        unitPrice = 50;

        num = new SimpleIntegerProperty(promotionGoodsItemPO.getNum());
    }

    public PromotionGoodsItemVO(String id, String name, int unitPrice, IntegerProperty num) {
        this.id = id;
        this.name = name;
        this.unitPrice = unitPrice;
        this.num = num;
    }

    public PromotionGoodsItemPO toPO() {
        return new PromotionGoodsItemPO(id, num.get());
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

    public int getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(int unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getNum() {
        return num.get();
    }

    public IntegerProperty numProperty() {
        return num;
    }

    public void setNum(int num) {
        this.num.set(num);
    }
}
