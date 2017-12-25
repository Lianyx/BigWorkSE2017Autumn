package businesslogic.goodsbl;

import blService.goodsblService.GoodsblService;
import po.GoodsPO;
import util.ResultMessage;
import vo.inventoryVO.GoodsVO;

import java.util.List;

public class GoodsController implements GoodsblService {
    Goods goods = new Goods();

    @Override
    public List<GoodsVO> show() {
        List<GoodsVO> goodsList = goods.show();
        return goodsList;
    }

    @Override
    public ResultMessage addGoods(GoodsVO goodsVO) {
        ResultMessage resultMessage = goods.addGoods(goodsVO);
        return resultMessage;
    }

    @Override
    public ResultMessage deleteGoods(GoodsVO goodsVO) {
        ResultMessage resultMessage = goods.deleteGoods(goodsVO);
        return resultMessage;
    }

    @Override
    public ResultMessage updateGoods(GoodsVO goodsVO) {
        ResultMessage resultMessage = goods.updateGoods(goodsVO);
        return resultMessage;
    }

    public ResultMessage updateGoods(GoodsPO goodsPO) {
        ResultMessage resultMessage = goods.updateGoods(goodsPO);
        return resultMessage;
    }

    public ResultMessage updateGoods(List<GoodsPO> goodsList){
        for (GoodsPO po:goodsList) {
            goods.updateGoods(po);
        }
        return ResultMessage.SUCCESS;
    }

    @Override
    public List<GoodsVO> SearchGoods(String info) {
        List<GoodsVO> goodsList = goods.SearchGoods(info);
        return goodsList;
    }

    @Override
    public String getID(String upID, int order) {
        String id = goods.getID(upID,order);
        return id;
    }
}
