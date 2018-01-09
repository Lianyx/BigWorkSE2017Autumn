package vo.receiptVO;

import blService.checkblService.CheckInfo;
import javafx.scene.Node;
import po.promotionPO.PromotionGoodsItemPO;
import po.receiptPO.SalesSellReceiptPO;
import util.ReceiptState;
import vo.ListGoodsItemVO;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class SalesSellReceiptVO extends SalesReceiptVO {

    private PromotionGoodsItemPO[] gifts;
    private double giveTokenAmount;

    public SalesSellReceiptVO(String id, int operatorId, LocalDateTime createTime, LocalDateTime lastModifiedTime, ReceiptState receiptState, int memberId, String memberName, String clerkName, String stockName, ArrayList<ListGoodsItemVO> items, String comment, double discountAmount, double tokenAmount, double originSum, PromotionGoodsItemPO[] gifts, double giveTokenAmount) {
        super(id, operatorId, createTime, lastModifiedTime, receiptState, memberId, memberName, clerkName, stockName, items, comment, discountAmount, tokenAmount, originSum);
        this.gifts = gifts;
        this.giveTokenAmount = giveTokenAmount;
    }

    public SalesSellReceiptVO() {
    }

    public SalesSellReceiptVO(SalesSellReceiptPO salesSellReceiptPO){
        super(salesSellReceiptPO);
        this.gifts = salesSellReceiptPO.getGifts();
        this.giveTokenAmount =salesSellReceiptPO.getGiveTokenAmount();
    }

    @Override
    public CheckInfo<SalesSellReceiptVO> getService() throws RemoteException, NotBoundException, MalformedURLException {
        return null;
    }

    @Override
    public Node getDetailPane() {
        return null;
    }

    @Override
    protected String getCodeName() {
        return "XSD";
    }

    @Override
    public SalesSellReceiptPO toPO() {
        SalesSellReceiptPO salesSellReceiptPO = toSalesReceiptPO(SalesSellReceiptPO.class);
        salesSellReceiptPO.setGifts(gifts);
        salesSellReceiptPO.setGiveTokenAmount(giveTokenAmount);
        return salesSellReceiptPO;
    }

}