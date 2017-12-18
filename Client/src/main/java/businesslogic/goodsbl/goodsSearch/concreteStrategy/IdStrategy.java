package businesslogic.goodsbl.goodsSearch.concreteStrategy;

import businesslogic.goodsbl.Goods;
import businesslogic.goodsbl.GoodsController;
import businesslogic.goodsbl.GoodsPOVOChanger;
import businesslogic.goodsbl.goodsSearch.GetGoodsStategy;
import po.GoodsPO;
import vo.GoodsVO;

import java.util.List;

public class IdStrategy implements GetGoodsStategy {
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
