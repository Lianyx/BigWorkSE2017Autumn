package ui.inventoryui.myGoodsClassificationUI.label;

import ui.common.mixer.FXMLLoadableMixer;

public class AddGoodsLabel extends GoodsPopUpListLabel implements FXMLLoadableMixer {
    public AddGoodsLabel() {
        load();
    }

    @Override
    public String publicGetURL() {
        return "/inventoryui/goodui/myAddGoodsLabel.fxml";
    }

    @Override
    public void clickAction() {
        System.out.println("addGoods");
    }
}
