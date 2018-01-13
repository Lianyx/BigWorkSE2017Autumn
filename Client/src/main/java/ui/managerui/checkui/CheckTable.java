package ui.managerui.checkui;

import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableRow;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.StringProperty;
import ui.common.treeTableRelated.ChooseColumn;
import ui.common.treeTableRelated.MyTreeTableBorderPane;
import ui.common.treeTableRelated.SearchableStringColumn;
import ui.util.ButtonCell;
import ui.util.RefreshablePane;
import util.ReceiptState;
import vo.receiptVO.ReceiptVO;

import java.util.List;
import java.util.Set;

public class CheckTable extends MyTreeTableBorderPane<ReceiptVO> {
    public CheckTable(Set<ReceiptVO> chosenItems, StringProperty keywordProperty, List<ReceiptVO> keywordFilterList) {
        JFXTreeTableColumn<ReceiptVO, Boolean> choose = new ChooseColumn<>(chosenItems);
        JFXTreeTableColumn<ReceiptVO, String> idColumn = new SearchableStringColumn<>("编号", 200, keywordProperty, ReceiptVO::getId);
        JFXTreeTableColumn<ReceiptVO, String> lastModifiedTimeColumn = new SearchableStringColumn<>("提交时间", 100, keywordProperty, p -> p.getLastModifiedTime().toLocalDate().toString());

        JFXTreeTableColumn<ReceiptVO, String> stateColumn = new JFXTreeTableColumn<>("状态");
        stateColumn.setPrefWidth(100);
        stateColumn.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue().getValue().getReceiptState().name()));
        stateColumn.setCellFactory(param -> new ButtonCell<ReceiptVO>() {
            @Override
            public void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (item != null) {
                    setButtonStyle(ReceiptState.color.get(item));
                }
            }
        });

        myTreeTable.getColumns().addAll(choose, idColumn, lastModifiedTimeColumn, stateColumn);
    }

    @Override
    protected void clickTwiceAftermath(JFXTreeTableRow<ReceiptVO> row) {
        ((RefreshablePane)row.getTreeItem().getValue().getDetailPane()).refresh(false);
    }
}
