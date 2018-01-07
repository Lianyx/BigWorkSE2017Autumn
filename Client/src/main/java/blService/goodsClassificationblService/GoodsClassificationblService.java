package blService.goodsClassificationblService;

import exception.ExistException;
import util.ResultMessage;
import vo.inventoryVO.GoodsClassificationVO;

import java.util.List;
import java.util.Set;

public interface GoodsClassificationblService {
    /**
     * 显示所有商品分类
     *
     * @return
     */
    public List<GoodsClassificationVO> show();

    /**
     * 创建商品分类时调用，根据上层分类编号生成编号
     * @param fatherId
     * @param order
     * @return
     */
    public String getID(String fatherId, int order);

    /**
     * 增加分类
     * @param vo
     * @return
     */
    public ResultMessage addGoodsClassification(GoodsClassificationVO vo) throws ExistException;

    /**
     * 删除商品分类
     * @param vo
     * @return
     */
    public ResultMessage deleteGoodsClassification(GoodsClassificationVO vo);

    /**
     * 修改分类
     * @param vo
     * @return
     */
    public ResultMessage updateGoodsClassification(GoodsClassificationVO vo);
}
