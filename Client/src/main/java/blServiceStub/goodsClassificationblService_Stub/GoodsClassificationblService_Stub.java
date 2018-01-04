package blServiceStub.goodsClassificationblService_Stub;

import blService.goodsClassificationblService.GoodsClassificationblService;
import exception.ExistException;
import util.ResultMessage;
import vo.inventoryVO.GoodsClassificationVO;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class GoodsClassificationblService_Stub implements GoodsClassificationblService{
    Set<GoodsClassificationVO> set = new TreeSet<>();


    public GoodsClassificationblService_Stub() {
        set.add(new GoodsClassificationVO("123","345","212"));
        set.add(new GoodsClassificationVO("124","345","212"));
        set.add(new GoodsClassificationVO("125","345","212"));
        set.add(new GoodsClassificationVO("126","345","212"));
        set.add(new GoodsClassificationVO("127","345","212"));
    }

    /**
   	 * 显示所有商品分类
	 * @return
             */
    public Set<GoodsClassificationVO> show(){
        System.out.println("show success");
        return set;
    }



    @Override
    public String getID(String fatherId, int order) {
        return null;
    }

    @Override
    public ResultMessage addGoodsClassification(GoodsClassificationVO vo) throws ExistException {
        return null;
    }

    @Override
    public ResultMessage deleteGoodsClassification(GoodsClassificationVO vo) {
        return null;
    }

    @Override
    public ResultMessage updateGoodsClassification(GoodsClassificationVO vo) {
        return null;
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
