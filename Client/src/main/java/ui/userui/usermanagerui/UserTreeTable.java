package ui.userui.usermanagerui;

import blService.blServiceFactory.ServiceFactory_Stub;
import blService.userblService.UserManagerblService;
import businesslogic.userbl.Userbl;
import com.jfoenix.controls.*;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.scene.control.*;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.util.Callback;

import javafx.util.Duration;
import ui.util.*;
import util.UserCategory;
import vo.UserListVO;
import vo.UserSearchVO;

import java.util.Set;


public class UserTreeTable extends ReceiptTreeTable<UserListVO> {
    private UserManagerblService userManagerblService;
    private UserSearchVO userSearchVO;


    public UserTreeTable() {
        super();
        rowsPerPage = 7;
        try {
            userManagerblService = new Userbl();
        }catch (Exception e){
            e.printStackTrace();
        }
        JFXTreeTableColumn<UserListVO,Boolean> choose = new JFXTreeTableColumn("  ");
        choose.setPrefWidth(40);
        columnDecorator.setupCellValueFactory(choose, s -> s.selectedProperty().asObject());
        choose.setCellFactory(t->new ChooseCell<UserListVO>(chosenItem));


        JFXTreeTableColumn image = new JFXTreeTableColumn("  ");
        image.setPrefWidth(60);
        image.setCellValueFactory(new TreeItemPropertyValueFactory<>("image"));
        image.setCellFactory(p->new ImageCell());



        JFXTreeTableColumn<UserListVO, String> username = new JFXTreeTableColumn("Username");
        username.setPrefWidth(81);
        columnDecorator.setupCellValueFactory(username,s->new ReadOnlyObjectWrapper<>(s.getUsername()));
        username.setCellFactory(t->new SearchableStringCell<>(keyword));



        JFXTreeTableColumn<UserListVO, Integer> userid = new JFXTreeTableColumn("Userid");
        userid.setPrefWidth(120);
        columnDecorator.setupCellValueFactory(userid,s->new ReadOnlyObjectWrapper<>(s.getUserid()));

        JFXTreeTableColumn<UserListVO, String> usertype = new JFXTreeTableColumn("Usertype");
        usertype.setPrefWidth(125);
        columnDecorator.setupCellValueFactory(usertype,s->new ReadOnlyObjectWrapper(s.getUserCategory().name()));
        usertype.setCellFactory(new Callback<TreeTableColumn<UserListVO, String>, TreeTableCell<UserListVO, String>>() {
            @Override
            public TreeTableCell<UserListVO, String> call(TreeTableColumn<UserListVO, String> param) {
                return new ButtonCell() {
                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if(item!=null){
                            setButtonStyle(UserCategory.color.get(item));
                        }
                    }
                };
            }
        });


        JFXTreeTableColumn<UserListVO, String> phone = new JFXTreeTableColumn("Phone");
        phone.setPrefWidth(120);
        columnDecorator.setupCellValueFactory(phone,s->new ReadOnlyObjectWrapper<>(s.getPhone()));
        phone.setCellFactory(s->new SearchableStringCell<>(keyword));


        JFXTreeTableColumn<UserListVO, Boolean> more = new JFXTreeTableColumn("");
        more.setPrefWidth(30);
        columnDecorator.setupCellValueFactory(more, s -> new ReadOnlyObjectWrapper(s.isMultiple()));
        more.setCellFactory(new Callback<TreeTableColumn<UserListVO, Boolean>, TreeTableCell<UserListVO, Boolean>>() {
            @Override
            public TreeTableCell<UserListVO, Boolean> call(TreeTableColumn<UserListVO, Boolean> param) {
                MultiCell multiCell = new MultiCell();
                multiCell.setRunnable1(()->{UserDetailPane userDetailPane = new UserDetailPane(((UserListVO)multiCell.getTreeTableRow().getTreeItem().getValue()).getUserid()); userDetailPane.refresh(true);});
                multiCell.setRunnable2(()->{
                    try {
                        userManagerblService.delete(((UserListVO) multiCell.getTreeTableRow().getTreeItem().getValue()).getUserid());
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    BoardController.getBoardController().refresh();});
                return multiCell;
            }
        });




        this.setRowFactory(tableView-> {
            JFXTreeTableRow<UserListVO> row = new JFXTreeTableRow();
            RowSetter(row,()->{UserDetailPane userDetailPane = new UserDetailPane(row.getTreeItem().getValue().getUserid());userDetailPane.refresh(true);});
            return row;
        });


        this.getColumns().addAll(choose,image, username,  userid,usertype,phone,more);

    }

    public void setUser(Set<UserListVO> users){
        observableList.setAll(users);
    }

    public void removeUser(UserListVO userListVO){
        observableList.remove(userListVO);
    }

    public UserSearchVO getUserSearchVO() {
        return userSearchVO;
    }

    public void setUserSearchVO(UserSearchVO userSearchVO) {
        this.userSearchVO = userSearchVO;
    }

    @Override
    public void delete(Pagination p) {
        chosenItem.getSet().forEach(s -> {observableList.remove(s);
        try {
            userManagerblService.delete(s.getUserid());
        }catch (Exception e){
            e.printStackTrace();
        }
        });
        p.setPageCount(observableList.size() / getRowsPerPage() + 1);
        createPage(p.getCurrentPageIndex());
        p.setCurrentPageIndex(p.getCurrentPageIndex());
        chosenItem.getSet().clear();
    }


}


