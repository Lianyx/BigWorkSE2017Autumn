package vo;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.Serializable;

public class ListGoodsItemVO extends RecursiveTreeObject<ListGoodsItemVO> implements Serializable {
    private StringProperty goodsName;
    private IntegerProperty goodsId;
    private StringProperty type;
    private IntegerProperty price;
     private IntegerProperty goodsNum;
     private IntegerProperty sum;
     private StringProperty comment;

    public ListGoodsItemVO(String goodsName, int goodsId, String type, int price,int goodsNum, String comment) {
        this.goodsName = new SimpleStringProperty(goodsName);
        this.goodsId =  new SimpleIntegerProperty(goodsId);
        this.type =  new SimpleStringProperty(type);
        this.price = new SimpleIntegerProperty(price);
        this.goodsNum = new SimpleIntegerProperty(goodsNum);
        this.sum = new SimpleIntegerProperty();
        this.comment = new SimpleStringProperty(comment);
        this.sum.bind(this.goodsNum.multiply(price));
    }

    public String getGoodsName() {
        return goodsName.get();
    }

    public StringProperty goodsNameProperty() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName.set(goodsName);
    }

    public int getGoodsId() {
        return goodsId.get();
    }

    public IntegerProperty goodsIdProperty() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId.set(goodsId);
    }

    public String getType() {
        return type.get();
    }

    public StringProperty typeProperty() {
        return type;
    }

    public void setType(String type) {
        this.type.set(type);
    }

    public int getPrice() {
        return price.get();
    }

    public IntegerProperty priceProperty() {
        return price;
    }

    public void setPrice(int price) {
        this.price.set(price);
    }

    public int getGoodsNum() {
        return goodsNum.get();
    }

    public IntegerProperty goodsNumProperty() {
        return goodsNum;
    }

    public void setGoodsNum(int goodsNum) {
        this.goodsNum.set(goodsNum);
    }

    public int getSum() {
        return sum.get();
    }

    public IntegerProperty sumProperty() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum.set(sum);
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
}
