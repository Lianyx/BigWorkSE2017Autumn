package businesslogic.goodsbl.goodsSearch;

import vo.GoodsVO;

import java.util.List;

public interface GoodsFuzzyQuery {
    /**
     * 提供给外部的模糊查询接口，输入查询信息和查询策略（查询策略会随着ui传下来，因为每次搜搜旁边都有一个小框框提示使用的策略）
     * @return
     */
    public List<GoodsVO> getGoods(String info, GetGoodsStategy stategy);
}
