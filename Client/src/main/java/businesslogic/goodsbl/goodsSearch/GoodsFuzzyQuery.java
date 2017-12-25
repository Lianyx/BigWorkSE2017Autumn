package businesslogic.goodsbl.goodsSearch;

import vo.inventoryVO.GoodsVO;

import java.util.List;

public class GoodsFuzzyQuery implements GoodsFuzzyQueryInfo {
    GoodsSearchContext goodsContext;

    public GoodsFuzzyQuery() {
        this.goodsContext = new GoodsSearchContext();
    }

    @Override
    public List<GoodsVO> getGoods(String info, GoodsSearchStrategy stategy) {
        goodsContext.setGoodsStrategy(stategy);

        List<GoodsVO> goodsList = goodsContext.getGoods();

        return goodsList;
    }
}
