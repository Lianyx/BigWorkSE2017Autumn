package po;

import util.GoodsList;
import util.ReceiptCategory;

public class SalesReceiptPO {
    private int ID;
    private ReceiptCategory receiptCategory;
    private int number;
    private int supplierid;
    private String stockName;
    private String operator;
    private GoodsList goodsList;
    private String comment;
    private int sum;

    public SalesReceiptPO(ReceiptCategory receiptCategory, int number, int supplier, String stockName, String operator, String comment, int sum) {
        this.receiptCategory = receiptCategory;
        this.number = number;
        this.supplierid = supplier;
        this.stockName = stockName;
        this.operator = operator;
        this.comment = comment;
        this.sum = sum;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public ReceiptCategory getReceiptCategory() {
        return receiptCategory;
    }

    public void setReceiptCategory(ReceiptCategory receiptCategory) {
        this.receiptCategory = receiptCategory;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getSupplier() {
        return supplierid;
    }

    public void setSupplier(int supplier) {
        this.supplierid = supplier;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public GoodsList getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(GoodsList goodsList) {
        this.goodsList = goodsList;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }
}
