package businesslogic.goodsbl.goodsSearch;

import vo.GoodsVO;

import java.util.List;

public interface GetGoodsStategy {
    //策略接口
    public List<GoodsVO> getGoods();
}
