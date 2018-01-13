package ui.stockui;

import com.jfoenix.controls.*;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.StringProperty;

import ui.managerui.common.treeTableRelated.ChooseColumn;
import ui.managerui.common.treeTableRelated.MyTreeTableBorderPane;
import ui.managerui.common.treeTableRelated.SearchableStringColumn;
import ui.util.*;
import util.ReceiptState;
import vo.receiptVO.StockReceiptListVO;

import java.util.Set;

public abstract class StockTreeTable <T extends StockReceiptListVO<T>> extends MyTreeTableBorderPane<T> {



    public StockTreeTable(Set<T> chosenItems, StringProperty keywordProperty) {

        JFXTreeTableColumn<T, Boolean> choose = new ChooseColumn<>(chosenItems);
        JFXTreeTableColumn<T, String> id = new SearchableStringColumn<>("ID", 200, keywordProperty, p -> p.getId());
        JFXTreeTableColumn<T, String> member = new SearchableStringColumn<>("客户", 100, keywordProperty, p -> p.getMemberName());
        JFXTreeTableColumn<T, String> stateColumn = new JFXTreeTableColumn<>("类型");
        stateColumn.setPrefWidth(100);
        stateColumn.setCellValueFactory(param -> {
            System.out.println(param.getValue().getValue().getReceiptState().name());
            return new ReadOnlyObjectWrapper<>(param.getValue().getValue().getReceiptState().name());
        });
        stateColumn.setCellFactory(param -> new ButtonCell<T>() {
            @Override
            public void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (item != null) {
                    setButtonStyle(ReceiptState.color.get(item));
                }
            }
        });

        myTreeTable.getColumns().addAll(choose,id,member, stateColumn);
    }

}
