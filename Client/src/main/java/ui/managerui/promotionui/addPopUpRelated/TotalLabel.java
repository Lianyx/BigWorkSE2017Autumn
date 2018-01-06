package ui.managerui.promotionui.addPopUpRelated;

import com.jfoenix.controls.JFXButton;
import ui.managerui.promotionui.promotionDetailPane.TotalPromotionDetailPane;
import ui.util.Refreshable;

public class TotalLabel extends addButton {
    public TotalLabel() {
        setText("总价促销");
    }

    @Override
    public Refreshable getPane() {
        return new TotalPromotionDetailPane();
    }
}
