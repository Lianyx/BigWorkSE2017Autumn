package vo.promotionVO;

import po.PromotionGoodsItemPO;
import po.promotionPO.CombinePromotionPO;
import po.promotionPO.PromotionPO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class CombinePromotionVO extends PromotionVO {
    private ArrayList<PromotionGoodsItemPO> goodsCombination;
    private double discountAmount;

    public CombinePromotionVO(CombinePromotionPO promotionPO) {
        discountAmount = promotionPO.getDiscountAmount();
        goodsCombination = Arrays.stream(promotionPO.getGoodsCombination()).collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public <T extends PromotionPO> T toPO() {
        return null;
    }
}
