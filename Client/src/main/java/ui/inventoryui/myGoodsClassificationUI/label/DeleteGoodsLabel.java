package ui.inventoryui.myGoodsClassificationUI.label;

import ui.common.mixer.FXMLLoadableMixer;

public class DeleteGoodsLabel extends GoodsPopUpListLabel implements FXMLLoadableMixer {
    public DeleteGoodsLabel() {
        load();
    }

    @Override
    public String publicGetURL() {
        return "/inventoryui/goodui/myDeleteGoodsLabel.fxml";
    }

    @Override
    public void clickAction() {
        System.out.println("Delete Goods Label");
    }
}
