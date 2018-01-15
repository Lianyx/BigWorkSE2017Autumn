package vo.inventoryVO.goodsTreeTableView;

import vo.inventoryVO.GoodsClassificationVO;
import vo.inventoryVO.MyGoodsClassificationVO;

public class GoodsClassficationTreeTableViewVO extends AbstractGoodsTreeTableViewVO {
    private MyGoodsClassificationVO goodsClassificationVO;

    public GoodsClassficationTreeTableViewVO(MyGoodsClassificationVO goodsClassificationVO) {
        this.goodsClassificationVO = goodsClassificationVO;
    }

    @Override
    public String getName() {
        return goodsClassificationVO.getName();
    }

    @Override
    public String getType() {
        return "";
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
