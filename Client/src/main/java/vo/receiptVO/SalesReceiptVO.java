package vo.receiptVO;

import blService.checkblService.CheckInfo;
import blService.checkblService.ReceiptblService;
import javafx.beans.property.*;
import javafx.scene.Node;
import po.ReceiptGoodsItemPO;
import po.promotionPO.PromotionGoodsItemPO;
import po.receiptPO.*;
import util.ReceiptState;
import vo.ListGoodsItemVO;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class SalesReceiptVO extends ReceiptVO {
    protected int memberId;
    protected String memberName;
    protected String clerkName;
    protected String stockName;
    protected ArrayList<ListGoodsItemVO> items = new ArrayList<>();
    protected String comment;
    protected double discountAmount;
    protected double tokenAmount;
    protected double originSum;

    public SalesReceiptVO() {
    }

    public SalesReceiptVO(String id, int operatorId, LocalDateTime createTime, LocalDateTime lastModifiedTime, ReceiptState receiptState, int memberId, String memberName, String clerkName, String stockName, ArrayList<ListGoodsItemVO> items, String comment, double discountAmount, double tokenAmount, double originSum) {
        super(id, operatorId, createTime, lastModifiedTime, receiptState);
        this.memberId = memberId;
        this.memberName = memberName;
        this.clerkName = clerkName;
        this.stockName = stockName;
        this.items = items;
        this.comment = comment;
        this.discountAmount = discountAmount;
        this.tokenAmount = tokenAmount;
        this.originSum = originSum;
    }

    public SalesReceiptVO(SalesReceiptPO salesReceiptPO){
        super(salesReceiptPO);
        this.memberId = salesReceiptPO.getMemberId();
        this.clerkName = salesReceiptPO.getClerkName();
        this.stockName = salesReceiptPO.getStockName();
        this.items = toGoodsList(salesReceiptPO.getGoodsList());
        this.comment = salesReceiptPO.getComment();
        this.discountAmount =salesReceiptPO.getDiscountAmount();
        this.tokenAmount = salesReceiptPO.gettokenAmount();
        this.originSum = salesReceiptPO.getOriginSum();
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
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

    public ArrayList<ListGoodsItemVO> getItems() {
        return items;
    }

    public void setItems(ArrayList<ListGoodsItemVO> items) {
        this.items = items;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
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

    public ReceiptGoodsItemPO[] toGoodsArray(ArrayList<ListGoodsItemVO> items){
        List<ReceiptGoodsItemPO> receiptGoodsItemPOs = items.stream().map(t->t.toPo()).collect(Collectors.toList());
        ReceiptGoodsItemPO[] goodsItemPOs = (ReceiptGoodsItemPO[])receiptGoodsItemPOs.toArray();
        return goodsItemPOs;
    }

    public ArrayList<ListGoodsItemVO> toGoodsList(ReceiptGoodsItemPO[] array){
        ArrayList<ListGoodsItemVO> list = new ArrayList<>();
        for(ReceiptGoodsItemPO receiptGoodsItemPO:array){
            list.add(new ListGoodsItemVO(receiptGoodsItemPO));
        }
        return list;
    }
    protected <T extends SalesReceiptPO> T toSalesReceiptPO(Class<T> receiptClass) {
        T result = toReceiptPO(receiptClass);
        result.setMemberId(memberId);
        result.setClerkName(clerkName);
        result.setStockName(stockName);
        result.setGoodsList(toGoodsArray(items));
        result.setComment(comment);
        result.setDiscountAmount(discountAmount);
        result.settokenAmount(tokenAmount);
        result.setOriginSum(originSum);
        return result;
    }
}
