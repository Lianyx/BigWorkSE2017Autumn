package po;

import java.io.Serializable;

public class ReceiptGoodsItemPO implements Serializable {
    private int id;
    private int num;
    private double price; // 金额（num * price）没必要存在数据库里吧
    private String comment;

    public ReceiptGoodsItemPO() {
    }

    public ReceiptGoodsItemPO(int id, int num, double price, String comment) {
        this.id = id;
        this.num = num;
        this.price = price;
        this.comment = comment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
