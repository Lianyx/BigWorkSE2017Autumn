package businesslogic.goodsbl.goodsSearch.concreteStrategy;

import businesslogic.goodsbl.GoodsController;
import businesslogic.goodsbl.goodsSearch.GoodsSearchStrategy;
import vo.inventoryVO.GoodsVO;

import java.util.List;

public class GoodTypeStrategy implements GoodsSearchStrategy {
    private String goodsType;
    private GoodsController controller;

    public GoodTypeStrategy() {
        controller = new GoodsController();
    }

    public GoodTypeStrategy(String goodsType) {
        this.goodsType = goodsType;
        controller = new GoodsController();
    }

    @Override
    public List<GoodsVO> getGoods() {
        List<GoodsVO> goodsList = controller.SearchGoods(goodsType);
        return goodsList;
    }
}
