package ui.managerui.navigation;

import ui.managerui.businessConditionui.BusinessConditionPane;
import ui.util.Refreshable;

public class BusinessConditionChangePaneLabel extends ChangePaneLabel {
    @Override
    public Refreshable getPane() {
        return new BusinessConditionPane();
    }
}
