package ui.common;

import ui.common.mixer.FXMLLoadableMixer;
import ui.util.Refreshable;

public abstract class FXMLRefreshableAnchorPane extends Refreshable implements FXMLLoadableMixer {
    public FXMLRefreshableAnchorPane() {
        load();
    }

    protected abstract String getURL();

    @Override
    public String publicGetURL() {
        return getURL();
    }
}
