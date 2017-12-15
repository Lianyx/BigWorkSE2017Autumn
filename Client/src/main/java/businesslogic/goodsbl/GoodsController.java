package businesslogic.goodsbl;

import blService.goodsblService.GoodsblService;
import util.ResultMessage;
import vo.GoodsVO;

import java.util.List;

public class GoodsController implements GoodsblService {
    Goods goods = new Goods();

    @Override
    public List<GoodsVO> show() {
        return null;
    }

    @Override
    public ResultMessage addGoods(GoodsVO goodsVO) {
        return null;
    }

    @Override
    public ResultMessage deleteGoods(String ID) {
        return null;
    }

    @Override
    public ResultMessage updateGoods(GoodsVO goodsVO) {
        return null;
    }

    @Override
    public List<GoodsVO> SearchGoods(String info) {
        return null;
    }

    @Override
    public String getID(String upID, int order) {
        return null;
    }
}
