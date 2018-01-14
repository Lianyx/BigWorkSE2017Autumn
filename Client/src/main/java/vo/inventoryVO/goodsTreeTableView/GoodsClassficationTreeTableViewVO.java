package vo.inventoryVO.goodsTreeTableView;

import vo.inventoryVO.GoodsClassificationVO;

public class GoodsClassficationTreeTableViewVO extends AbstractGoodsTreeTableViewVO {
    private GoodsClassificationVO goodsClassificationVO;

    public GoodsClassficationTreeTableViewVO(GoodsClassificationVO goodsClassificationVO) {
        this.goodsClassificationVO = goodsClassificationVO;
    }

    @Override
    public String getName() {
        return goodsClassificationVO.getName();
    }

    @Override
    public String getNum() {
        return "";
    }

    @Override
    public String getPurPrice() {
        return "";
    }

    @Override
    public String getSalePrice() {
        return "";
    }

    @Override
    public String getAlarmNumber() {
        return "";
    }
}
