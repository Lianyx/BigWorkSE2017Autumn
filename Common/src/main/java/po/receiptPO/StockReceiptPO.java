package po.receiptPO;

import po.ReceiptGoodsItemPO;
import util.GoodsList;
import util.ReceiptCategory;
import util.ReceiptState;

import java.time.LocalDateTime;

public class StockReceiptPO extends ReceiptPO{
    private int memberid;
    private String stockName;
    private ReceiptGoodsItemPO[] goodsList;
    private double originalSum;
    private String comment;


    public StockReceiptPO(){}

    public StockReceiptPO(int dayId, int operatorId, LocalDateTime createTime, LocalDateTime lastModifiedTime, ReceiptState receiptState, int memberid, String stockName, ReceiptGoodsItemPO[] goodsList, double originalSum,String comment) {
        super(dayId, operatorId, createTime, lastModifiedTime, receiptState);
        this.memberid = memberid;
        this.stockName = stockName;
        this.goodsList = goodsList;
        this.comment = comment;
        this.originalSum = originalSum;
    }

    public ReceiptGoodsItemPO[] getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(ReceiptGoodsItemPO[] goodsList) {
        this.goodsList = goodsList;
    }

    public double getOriginalSum() {
        return originalSum;
    }

    public void setOriginalSum(double originalSum) {
        this.originalSum = originalSum;
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



    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }


}
