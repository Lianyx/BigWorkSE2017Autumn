package blService.goodsblService;

import util.ResultMessage;
import vo.inventoryVO.GoodsVO;

import java.util.List;
import java.util.Set;

public interface GoodsblService {
    /**
     * 显示所有商品
     */
    public Set<GoodsVO> show();

    /**
     * 增加商品
     * @param goodsVO
     */
    public ResultMessage addGoods(GoodsVO goodsVO);

    /**
     * 删除商品
     * @param goodsVO
     * @return
     */
    public ResultMessage deleteGoods(GoodsVO goodsVO);

    /**
     * 更改商品属性
     * @param goodsVO
     * @return
     */
    public ResultMessage updateGoods(GoodsVO goodsVO);

    /**
     * 根据商品编号或名称查找商品
     * @param info
     * @return
     */
    public List<GoodsVO> SearchGoods(String info);

    /**
     * 根据分类编号和添加次序生成编号
     * @param upID
     * @return
     */
    public String getID(String upID ,int order);
}
