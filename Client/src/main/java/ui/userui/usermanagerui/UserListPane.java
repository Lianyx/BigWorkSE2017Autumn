package ui.userui.usermanagerui;

import blService.blServiceFactory.ServiceFactory_Stub;
import blService.userblService.UserManagerblService;
import businesslogic.userbl.Userbl;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import org.controlsfx.control.PopOver;
import ui.util.*;
import util.UserCategory;
import util.UserSearchCondition;
import vo.UserListVO;
import vo.UserSearchVO;

import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class UserListPane extends ReceiptListPane<UserListVO>{

    UserManagerblService userManagerblService;

    private static UserSearchCondition userSearchVO = new UserSearchCondition();

    private static FilterPane filterPane ;

    SimpleStringProperty match = new SimpleStringProperty("");

    public UserListPane() throws Exception{
        super("/userui/userlistpane.fxml");
        this.userManagerblService = new Userbl();
        receiptTreeTable = new UserTreeTable();
        receiptTreeTable.setPrefSize(600,435);
        receiptTreeTable.keywordProperty().bind(match);
        borderpane.setTop(new BorderPane(receiptTreeTable));

        filterPane = new FilterPane(filterPopOver, userSearchVO);
        filterPopOver.setDetachable(false);
        filterPopOver.setArrowLocation(PopOver.ArrowLocation.TOP_RIGHT);
        filterPopOver.setContentNode(filterPane);
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

}
