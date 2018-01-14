package ui.managerui.navigation;

import ui.managerui.promotionui.PromotionListPane;
import ui.util.RefreshablePane;

public class PromotionChangePaneLabel extends ChangePaneLabel {

    @Override
    public RefreshablePane getPane() {
        return new PromotionListPane();
    }
}
