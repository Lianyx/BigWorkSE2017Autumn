package ui.common.treeTableRelated;

import com.jfoenix.controls.JFXPopup;
import com.jfoenix.controls.JFXTreeTableColumn;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.scene.control.TreeTableCell;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.input.MouseButton;
import ui.util.ListPopup;

import java.util.function.Function;

public class OrdinaryStringColumn<T> extends TreeTableColumn<T, String> {
    public OrdinaryStringColumn(String text, double width, String fieldName) {
        super(text);
        setPrefWidth(width);
        setCellValueFactory(new TreeItemPropertyValueFactory<>(fieldName));
    }
}
