package ui.inventoryui.inventoryReceiptui;

import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableRow;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.StringProperty;
import ui.managerui.common.treeTableRelated.ChooseColumn;
import ui.managerui.common.treeTableRelated.MyTreeTableBorderPane;
import ui.managerui.common.treeTableRelated.SearchableStringColumn;
import ui.util.ButtonCell;
import ui.util.Refreshable;
import util.ReceiptState;
import vo.inventoryVO.inventoryReceiptVO.InventoryDamageListVO;

import java.util.Set;

public class InventoryDamageTablePane extends MyTreeTableBorderPane<InventoryDamageListVO> {
    public InventoryDamageTablePane(Set<InventoryDamageListVO> chosenItems, StringProperty keywordProperty) {
        JFXTreeTableColumn<InventoryDamageListVO, Boolean> choose = new ChooseColumn<>(chosenItems);
        JFXTreeTableColumn<InventoryDamageListVO, String> idColumn = new SearchableStringColumn<>("编号", 200, keywordProperty, InventoryDamageListVO::getId);
        JFXTreeTableColumn<InventoryDamageListVO, String> operatorColumn = new SearchableStringColumn<>("操作员", 100, keywordProperty, p -> String.valueOf(p.getOperator()));

        JFXTreeTableColumn<InventoryDamageListVO, String> stateColumn = new JFXTreeTableColumn<>("状态");
        stateColumn.setPrefWidth(100);
        stateColumn.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue().getValue().getReceiptState().name()));
        stateColumn.setCellFactory(param -> new ButtonCell<InventoryDamageListVO>() {
            @Override
            public void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (item != null) {
                    setButtonStyle(ReceiptState.color.get(item));
                }
            }
        });






        myTreeTable.getColumns().addAll(choose, idColumn, operatorColumn, stateColumn);
    }

    @Override
    protected void clickTwiceAftermath(JFXTreeTableRow<InventoryDamageListVO> row) {
        ((Refreshable)row.getTreeItem().getValue().toVO().getDetailPane()).refresh(true);
    }
}