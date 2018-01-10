package vo.promotionVO;

import blService.promotionblService.PromotionblService;
import blService.promotionblService.TotalPromotionblService;
import businesslogic.promotionbl.MyblServiceFactory;
import po.promotionPO.PromotionGoodsItemPO;
import po.promotionPO.TotalPromotionPO;
import ui.managerui.promotionui.promotionDetailPane.PromotionDetailPane;
import ui.managerui.promotionui.promotionDetailPane.TotalPromotionDetailPane;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class TotalPromotionVO extends PromotionVO {
    private double requiredTotal;
    private double tokenAmount;
    private ArrayList<PromotionGoodsItemVO> gifts;


    public TotalPromotionVO() {
    }

    public TotalPromotionVO(TotalPromotionPO promotionPO) {
        super(promotionPO);
        requiredTotal = promotionPO.getRequiredTotal();
        tokenAmount = promotionPO.getTokenAmount();
        gifts = promotionPO.getGifts() == null ? null
                : Arrays.stream(promotionPO.getGifts()).map(PromotionGoodsItemVO::new).collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    protected String getCodeName() {
        return "ZJCX";
    }

    @Override
    public TotalPromotionPO toPO() {
        TotalPromotionPO result = toPromotionPO(TotalPromotionPO.class);
        result.setRequiredTotal(requiredTotal);
        result.setTokenAmount(tokenAmount);
        result.setGifts(gifts == null ? null : gifts.stream().map(PromotionGoodsItemVO::toPO).toArray(PromotionGoodsItemPO[]::new));
        return result;
    }

    @Override
    public PromotionblService getService() throws RemoteException, NotBoundException, MalformedURLException {
        return MyblServiceFactory.getService(TotalPromotionblService.class);
    }

    @Override
    public PromotionDetailPane getDetailPane() {
        return new TotalPromotionDetailPane(this);
    }

    public double getRequiredTotal() {
        return requiredTotal;
    }

    public void setRequiredTotal(double requiredTotal) {
        this.requiredTotal = requiredTotal;
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
