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
    private int memberId;
    private String memberName;
    private String clerkName;
    private String stockName;
    private ArrayList<ListGoodsItemVO> items = new ArrayList<>();
    private String comment;
    private double discountAmount;
    private double tokenAmount;
    private double originSum;
    private PromotionGoodsItemPO[] gifts;
    private double giveTokenAmount;



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


    public SalesReceiptVO(String id, int operatorId, LocalDateTime createTime, LocalDateTime lastModifiedTime, ReceiptState receiptState, int memberId, String memberName, String clerkName, String stockName, ArrayList<ListGoodsItemVO> items, String comment, double discountAmount, double tokenAmount, double originSum, PromotionGoodsItemPO[] gifts, double giveTokenAmount) {
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
        this.gifts = gifts;
        this.giveTokenAmount = giveTokenAmount;
    }



    public PromotionGoodsItemPO[] getGifts() {
        return gifts;
    }

    public void setGifts(PromotionGoodsItemPO[] gifts) {
        this.gifts = gifts;
    }

    public double getGiveTokenAmount() {
        return giveTokenAmount;
    }

    public void setGiveTokenAmount(double giveTokenAmount) {
        this.giveTokenAmount = giveTokenAmount;
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

    public SalesSellReceiptPO convertToSell(SalesReceiptPO salesReceiptPO){
        return new SalesSellReceiptPO(salesReceiptPO.getDayId(),salesReceiptPO.getOperatorId(),salesReceiptPO.getCreateTime(),salesReceiptPO.getLastModifiedTime(),salesReceiptPO.getReceiptState(),this.getMemberId(),this.getClerkName(),this.getStockName(),toGoodsArray(items),this.getDiscountAmount(),this.getTokenAmount(),this.getOriginSum(),this.getComment(),this.getGifts(),this.getGiveTokenAmount());
    }
    public SalesRetReceiptPO convertToRet(SalesReceiptPO salesReceiptPO){
        return new SalesRetReceiptPO(salesReceiptPO.getDayId(),salesReceiptPO.getOperatorId(),salesReceiptPO.getCreateTime(),salesReceiptPO.getLastModifiedTime(),salesReceiptPO.getReceiptState(),this.getMemberId(),this.getClerkName(),this.getStockName(),toGoodsArray(items),this.getDiscountAmount(),this.getTokenAmount(),this.getOriginSum(),this.getComment());
    }

    public ReceiptGoodsItemPO[] toGoodsArray(ArrayList<ListGoodsItemVO> items){
        List<ReceiptGoodsItemPO> receiptGoodsItemPOs = items.stream().map(t->t.toPo()).collect(Collectors.toList());
        ReceiptGoodsItemPO[] goodsItemPOs = (ReceiptGoodsItemPO[])receiptGoodsItemPOs.toArray();
        return goodsItemPOs;
    }
}
