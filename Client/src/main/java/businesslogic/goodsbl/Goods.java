package businesslogic.goodsbl;

import businesslogic.goodsClassificationbl.GoodsClassificationInfo;
import dataService.goodsdataService.GoodsDataService;
import po.GoodsPO;
import util.ResultMessage;
import vo.inventoryVO.GoodsVO;

import java.util.List;

public class Goods {
    private GoodsDataService dataService;

    private GoodsPOVOChanger changer;

    private GoodsClassificationInfo info = new GoodsClassificationInfo();

    /**
     * 返回所有商品
     * @return
     */
    public List<GoodsVO> show() {
        List<GoodsPO> POList = dataService.show();
        List<GoodsVO> VOList = changer.allToVO(POList);
        return VOList;
    }

    /**
     * 添加商品
     * @param goodsVO
     * @return
     */
    public ResultMessage addGoods(GoodsVO goodsVO) {
        GoodsPO po = changer.oneToPO(goodsVO);

        dataService.insert(po);

        info.addGoods(po.getClassifyId(),po.getId());

        return ResultMessage.SUCCESS;
    }

    public ResultMessage deleteGoods(GoodsVO goodsVO) {
        dataService.delete(goodsVO.getId());

        info.deleteGoods(goodsVO.getClassifyId(),goodsVO.getId());

        return ResultMessage.SUCCESS;
    }

    public ResultMessage updateGoods(GoodsVO goodsVO) {
        GoodsPO po = changer.oneToPO(goodsVO);
        dataService.update(po);
        return ResultMessage.SUCCESS;
    }

    public ResultMessage updateGoods(GoodsPO goodsPO) {
        dataService.update(goodsPO);
        return ResultMessage.SUCCESS;
    }

    public List<GoodsVO> SearchGoods(String info) {
        /* 虽然前面使用了策略模式实现模糊查询及查询方式的可拓展性，但实际上他们调的是Controller的同一个方法，再调这个方法，所以是假模糊查询，待讨论*/
        List<GoodsPO> POList = dataService.select(info);
        List<GoodsVO> VOList = changer.allToVO(POList);
        return VOList;
    }

    /**
     * 这里将商品编号设置为 分类ID + 添加次序的形式
     * @param upID
     * @param order
     * @return
     */
    public String getID(String upID, int order) {
        String id = upID+order;
        return id;
    }
}
