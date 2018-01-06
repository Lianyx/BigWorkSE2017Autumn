package ui.managerui.common.treeTableRelated;

import com.jfoenix.controls.JFXTreeTableColumn;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.StringProperty;

import java.util.List;
import java.util.function.Function;

public class SearchableStringColumn<T> extends JFXTreeTableColumn<T, String> {
    public SearchableStringColumn(String text, double width, StringProperty keyWordProperty, List<T> keywordFilterList, Function<T, String> getter) {
        super(text);
        setPrefWidth(width);
        setCellValueFactory(param -> new ReadOnlyStringWrapper(getter.apply(param.getValue().getValue())));
        setCellFactory(param -> new SearchableStringCell<>(keyWordProperty, keywordFilterList));
    }
}
