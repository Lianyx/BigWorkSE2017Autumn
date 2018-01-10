package ui.common;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public abstract class FXMLAnchorPane extends AnchorPane {
    public FXMLAnchorPane() {
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
