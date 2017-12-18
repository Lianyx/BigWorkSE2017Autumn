package businesslogic.goodsbl.goodsSearch;

import vo.GoodsVO;

import java.util.ArrayList;
import java.util.List;

//模糊查询的上下文
public class GoodsContext {
    private static final int DEFAULT_SIZE = 50;

    GetGoodsStategy stategy;

    //模糊查询
    public List<GoodsVO> getGoods(){
        List<GoodsVO> goodsList = new ArrayList<>(DEFAULT_SIZE);

        goodsList = stategy.getGoods();

        return goodsList;
    }

    //设置策略
    public void setGetGoodsStrategy (GetGoodsStategy getGoodsStrategy){
        stategy = getGoodsStrategy;
    }
}
