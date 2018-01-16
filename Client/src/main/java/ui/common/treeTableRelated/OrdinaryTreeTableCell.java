package ui.common.treeTableRelated;

import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TreeTableCell;

public class OrdinaryTreeTableCell<T> extends TreeTableCell<T, String> {
    private class OrdinaryLable extends Label {
        private OrdinaryLable(String text) {
            super(text);
            this.setStyle("-fx-background-color: white;-fx-text-fill: black");
        }
    }

    @Override
    protected void updateItem(String item, boolean empty) {
        if (empty) {
            setGraphic(null);
        } else {
            setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
            setGraphic(new OrdinaryLable(item));
        }
    }
}
