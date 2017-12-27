package vo.promotionVO;

import blService.promotionblService.PromotionblService;
import businesslogic.promotionbl.PromotionFactory;
import javafx.scene.layout.Pane;
import po.promotionPO.MemberPromotionPO;
import po.promotionPO.PromotionGoodsItemPO;
import ui.managerui.promotion.MemberPromotionDetailPane;
import ui.managerui.promotion.PromotionDetailPane;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;


public class MemberPromotionVO extends PromotionVO{
    private int requiredLevel;
    private double discountFraction;
    private double tokenAmount;
    private ArrayList<PromotionGoodsItemVO> gifts;

    public MemberPromotionVO() {
    }

    public MemberPromotionVO (MemberPromotionPO promotionPO) {
        super(promotionPO);
        id = formatId("HYCX", promotionPO);

        requiredLevel = promotionPO.getRequiredLevel();
        discountFraction = promotionPO.getDiscountFraction();
        tokenAmount = promotionPO.getTokenAmount();
        gifts = Arrays.stream(promotionPO.getGifts()).map(PromotionGoodsItemVO::new).collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public MemberPromotionPO toPO() {
        return new MemberPromotionPO(idToDayId(),
                createTime,
                lastModifiedTime,
                beginTime,
                endTime,
                comment,
                requiredLevel,
                discountFraction,
                tokenAmount,
                gifts.stream().map(PromotionGoodsItemVO::toPO).toArray(PromotionGoodsItemPO[]::new));
    }

    @Override
    public PromotionblService getService() throws RemoteException, NotBoundException, MalformedURLException {
        return PromotionFactory.getMemberPromotionblService();
    }

    @Override
    public PromotionDetailPane getDetailPane() {
        return new MemberPromotionDetailPane(this);
    }

    public int getRequiredLevel() {
        return requiredLevel;
    }

    public void setRequiredLevel(int requiredLevel) {
        this.requiredLevel = requiredLevel;
    }

    public double getDiscountFraction() {
        return discountFraction;
    }

    public void setDiscountFraction(double discountFraction) {
        this.discountFraction = discountFraction;
    }

    public double getTokenAmount() {
        return tokenAmount;
    }

    public void setTokenAmount(double tokenAmount) {
        this.tokenAmount = tokenAmount;
    }

    public ArrayList<PromotionGoodsItemVO> getGifts() {
        return gifts;
    }

    public void setGifts(ArrayList<PromotionGoodsItemVO> gifts) {
        this.gifts = gifts;
    }
}
