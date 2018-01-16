package ui.inventoryui.myGoodsClassificationUI;

import com.jfoenix.controls.JFXListCell;
import com.jfoenix.controls.JFXListView;
import ui.common.mixer.FXMLLoadableMixer;
import ui.inventoryui.myGoodsClassificationUI.label.GoodsPopUpListLabel;

public class GoodsPopUpListView extends JFXListView<GoodsPopUpListLabel> implements FXMLLoadableMixer {

    public GoodsPopUpListView() {
        load();
        this.setCellFactory(param -> {
            JFXListCell<GoodsPopUpListLabel> cell = new JFXListCell<>();
            cell.setOnMouseClicked(e -> {
                cell.getItem().clickAction();
            });
            return cell;
        });
    }

    @Override
    public String publicGetURL() {
        return "/inventoryui/goodui/goodsPopUpList.fxml";
    }
}