package blService.goodsClassificationblService;

import exception.ExistException;
import util.ResultMessage;
import vo.GoodsClassificationVO;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public interface GoodsClasssficationblService {
    /**
     * 显示所有商品分类
     *
     * @return
     */
    public List<GoodsClassificationVO> show();

    /**
     * 创建商品分类时调用，根据上层分类编号生成编号
     * @param upID
     * @return
     */
    public String getID(String upID);

    /**
     * 增加分类
     * @param vo
     * @return
     */
    public ResultMessage addGoodsClassification(GoodsClassificationVO vo) throws ExistException;

    /**
     * 删除商品分类
     * @param ID
     * @return
     */
    public ResultMessage deleteGoodsClassification(String ID);

    /**
     * 修改分类
     * @param vo
     * @return
     */
    public ResultMessage updateGoodsClassification(GoodsClassificationVO vo);
}
