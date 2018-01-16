package ui.inventoryui.myGoodsClassificationUI.label;

import ui.common.mixer.FXMLLoadableMixer;

public class ModifyGoodsClassificationLabel extends GoodsPopUpListLabel implements FXMLLoadableMixer {
    public ModifyGoodsClassificationLabel() {
        load();
    }

    @Override
    public String publicGetURL() {
        return "/inventoryui/goodui/myModifyGoodsClassificationLabel.fxml";
    }

    @Override
    public void clickAction() {
        System.out.println("modify goods classification label");
    }
}
