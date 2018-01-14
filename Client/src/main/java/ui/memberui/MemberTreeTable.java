package ui.memberui;

import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableRow;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.StringProperty;
import ui.common.treeTableRelated.ChooseColumn;
import ui.common.treeTableRelated.ImageColumn;
import ui.common.treeTableRelated.MyTreeTableBorderPane;
import ui.common.treeTableRelated.SearchableStringColumn;
import ui.util.*;
import util.MemberCategory;
import util.UserCategory;
import vo.MemberListVO;

import java.util.Set;

public class MemberTreeTable extends MyTreeTableBorderPane<MemberListVO> {

public MemberTreeTable(Set<MemberListVO> chosenItems, StringProperty keywordProperty){

        JFXTreeTableColumn<MemberListVO, Boolean> choose = new ChooseColumn<>(chosenItems);
        JFXTreeTableColumn image = new ImageColumn("姓名");
        JFXTreeTableColumn<MemberListVO, String> name = new SearchableStringColumn<>(" ", 60, keywordProperty, p -> p.getName());
        JFXTreeTableColumn<MemberListVO, String> clerk = new SearchableStringColumn<>("业务员", 50, keywordProperty, p -> p.getClerkName());
        JFXTreeTableColumn<MemberListVO, String> stateColumn = new JFXTreeTableColumn<>("类型");
        stateColumn.setPrefWidth(150);
        stateColumn.setCellValueFactory(param ->param.getValue()==null? new ReadOnlyObjectWrapper<>(""):new ReadOnlyObjectWrapper<>(param.getValue().getValue().getMemberCategory().name()));
        stateColumn.setCellFactory(param -> new ButtonCell<MemberListVO>() {
@Override
public void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);
        if (item != null) {
        setButtonStyle(MemberCategory.color.get(item));
        }
        }
        });
    JFXTreeTableColumn<MemberListVO, String> debt = new SearchableStringColumn<>("应收", 50, keywordProperty, p -> String.valueOf(p.getDebt()));
    JFXTreeTableColumn<MemberListVO, String> credit = new SearchableStringColumn<>("应付", 50, keywordProperty, p -> String.valueOf(p.getCredit()));




    myTreeTable.getColumns().addAll(choose,image,name,clerk ,stateColumn,debt,credit);
        }
@Override
protected void clickTwiceAftermath(JFXTreeTableRow<MemberListVO> row) {
        MemberDetailPane userDetailPane = new MemberDetailPane(row.getTreeItem().getValue().toVO());
        userDetailPane.refresh(false);
        }
}