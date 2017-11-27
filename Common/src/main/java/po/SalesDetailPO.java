package po;

public class SalesDetailPO {
    private String date;
    private int goodsID;
    private int number;
    private double price;
    private double total;
    private String goodsName;

    public SalesDetailPO(String date, String goodsName, int goodsID, int number, double price, double total) {
        this.date = date;
        this.goodsName = goodsName;
        this.goodsID = goodsID;
        this.number = number;
        this.price = price;
        this.total = total;
    }



    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public int getGoodsID() {
        return goodsID;
    }

    public void setGoodsID(int goodsID) {
        this.goodsID = goodsID;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }


}
