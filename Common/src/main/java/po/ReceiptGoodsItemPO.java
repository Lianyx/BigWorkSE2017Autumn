package po;

public class ReceiptGoodsItemPO {
    private int id;
    // 先暂时只有编号没有型号吧。因为不是很理解“型号”，不同型号的id怎么能相同啊
    // 没有商品名称，还是只保留id原则，万一改名了呢。

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
