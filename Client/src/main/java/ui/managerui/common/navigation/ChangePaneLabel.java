package ui.managerui.common.navigation;

import javafx.scene.control.Label;
import ui.util.Refreshable;

public abstract class ChangePaneLabel extends Label {
    public abstract Refreshable getPane();
}
