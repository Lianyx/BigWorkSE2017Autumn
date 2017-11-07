package blService.goodsClassificationblService;

import util.ResultMessage;
import vo.GoodsClassificationVO;

import java.util.ArrayList;
import java.util.Set;

public interface GoodsClasssficationblService {
    /**
     * 显示所有商品分类
     * @return
     */
    public Set<GoodsClassificationVO> show();

    /**
     * 创建商品分类时调用，根据上层分类编号生成编号
     * @param upID
     * @return
     */
    public String getID(String upID);

    /**
     * 增加分类
     * @param name
     * @param upID
     * @return
     */
    public ResultMessage addGoodsClassification(String name, String upID);

    /**
     * 删除商品分类
     * @param ID
     * @return
     */
    public ResultMessage deleteGoodsClassification(String ID);

    /**
     * 修改分类
     * @param ID
     * @param name
     * @return
     */
    public ResultMessage updateGoodsClassification(String ID,String name);
}
