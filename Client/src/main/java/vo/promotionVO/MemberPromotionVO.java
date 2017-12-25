package vo.promotionVO;

import blService.promotionblService.PromotionblService;
import businesslogic.promotionbl.PromotionFactory;
import po.promotionPO.MemberPromotionPO;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;


public class MemberPromotionVO extends PromotionVO{
    private int requiredLevel;
    private double discountFraction;
    private double tokenAmount;

    public MemberPromotionVO() {
    }

    public MemberPromotionVO (MemberPromotionPO promotionPO) {
        super(promotionPO);
        id = formatId("HYCX", promotionPO);

        requiredLevel = promotionPO.getRequiredLevel();
        discountFraction = promotionPO.getDiscountFraction();
        tokenAmount = promotionPO.getTokenAmount();
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
                tokenAmount);
    }

    @Override
    public PromotionblService getService() throws RemoteException, NotBoundException, MalformedURLException {
        return PromotionFactory.getMemberPromotionblService();
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
}
