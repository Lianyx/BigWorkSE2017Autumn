package blServiceStub.goodsblService_Stub;

import util.ResultMessage;
import vo.inventoryVO.GoodsVO;

import java.util.HashSet;
import java.util.Set;

public class GoodsblService_Stub {
    /**
     * 显示所有商品
     */
    public Set<GoodsVO> show(){
        System.out.println("show success");
        return new HashSet<GoodsVO>();
    }

    /**
     * 增加商品
     * @param goodsVO
     */
    public ResultMessage addGoods(GoodsVO goodsVO){
        System.out.println("insert success");
        return ResultMessage.SUCCESS;
    }

    /**
     * 删除商品
     * @param ID
     * @return
     */
    public ResultMessage deleteGoods(String ID){
        System.out.println("delete success");
        return ResultMessage.SUCCESS;
    }

    /**
     * 更改商品属性
     * @param goodsVO
     * @return
     */
    public ResultMessage updateGoods(GoodsVO goodsVO){
        System.out.println("update success");
        return ResultMessage.SUCCESS;
    }

    /**
     * 根据商品编号或名称查找商品
     * @param info
     * @return
     */
    public Set<GoodsVO> SearchGoods(String info){
        return new HashSet<GoodsVO>();
    }

    /**
     * 根据分类编号和添加次序生成编号
     * @param upID
     * @return
     */
    public String getID(String upID){
        return "123";
    }
}
