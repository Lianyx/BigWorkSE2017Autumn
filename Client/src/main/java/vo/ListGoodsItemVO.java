package vo;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.*;
import po.ReceiptGoodsItemPO;

import java.io.Serializable;

public class ListGoodsItemVO extends RecursiveTreeObject<ListGoodsItemVO> implements Serializable {
    private StringProperty goodsName = new SimpleStringProperty();
    private IntegerProperty goodsId = new SimpleIntegerProperty();
    private StringProperty type = new SimpleStringProperty();
    private DoubleProperty price = new SimpleDoubleProperty();
     private IntegerProperty goodsNum = new SimpleIntegerProperty();
     private DoubleProperty sum = new SimpleDoubleProperty();
     private StringProperty comment = new SimpleStringProperty();

    public ListGoodsItemVO(String goodsName, int goodsId, String type, int price,int goodsNum, String comment) {
        this.goodsName = new SimpleStringProperty(goodsName);
        this.goodsId =  new SimpleIntegerProperty(goodsId);
        this.type =  new SimpleStringProperty(type);
        this.price = new SimpleDoubleProperty(price);
        this.goodsNum = new SimpleIntegerProperty(goodsNum);
        this.sum = new SimpleDoubleProperty();
        this.comment = new SimpleStringProperty(comment);
        this.sum.bind(this.goodsNum.multiply(price));
    }


    public ListGoodsItemVO(ReceiptGoodsItemPO receiptGoodsItemPO){
        this.goodsId.set(receiptGoodsItemPO.getId());
        this.price.set(receiptGoodsItemPO.getPrice());
        this.goodsNum.set(receiptGoodsItemPO.getNum());
        this.comment.set(receiptGoodsItemPO.getComment());
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

    public int getGoodsNum() {
        return goodsNum.get();
    }

    public IntegerProperty goodsNumProperty() {
        return goodsNum;
    }

    public void setGoodsNum(int goodsNum) {
        this.goodsNum.set(goodsNum);
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

    public double getPrice() {
        return price.get();
    }

    public DoubleProperty priceProperty() {
        return price;
    }

    public void setPrice(double price) {
        this.price.set(price);
    }

    public double getSum() {
        return sum.get();
    }

    public DoubleProperty sumProperty() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum.set(sum);
    }

    public ReceiptGoodsItemPO toPo(){
        return new ReceiptGoodsItemPO(this.goodsId.get(),this.goodsNum.get(),this.price.get(),this.comment.get());
    }

}
