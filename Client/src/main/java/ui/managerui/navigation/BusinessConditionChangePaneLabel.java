package ui.managerui.navigation;

import ui.managerui.businessConditionui.BusinessConditionPane;
import ui.util.RefreshablePane;

public class BusinessConditionChangePaneLabel extends ChangePaneLabel {
    @Override
    public RefreshablePane getPane() {
        return new BusinessConditionPane();
    }
}
