package vo.inventoryVO.uiReceipt;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.*;

import java.io.Serializable;

public class GiftuiGoodsItemVO extends RecursiveTreeObject<GiftuiGoodsItemVO> implements Serializable,Comparable<GiftuiGoodsItemVO> {
    private StringProperty goodName;
    private StringProperty goodId;
    private IntegerProperty inventoryNum;
    private IntegerProperty sendNum;
    private DoubleProperty goodPrice;
    private StringProperty comment;

    public GiftuiGoodsItemVO(String goodName, String goodId,Integer inventoryNum, Integer sendNum, Double goodPrice, String comment) {
        this.goodName = new SimpleStringProperty(goodName);
        this.goodId = new SimpleStringProperty(goodId);
        this.inventoryNum = new SimpleIntegerProperty(inventoryNum);
        this.sendNum = new SimpleIntegerProperty(sendNum);
        this.goodPrice = new SimpleDoubleProperty(goodPrice);
        this.comment = new SimpleStringProperty(comment);
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

    public int getSendNum() {
        return sendNum.get();
    }

    public IntegerProperty sendNumProperty() {
        return sendNum;
    }

    public void setSendNum(int sendNum) {
        this.sendNum.set(sendNum);
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
    public int compareTo(GiftuiGoodsItemVO o) {
        return this.getGoodId().compareTo(o.getGoodId());
    }
}
