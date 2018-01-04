package vo.inventoryVO.uiReceipt;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

import java.io.Serializable;

public class DamageuiGoodsItemVO extends RecursiveTreeObject<DamageuiGoodsItemVO> implements Serializable,Comparable<DamageuiGoodsItemVO> {
    private StringProperty goodName;
    private StringProperty goodId;
    private IntegerProperty inventoryNum;
    private IntegerProperty factNum;
    private DoubleProperty goodPrice;
    private StringProperty comment;

    public DamageuiGoodsItemVO(StringProperty goodName, StringProperty goodId, IntegerProperty inventoryNum,
                               IntegerProperty factNum, DoubleProperty goodPrice, StringProperty comment) {
        this.goodName = goodName;
        this.goodId = goodId;
        this.inventoryNum = inventoryNum;
        this.factNum = factNum;
        this.goodPrice = goodPrice;
        this.comment = comment;
    }

    public String getGoodName() {
        return goodName.get();
    }

    public StringProperty goodNameProperty() {
        return goodName;
    }

    public void setGoodName(String goodName) {
        this.goodName.set(goodName);
    }

    public String getGoodId() {
        return goodId.get();
    }

    public StringProperty goodIdProperty() {
        return goodId;
    }

    public void setGoodId(String goodId) {
        this.goodId.set(goodId);
    }

    public int getInventoryNum() {
        return inventoryNum.get();
    }

    public IntegerProperty inventoryNumProperty() {
        return inventoryNum;
    }

    public void setInventoryNum(int inventoryNum) {
        this.inventoryNum.set(inventoryNum);
    }

    public int getFactNum() {
        return factNum.get();
    }

    public IntegerProperty factNumProperty() {
        return factNum;
    }

    public void setFactNum(int factNum) {
        this.factNum.set(factNum);
    }

    public double getGoodPrice() {
        return goodPrice.get();
    }

    public DoubleProperty goodPriceProperty() {
        return goodPrice;
    }

    public void setGoodPrice(double goodPrice) {
        this.goodPrice.set(goodPrice);
    }

    public String getComment() {
        return comment.get();
    }

    public StringProperty commentProperty() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment.set(comment);
    }

    @Override
    public int compareTo(DamageuiGoodsItemVO o) {
        return goodId.get().compareTo(o.goodId.get());
    }
}
