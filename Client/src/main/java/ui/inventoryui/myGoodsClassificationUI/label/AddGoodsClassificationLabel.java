package ui.inventoryui.myGoodsClassificationUI.label;

import ui.common.mixer.FXMLLoadableMixer;

public class AddGoodsClassificationLabel extends GoodsPopUpListLabel implements FXMLLoadableMixer{
    public AddGoodsClassificationLabel() {
        load();
    }

    @Override
    public String publicGetURL() {
        return "/inventoryui/goodui/myAddGoodsClassificationLabel.fxml";
    }

    @Override
    public void clickAction() {
        System.out.println("addGoodsClassification");
    }
}
