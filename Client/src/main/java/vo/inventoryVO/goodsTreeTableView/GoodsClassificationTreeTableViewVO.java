package vo.inventoryVO.goodsTreeTableView;

import ui.inventoryui.myGoodsClassificationUI.GoodsPopUpListView;
import ui.inventoryui.myGoodsClassificationUI.label.*;
import ui.util.ListPopup;
import vo.inventoryVO.MyGoodsClassificationVO;

public class GoodsClassificationTreeTableViewVO extends AbstractGoodsTreeTableViewVO {
    private MyGoodsClassificationVO goodsClassificationVO;

    public GoodsClassificationTreeTableViewVO(MyGoodsClassificationVO goodsClassificationVO) {
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

    @Override
    public GoodsPopUpListView clickSecondaryPopUpList() {
        GoodsPopUpListView result = new GoodsPopUpListView();

        result.getItems().add(new AddGoodsClassificationLabel(goodsClassificationVO));
        result.getItems().add(new AddGoodsLabel(goodsClassificationVO));
        result.getItems().add(new DeleteGoodsClassificationLabel());
        result.getItems().add(new DeleteGoodsLabel());
        result.getItems().add(new ModifyGoodsClassificationLabel());
        result.getItems().add(new ModifyGoodsLabel());

        return result;
    }
}
