package ui.common;

import javafx.fxml.FXMLLoader;
import ui.util.Refreshable;

import java.io.IOException;

public abstract class FXMLRefreshableAnchorPane extends Refreshable {
    public FXMLRefreshableAnchorPane() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(getURL()));
            fxmlLoader.setRoot(this);
            fxmlLoader.setController(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected abstract String getURL();
}
