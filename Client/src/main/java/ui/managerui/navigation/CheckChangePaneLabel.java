package ui.managerui.navigation;

import ui.managerui.checkui.CheckListPane;
import ui.util.Refreshable;

public class CheckChangePaneLabel extends ChangePaneLabel {
    @Override
    public Refreshable getPane() {
        return new CheckListPane();
    }
}
