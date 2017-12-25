package businesslogic.goodsbl.goodsSearch;

import vo.inventoryVO.GoodsVO;

import java.util.ArrayList;
import java.util.List;

//模糊查询的上下文
public class GoodsSearchContext {
    private static final int DEFAULT_SIZE = 50;

    GoodsSearchStrategy stategy;

    //模糊查询
    public List<GoodsVO> getGoods(){
        List<GoodsVO> goodsList = new ArrayList<>(DEFAULT_SIZE);

        goodsList = stategy.getGoods();

        return goodsList;
    }

    //设置策略
    public void setGoodsStrategy (GoodsSearchStrategy goodsStrategy){
        stategy = goodsStrategy;
    }
}
