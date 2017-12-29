package vo.promotionVO;

import blService.promotionblService.PromotionblService;
import businesslogic.promotionbl.PromotionFactory;
import javafx.scene.layout.Pane;
import po.promotionPO.PromotionGoodsItemPO;
import po.promotionPO.TotalPromotionPO;
import ui.managerui.promotion.PromotionDetailPane;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class TotalPromotionVO extends PromotionVO{
    private double requiredTotal;
    private double tokenAmount;
    private ArrayList<PromotionGoodsItemVO> gifts;

    public TotalPromotionVO() {
    }

    public TotalPromotionVO(TotalPromotionPO promotionPO) {
        super(promotionPO);
        id = formatId("ZJCX", promotionPO);

        requiredTotal = promotionPO.getRequiredTotal();
        tokenAmount = promotionPO.getTokenAmount();
        gifts = Arrays.stream(promotionPO.getGifts())
                .map(PromotionGoodsItemVO::new)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public TotalPromotionPO toPO() {
        return new TotalPromotionPO(idToDayId(),
                createTime,
                lastModifiedTime,
                beginTime,
                endTime,
                comment,
                requiredTotal,
                tokenAmount,
                gifts.stream().map(PromotionGoodsItemVO::toPO).toArray(PromotionGoodsItemPO[]::new));
    }

    @Override
    public PromotionblService getService() throws RemoteException, NotBoundException, MalformedURLException {
        return PromotionFactory.getTotalPromotionblService();
    }

    @Override
    public PromotionDetailPane getDetailPane() {
        return null;
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
