package businesslogic.goodsbl.goodsUpdate;

import businesslogic.goodsbl.GoodsController;
import po.GoodsPO;

import java.util.List;

public class GoodsUpdate implements GoodsUpdateInfo {
    GoodsController controller;
    public GoodsUpdate() {controller = new GoodsController();}


    @Override
    public void goodsUpdate(List<GoodsPO> goodsList) {
        controller.updateGoods(goodsList);
    }
}
