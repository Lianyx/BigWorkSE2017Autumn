package businesslogic.goodsbl.goodsSearch;

import businesslogic.goodsbl.Goods;
import vo.GoodsVO;

import java.util.List;

public class GoodsSearchInfo implements GoodsFuzzyQuery{
    GoodsContext goodsContext;

    public GoodsSearchInfo() {
        this.goodsContext = new GoodsContext();
    }

    @Override
    public List<GoodsVO> getGoods(String info, GetGoodsStategy stategy) {
        goodsContext.setGetGoodsStrategy(stategy);

        List<GoodsVO> goodsList = goodsContext.getGoods();

        return goodsList;
    }
}
