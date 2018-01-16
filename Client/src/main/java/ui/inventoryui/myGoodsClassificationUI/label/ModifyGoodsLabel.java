package ui.inventoryui.myGoodsClassificationUI.label;

import ui.common.mixer.FXMLLoadableMixer;

public class ModifyGoodsLabel extends GoodsPopUpListLabel implements FXMLLoadableMixer {
    public ModifyGoodsLabel() {
        load();
    }

    @Override
    public String publicGetURL() {
        return "/inventoryui/goodui/myModifyGoodsLabel.fxml";
    }

    @Override
    public void clickAction() {
        System.out.println("modify goods");
    }
}
