package vo.receiptVO;

import po.ReceiptGoodsItemPO;
import util.ReceiptState;

import java.time.LocalDateTime;

public class SalesReceiptVO extends ReceiptVO {
    private int clientId;
    private String clerkName; // 业务员
    private String stockName;
    private ReceiptGoodsItemPO[] goodsList;
    private double discountAmount;
    private double tokenAmount;
    private double originSum;
    private String comment;

    public SalesReceiptVO(String id, int operatorId, LocalDateTime createTime, LocalDateTime lastModifiedTime, ReceiptState receiptState, int clientId, String clerkName, String stockName, ReceiptGoodsItemPO[] goodsList, double discountAmount, double tokenAmount, double originSum, String comment) {
        super(id, operatorId, createTime, lastModifiedTime, receiptState);
        this.clientId = clientId;
        this.clerkName = clerkName;
        this.stockName = stockName;
        this.goodsList = goodsList;
        this.discountAmount = discountAmount;
        this.tokenAmount = tokenAmount;
        this.originSum = originSum;
        this.comment = comment;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public String getClerkName() {
        return clerkName;
    }

    public void setClerkName(String clerkName) {
        this.clerkName = clerkName;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public ReceiptGoodsItemPO[] getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(ReceiptGoodsItemPO[] goodsList) {
        this.goodsList = goodsList;
    }

    public double getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(double discountAmount) {
        this.discountAmount = discountAmount;
    }

    public double getTokenAmount() {
        return tokenAmount;
    }

    public void setTokenAmount(double tokenAmount) {
        this.tokenAmount = tokenAmount;
    }

    public double getOriginSum() {
        return originSum;
    }

    public void setOriginSum(double originSum) {
        this.originSum = originSum;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
