package businesslogic.goodsbl.goodsSearch.concreteStrategy;

import businesslogic.goodsbl.GoodsController;
import businesslogic.goodsbl.goodsSearch.GetGoodsStategy;
import vo.GoodsVO;

import java.util.List;

public class GoodClassifyIdStrategy implements GetGoodsStategy{
    String goodClassifyId;
    GoodsController controller;

    public GoodClassifyIdStrategy() {
        controller = new GoodsController();
    }

    public GoodClassifyIdStrategy(String goodClassifyId) {
        this.goodClassifyId = goodClassifyId;
        controller = new GoodsController();
    }

    @Override
    public List<GoodsVO> getGoods() {
        List<GoodsVO> goodsList = controller.SearchGoods(goodClassifyId);
        return goodsList;
    }
}
