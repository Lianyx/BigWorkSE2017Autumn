package ui.userui.usermanagerui;

import blService.userblService.UserManagerblService;
import businesslogic.userbl.Userbl;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRippler;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import org.controlsfx.control.PopOver;
import ui.common.BoardController;
import ui.common.dialog.MyTwoButtonDialog;
import ui.util.*;
import util.UserSearchCondition;
import vo.UserListVO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class UserListPane extends RefreshablePane {

    @FXML
    private JFXRippler search;
    @FXML
    private JFXTextField searchField;
    @FXML
    private JFXButton filter;

    private UserTablePane userTreeTablePane;

    private SimpleStringProperty match = new SimpleStringProperty("");

    private UserSearchCondition userSearchCondition = new UserSearchCondition();

    private Set<UserListVO> chosenItems = new HashSet<>();

    private UserManagerblService userManagerblService;

    private UserFilterPane userFilterPane;

    private PopOver filterPopOver;

    private ArrayList<UserListVO> list;
    /**
     * Constructor related
     */

    public UserListPane() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(getURL()));
            fxmlLoader.setRoot(this);
            fxmlLoader.setController(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // TODO 这个Treetable设位置应该让配置文件来干

        initiateTreeTable();
        setFilter();
        userTreeTablePane.setLayoutX(20);
        userTreeTablePane.setLayoutY(80);
        this.getChildren().add(userTreeTablePane);
    }


    private void setFilter(){
        filterPopOver = new PopOver();
        userFilterPane = new UserFilterPane(filterPopOver, userSearchCondition);
        filterPopOver.setDetachable(false);
        filterPopOver.setArrowLocation(PopOver.ArrowLocation.TOP_RIGHT);
        filterPopOver.setContentNode(userFilterPane);
        filter.setOnMouseClicked(e -> filterPopOver.show(filter));
    }

    private static String getURL(){
        return "/myAccountantui/myReceiptListPane.fxml";
    }

    /**
     * Abstract methods
     */
    private  void initiateTreeTable(){
        userTreeTablePane = new UserTablePane(chosenItems, searchField.textProperty());
    }

    /**
     * FXML methods
     */

    @FXML
    private void deleteList() {

        new MyTwoButtonDialog("请确认删除", () -> {
            BoardController myBoardController = BoardController.getBoardController();
            myBoardController.Loading();
            ArrayList<UserListVO> tempList = new ArrayList<>();

            DoubleButtonDialog buttonDialog = new DoubleButtonDialog(PaneFactory.getMainPane(), "Wrong", "连接失败", "重试", "返回");
            buttonDialog.setButtonOne(this::deleteList);
            buttonDialog.setButtonTwo(myBoardController::Ret);

            GetTask getTask = new GetTask(() -> {
                userTreeTablePane.refresh(list);
                myBoardController.switchTo(this);

            }, buttonDialog, woid -> {
                try {
                    for (UserListVO chosenItem : new ArrayList<>(chosenItems)) {
                        userManagerblService.delete(chosenItem.toVO().getId());
                        chosenItems.remove(chosenItem);
                    }

                    if ((list = userManagerblService.search(userSearchCondition)) == null) {
                        return false;
                    }
                    return true;
                } catch (Exception e) {
                    e.printStackTrace();
                    return false;
                }
            });
            new Thread(getTask).start();
        }).show();
    }

    @FXML
    private void search() {
        if (!searchField.getText().equals("")) {
            match.setValue(searchField.getText().toLowerCase());
            ArrayList<UserListVO> templist = new ArrayList<>();
            templist = templist.stream().filter(
                    s -> s.getUserCategory().name().contains(match.get()) ||
                            s.getUserName().contains(match.get())||
                            s.getPhone().contains(match.get())||
                            s.getEmail().contains(match.get())
            ).collect(Collectors.toCollection(ArrayList::new));
            userTreeTablePane.refresh(templist);
        }
    }

    @FXML
    private void add() {
        new UserDetailPane().refresh(true);
    }

    /**
     * Refresh
     */
    @Override
    public void refresh(boolean toSwitch) {
        BoardController myBoardController = BoardController.getBoardController();
        myBoardController.Loading();

        DoubleButtonDialog buttonDialog = new DoubleButtonDialog(PaneFactory.getMainPane(), "Wrong", "连接失败", "重试", "返回");
        buttonDialog.setButtonOne(() -> refresh(false));
        buttonDialog.setButtonTwo(myBoardController::Ret);

        new Thread(new GetTask(() -> {
            userTreeTablePane.refresh(list);
            myBoardController.switchTo(this);
        }, buttonDialog, woid -> {
            try {
                if (userManagerblService == null) { // 如果这里扔出exception，十有八九是因为命名不对应。
                    userManagerblService = new Userbl();
                }
                if ((list = userManagerblService.search(userSearchCondition)) == null) {
                    return false;
                }
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        })).start();
    }
    /*
    UserManagerblService userManagerblService;

    private static UserSearchCondition userSearchVO = new UserSearchCondition();

    private static UserFilterPane userFilterPane;

    SimpleStringProperty match = new SimpleStringProperty("");

    public UserListPane() throws Exception{
        super("/userui/userlistpane.fxml");
        this.userManagerblService = new Userbl();
        receiptTreeTable = new UserTreeTable();
        receiptTreeTable.setPrefSize(600,435);
        receiptTreeTable.keywordProperty().bind(match);
        borderpane.setTop(new BorderPane(receiptTreeTable));

        userFilterPane = new UserFilterPane(filterPopOver, userSearchVO);
        filterPopOver.setDetachable(false);
        filterPopOver.setArrowLocation(PopOver.ArrowLocation.TOP_RIGHT);
        filterPopOver.setContentNode(userFilterPane);
        filter.setOnMouseClicked(e -> filterPopOver.show(filter));
    }


    @FXML
    public void deleteList(){
        DoubleButtonDialog doubleButtonDialog = new DoubleButtonDialog(mainpane,"Delete","sabi","Yes","No");
        doubleButtonDialog.setButtonOne(()->{receiptTreeTable.delete(pagination); });
        doubleButtonDialog.setButtonTwo(()->{});
        doubleButtonDialog.show();
    }

    @Override
    public void search() {
        if (!searchField.getText().equals("")) {
            match.setValue(searchField.getText().toLowerCase());
            Set<UserListVO> hashSet;
            hashSet = set.stream().filter(
                    s -> s.getUserCategory().name().contains(match.get()) ||
                            s.getUsername().contains(match.get())||
                            s.getPhone().contains(match.get())||
                            s.getEmail().contains(match.get())
            ).collect(Collectors.toSet());
            receiptTreeTable.setReceipts(hashSet);
            pagination.setPageCount(receiptTreeTable.getObservableList().size() / receiptTreeTable.getRowsPerPage() + 1);
            receiptTreeTable.createPage(0);
            borderpane.setBottom(pagination);
            switchPane(false);
        }
    }
    @Override
    public void add()
    {
        System.out.println("????");
        UserDetailPane userDetailPane = new UserDetailPane(true);
    }

    @Override
    public void refresh(boolean toSwitch) {
        boardController.Loading();
        try {
            DoubleButtonDialog buttonDialog =
                    new DoubleButtonDialog(mainpane, "Wrong", "sabi", "Last", "Ret");
            buttonDialog.setButtonTwo(() -> boardController.Ret());
            buttonDialog.setButtonTwo(() -> refresh(false));
            Predicate<Integer> p = (s) -> {
                try{
                if((set = userManagerblService.search(userSearchVO)) != null)
                    return true;
                }catch (Exception e) {
                    e.printStackTrace();
                }
                return false;
            };
            GetTask getTask =
                    new GetTask(() -> {
                        receiptTreeTable.setReceipts(set);
                        pagination.setPageCount(receiptTreeTable.getObservableList().size() / receiptTreeTable.getRowsPerPage() + 1);
                        receiptTreeTable.createPage(0);
                        borderpane.setBottom(pagination);
                        switchPane(toSwitch);
                    }, buttonDialog, p);

            new Thread(getTask).start();
        } catch (Exception e) {
            System.exit(1);
            e.printStackTrace();

        }

    }
*/
}
