package businesslogic.goodsbl.goodsSearch.concreteStrategy;

import businesslogic.goodsbl.GoodsController;
import businesslogic.goodsbl.goodsSearch.GoodsSearchStrategy;
import vo.inventoryVO.GoodsVO;

import java.util.List;

public class IdStrategy implements GoodsSearchStrategy {
    private String id;
    private GoodsController controller;

    public IdStrategy() {
        controller = new GoodsController();
    }

    public IdStrategy(String id) {
        this.id = id;
        controller = new GoodsController();
    }

    @Override
    public List<GoodsVO> getGoods() {
        List<GoodsVO> goodsList = controller.SearchGoods(id);
        return goodsList;
    }
}
