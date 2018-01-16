package ui.inventoryui.myGoodsClassificationUI.label;

import ui.common.mixer.FXMLLoadableMixer;

public class DeleteGoodsClassificationLabel extends GoodsPopUpListLabel implements FXMLLoadableMixer {
    public DeleteGoodsClassificationLabel() {
        load();
    }

    @Override
    public String publicGetURL() {
        return "/inventoryui/goodui/myDeleteGoodsClassificationLabel.fxml";
    }

    @Override
    public void clickAction() {
        System.out.println("deleteGoodsClassification");
    }
}
