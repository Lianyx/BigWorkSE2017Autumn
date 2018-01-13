package ui.managerui.navigation;

import ui.managerui.businessProgressui.BusinessProgressPane;
import ui.util.Refreshable;

public class BusinessProgressChangePaneLabel extends ChangePaneLabel {
    @Override
    public Refreshable getPane() {
        return new BusinessProgressPane();
    }
}
