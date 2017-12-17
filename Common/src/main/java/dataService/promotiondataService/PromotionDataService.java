package dataService.promotiondataService;

import po.PromotionPO;
import util.ResultMessage;

import java.util.List;

public interface PromotionDataService<T extends PromotionPO> {
    int getDayId();

    ResultMessage insert(T promotionPO);

    ResultMessage update(T promotionPO);

    ResultMessage delete(T promotionPO);

    List<T> selectInEffect();

}