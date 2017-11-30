package dataService.promotiondataService;

import po.PromotionPO;
import util.ResultMessage;

import java.util.List;

public interface PromotiondataService<T extends PromotionPO> {
    int getID();

    ResultMessage insert(T promotionPO);

    ResultMessage update(T promotionPO);

    ResultMessage delete(T promotionPO);

    List<T> selectInEffect();

}
