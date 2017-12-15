package goods;

import businesslogic.goodsbl.GoodsItem;

public class MockGoodsItem extends GoodsItem{
    MockGoods good;
    String id;
    int factCount;

    public MockGoodsItem( MockGoods good, int factCount) {
        this.good = good;
        this.factCount = factCount;
        this.id = good.id;
    }

    public MockGoods getGood() {
        return good;
    }

    public int getFactCount() {
        return factCount;
    }
}
