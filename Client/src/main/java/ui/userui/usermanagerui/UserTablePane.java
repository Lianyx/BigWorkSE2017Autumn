package ui.userui.usermanagerui;

import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableRow;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.StringProperty;
import ui.managerui.common.treeTableRelated.ChooseColumn;
import ui.managerui.common.treeTableRelated.ImageColumn;
import ui.managerui.common.treeTableRelated.MyTreeTableBorderPane;
import ui.managerui.common.treeTableRelated.SearchableStringColumn;
import ui.util.ButtonCell;
import ui.util.Refreshable;
import util.UserCategory;
import vo.UserListVO;

import java.util.Set;

public class UserTablePane extends MyTreeTableBorderPane<UserListVO> {

    public UserTablePane(Set<UserListVO> chosenItems, StringProperty keywordProperty){

        JFXTreeTableColumn<UserListVO, Boolean> choose = new ChooseColumn<>(chosenItems);
        JFXTreeTableColumn image = new ImageColumn("姓名");
        JFXTreeTableColumn<UserListVO, String> userName = new SearchableStringColumn<>(" ", 100, keywordProperty, p -> p.getUserName());
        JFXTreeTableColumn<UserListVO, String> phone = new SearchableStringColumn<>("电话", 100, keywordProperty, p -> p.getPhone());
        JFXTreeTableColumn<UserListVO, String> stateColumn = new JFXTreeTableColumn<>("类型");
        stateColumn.setPrefWidth(100);
        stateColumn.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue().getValue().getUserCategory().name()));
        stateColumn.setCellFactory(param -> new ButtonCell<UserListVO>() {
            @Override
            public void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (item != null) {
                    setButtonStyle(UserCategory.color.get(item));
                }
            }
        });

        myTreeTable.getColumns().addAll(choose,image,userName, stateColumn,phone);
    }
    @Override
    protected void clickTwiceAftermath(JFXTreeTableRow<UserListVO> row) {
        UserDetailPane userDetailPane = new UserDetailPane(row.getTreeItem().getValue().toVO());
        userDetailPane.refresh(false);
    }
}
