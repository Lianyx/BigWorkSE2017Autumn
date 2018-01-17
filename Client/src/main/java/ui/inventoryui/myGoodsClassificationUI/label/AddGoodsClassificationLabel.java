package ui.inventoryui.myGoodsClassificationUI.label;

import ui.common.mixer.FXMLLoadableMixer;
import vo.inventoryVO.MyGoodsClassificationVO;

public class AddGoodsClassificationLabel extends GoodsPopUpListLabel implements FXMLLoadableMixer{
    private MyGoodsClassificationVO goodsClassificationVO;

    public AddGoodsClassificationLabel(MyGoodsClassificationVO goodsClassificationVO) {
        load();

        this.goodsClassificationVO = goodsClassificationVO;
    }

    @Override
    public String publicGetURL() {
        return "/inventoryui/goodui/myAddGoodsClassificationLabel.fxml";
    }

    @Override
    public void clickAction() {
        System.out.println("addGoodsClassification");
//        if (goodsClassificationVO)
    }
}
