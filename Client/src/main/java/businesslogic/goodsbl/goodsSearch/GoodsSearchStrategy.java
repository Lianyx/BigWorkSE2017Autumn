package businesslogic.goodsbl.goodsSearch;

import vo.inventoryVO.GoodsVO;

import java.util.List;

public interface GoodsSearchStrategy {
    //策略接口
    public List<GoodsVO> getGoods();
}
