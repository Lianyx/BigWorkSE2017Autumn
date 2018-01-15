package vo.inventoryVO.goodsTreeTableView;

import vo.inventoryVO.GoodsVO;

public class GoodsTreeTableViewVO extends AbstractGoodsTreeTableViewVO {
    private GoodsVO goodsVO;
    public GoodsTreeTableViewVO(GoodsVO goodsVO) {
        this.goodsVO = goodsVO;
    }

    @Override
    public String getName() {
        return goodsVO.getGoodName();
    }

    @Override
    public String getType() {
        return goodsVO.getGoodType();
    }

    @Override
    public String getNum() {
        return String.valueOf(goodsVO.getInventoryNum());
    }

    @Override
    public String getPurPrice() {
        return String.valueOf(goodsVO.getPurPrice());
    }

    @Override
    public String getSalePrice() {
        return String.valueOf(goodsVO.getSalePrice());
    }

    @Override
    public String getAlarmNumber() {
        return String.valueOf(goodsVO.getAlarmNumber());
    }

    @Override
    public void clickTwiceAftermath() {
        super.clickTwiceAftermath();
        // TODO getGoodsDetailPane
    }
}
