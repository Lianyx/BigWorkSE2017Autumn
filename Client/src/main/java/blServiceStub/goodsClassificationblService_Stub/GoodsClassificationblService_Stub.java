package blServiceStub.goodsClassificationblService_Stub;

import util.ResultMessage;
import vo.inventoryVO.GoodsClassificationVO;

import java.util.HashSet;
import java.util.Set;

public class GoodsClassificationblService_Stub {
    /**
   	 * 显示所有商品分类
	 * @return
             */
    public Set<GoodsClassificationVO> show(){
        System.out.println("show success");
        return new HashSet<GoodsClassificationVO>();
    }

    /**
     * 创建商品分类时调用，根据上层分类编号生成编号
     * @param upID
     * @return
     */
    public String getID(String upID){
        return "123";
    }

    /**
     * 增加分类
     * @param name
     * @param upID
     * @return
     */
    public ResultMessage addGoodsClassification(String name,String upID){
        System.out.println("insert success");
        return ResultMessage.SUCCESS;
    }

    /**
     * 删除商品分类
     * @param ID
     * @return
     */
    public ResultMessage deleteGoodsClassification(String ID){
        System.out.println("delete success");
        return ResultMessage.SUCCESS;
    }

    /**
     * 修改分类
     * @param ID
     * @param name
     * @return
     */
    public ResultMessage updateGoodsClassification(String ID,String name){
        System.out.println("modify success");
        return ResultMessage.SUCCESS;
    }
}
