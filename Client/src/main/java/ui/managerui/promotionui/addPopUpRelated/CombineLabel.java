package ui.managerui.promotionui.addPopUpRelated;

import ui.managerui.promotionui.promotionDetailPane.CombinePromotionDetailPane;
import ui.util.Refreshable;

public class CombineLabel extends addButton {
    public CombineLabel() {
        setText("组合商品");
    }

    @Override
    public Refreshable getPane() {
        return new CombinePromotionDetailPane();
    }
}
