package vo.inventoryVO.goodsTreeTableView;

import ui.inventoryui.myGoodsClassificationUI.GoodsPopUpListView;

public abstract class AbstractGoodsTreeTableViewVO {
    public abstract String getName();

    public abstract String getType();

    public abstract String getNum();

    public abstract String getPurPrice();

    public abstract String getSalePrice();

    public abstract String getAlarmNumber();

    public abstract GoodsPopUpListView clickSecondaryPopUpList();

    public void clickTwiceAftermath() {
    }

}
