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
import util.UserCategory;
import vo.MemberListVO;

import java.util.Set;

public class MemberTreeTable extends MyTreeTableBorderPane<MemberListVO> {

public MemberTreeTable(Set<MemberListVO> chosenItems, StringProperty keywordProperty){

        JFXTreeTableColumn<MemberListVO, Boolean> choose = new ChooseColumn<>(chosenItems);
        JFXTreeTableColumn image = new ImageColumn("姓名");
        JFXTreeTableColumn<MemberListVO, String> name = new SearchableStringColumn<>(" ", 100, keywordProperty, p -> p.getName());
        JFXTreeTableColumn<MemberListVO, String> clerk = new SearchableStringColumn<>("业务员", 100, keywordProperty, p -> p.getClerkName());
        JFXTreeTableColumn<MemberListVO, String> stateColumn = new JFXTreeTableColumn<>("类型");
        stateColumn.setPrefWidth(100);
        stateColumn.setCellValueFactory(param ->param.getValue()==null? new ReadOnlyObjectWrapper<>(""):new ReadOnlyObjectWrapper<>(param.getValue().getValue().getMemberCategory().name()));
        stateColumn.setCellFactory(param -> new ButtonCell<MemberListVO>() {
@Override
public void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);
        if (item != null) {
        setButtonStyle(UserCategory.color.get(item));
        }
        }
        });

        myTreeTable.getColumns().addAll(choose,image,name, stateColumn);
        }
@Override
protected void clickTwiceAftermath(JFXTreeTableRow<MemberListVO> row) {
        MemberDetailPane userDetailPane = new MemberDetailPane(row.getTreeItem().getValue().toVO());
        userDetailPane.refresh(false);
        }
        }/*extends MyTreeTableBorderPane<UserListVO> {

    public MemberTreeTable(Set<UserListVO> chosenItems, StringProperty keywordProperty){

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
    /*


    private MemberblService memberblService;
    private MemberSearchVO memberSearchCondition;


    public MemberTreeTable() {
        super();
        rowsPerPage = 7;
        memberblService = ServiceFactory_Stub.getService(MemberblService.class.getName());

        JFXTreeTableColumn<MemberListVO,Boolean> choose = new JFXTreeTableColumn("  ");
        choose.setPrefWidth(40);
        columnDecorator.setupCellValueFactory(choose, s -> s.selectedProperty().asObject());
        choose.setCellFactory(t->new ChooseCell<MemberListVO>(chosenItem));


        JFXTreeTableColumn image = new JFXTreeTableColumn("Name");
        image.setPrefWidth(60);
        image.setCellValueFactory(new TreeItemPropertyValueFactory<>("image"));
        image.setCellFactory(p->new ImageCell());



        JFXTreeTableColumn<MemberListVO, String> memberName = new JFXTreeTableColumn(" ");
        memberName.setPrefWidth(75);
        columnDecorator.setupCellValueFactory(memberName,s->new ReadOnlyObjectWrapper<>(s.getName()));
        memberName.setCellFactory(t->new SearchableStringCell<>(keyword));



        JFXTreeTableColumn<MemberListVO, String> memberId = new JFXTreeTableColumn("Id");
        memberId.setPrefWidth(50);
        columnDecorator.setupCellValueFactory(memberId,s->new ReadOnlyObjectWrapper<>(String.format("%05d",s.getMemberId())));
        memberId.setCellFactory(t->new SearchableStringCell<>(keyword));


        JFXTreeTableColumn<MemberListVO, String> memberType = new JFXTreeTableColumn("Type");
        memberType.setPrefWidth(120);
        columnDecorator.setupCellValueFactory(memberType,s->new ReadOnlyObjectWrapper(s.getMemberCategory().name()));
        memberType.setCellFactory(new Callback<TreeTableColumn<MemberListVO, String>, TreeTableCell<MemberListVO, String>>() {
            @Override
            public TreeTableCell<MemberListVO, String> call(TreeTableColumn<MemberListVO, String> param) {
                return new ButtonCell() {
                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if(item!=null){
                            setButtonStyle(MemberCategory.color.get(item));
                        }
                    }
                };
            }
        });


        JFXTreeTableColumn<MemberListVO, Integer> degree = new JFXTreeTableColumn("degree");
        degree.setPrefWidth(50);
        columnDecorator.setupCellValueFactory(degree,s->new ReadOnlyObjectWrapper<>(s.getDegree()));


        JFXTreeTableColumn<MemberListVO, Boolean> more = new JFXTreeTableColumn("");
        more.setPrefWidth(40);
        columnDecorator.setupCellValueFactory(more, s -> new ReadOnlyObjectWrapper(s.isMultiple()));
        more.setCellFactory(new Callback<TreeTableColumn<MemberListVO, Boolean>, TreeTableCell<MemberListVO, Boolean>>() {
            @Override
            public TreeTableCell<MemberListVO, Boolean> call(TreeTableColumn<MemberListVO, Boolean> param) {
                MultiCell multiCell = new MultiCell();
                multiCell.setRunnable1(()->{MemberDetailPane memberDetailPane = new MemberDetailPane(((MemberListVO)multiCell.getTreeTableRow().getTreeItem().getValue()).getMemberId()); memberDetailPane.refresh(true);});
                multiCell.setRunnable2(()->{
                    memberblService.delete(((MemberListVO)multiCell.getTreeTableRow().getTreeItem().getValue()).getMemberId()); BoardController.getBoardController().refresh();});
                return multiCell;
            }
        });




        this.setRowFactory(tableView-> {
            JFXTreeTableRow<MemberListVO> row = new JFXTreeTableRow();
            RowSetter(row,()->{MemberDetailPane memberDetailPane = new MemberDetailPane(row.getTreeItem().getValue().getMemberId());memberDetailPane.refresh(true);});
            return row;
        });


        JFXTreeTableColumn blank = new JFXTreeTableColumn(" ");
        blank.setPrefWidth(150);

        this.getColumns().addAll(choose,image, memberName,  memberId,memberType,degree,blank,more);

    }

    public void setUser(Set<MemberListVO> users){
        observableList.setAll(users);
    }

    public void removeUser(MemberListVO memberListVO){
        observableList.remove(memberListVO);
    }

    public MemberSearchVO getMemberSearchVO() {
        return memberSearchCondition;
    }

    public void setMemberSearchVO(MemberSearchVO memberSearchCondition) {
        this.memberSearchCondition = memberSearchCondition;
    }

    @Override
    public void delete(Pagination p) {
        chosenItem.getSet().forEach(s -> {observableList.remove(s); memberblService.delete(s.getMemberId());});
        p.setPageCount(observableList.size() / getRowsPerPage() + 1);
        createPage(p.getCurrentPageIndex());
        p.setCurrentPageIndex(p.getCurrentPageIndex());
        chosenItem.getSet().clear();
    }
*/
