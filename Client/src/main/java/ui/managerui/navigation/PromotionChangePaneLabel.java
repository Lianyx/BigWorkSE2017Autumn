package ui.managerui.navigation;

import ui.managerui.promotionui.PromotionListPane;
import ui.util.Refreshable;

public class PromotionChangePaneLabel extends ChangePaneLabel {

    @Override
    public Refreshable getPane() {
        return new PromotionListPane();
    }
}
