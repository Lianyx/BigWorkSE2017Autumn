package po;

import util.GoodsList;
import util.ReceiptCategory;

public class StockReceiptPO {
    private int ID;
    private ReceiptCategory receiptCategory;
    private int number;
    private int memberid;
    private String stockName;
    private String operator;
    private GoodsList goodsList;
    private String comment;
    private int discount;
    private int voucherSum;
    private int sum;

    public StockReceiptPO(ReceiptCategory receiptCategory, int number, int memberid, String stockName, String operator, String comment, int discount, int voucherSum) {
        this.receiptCategory = receiptCategory;
        this.number = number;
        this.memberid = memberid;
        this.stockName = stockName;
        this.operator = operator;
        this.comment = comment;
        this.discount = discount;
        this.voucherSum = voucherSum;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public GoodsList getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(GoodsList goodsList) {
        this.goodsList = goodsList;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
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

    public int getMemberid() {
        return memberid;
    }

    public void setMemberid(int memberid) {
        this.memberid = memberid;
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public int getVoucherSum() {
        return voucherSum;
    }

    public void setVoucherSum(int voucherSum) {
        this.voucherSum = voucherSum;
    }
}
