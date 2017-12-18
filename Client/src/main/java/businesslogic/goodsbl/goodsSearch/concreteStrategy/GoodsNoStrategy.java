package businesslogic.goodsbl.goodsSearch.concreteStrategy;

import businesslogic.goodsbl.GoodsController;
import businesslogic.goodsbl.goodsSearch.GetGoodsStategy;
import vo.GoodsVO;

import java.util.List;

/**
 * 传过来的为空，证明是获取所有的商品
 */
public class GoodsNoStrategy implements GetGoodsStategy {
    GoodsController controller;

    public GoodsNoStrategy() {
        controller = new GoodsController();
    }

    @Override
    public List<GoodsVO> getGoods() {
        List<GoodsVO> goodsList = controller.show();
        return goodsList;
    }
}
