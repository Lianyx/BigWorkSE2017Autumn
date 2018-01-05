package ui.userui.usermanagerui;

import blService.blServiceFactory.ServiceFactory_Stub;
import blService.userblService.UserManagerblService;
import com.jfoenix.controls.*;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import org.controlsfx.control.PopOver;
import ui.util.*;
import util.UserCategory;
import vo.UserListVO;
import vo.UserSearchVO;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class UserListPane extends ReceiptListPane<UserListVO>{

    static Set<UserListVO> set = new HashSet<>();

    UserManagerblService userManagerblService;

    private static UserSearchVO userSearchVO = new UserSearchVO();

    private static FilterPane filterPane ;

    SimpleStringProperty match = new SimpleStringProperty("");

    public boolean historyAdd = false;

    public UserListPane() throws Exception{
        super("/userui/userlistpane.fxml");
        this.userManagerblService = ServiceFactory_Stub.getService(UserManagerblService.class.getName());
        receiptTreeTable = new UserTreeTable();
        receiptTreeTable.setPrefSize(600,435);
        borderpane.setTop(new BorderPane(receiptTreeTable));
        for (UserCategory userCategory : UserCategory.values()) {
            userSearchVO.getUserCategories().add(userCategory);
        }
        FilterPane slp = new FilterPane(filterPopOver, userSearchVO);
        filterPopOver.setDetachable(false);
        filterPopOver.setArrowLocation(PopOver.ArrowLocation.TOP_RIGHT);
        filterPopOver.setContentNode(slp);
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
        if (searchField.getText() != ""&&searchField.getText() != null) {
            match.setValue(searchField.getText().toLowerCase());
            Set<UserListVO> hashSet;
            hashSet = set.stream().filter(
                    s -> s.getUserCategory().name().toLowerCase().contains(match.get()) ||
                            s.getUsername().toLowerCase().contains(match.get())||
                            s.getPhone().toLowerCase().contains(match.get())||
                            s.getEmail().toLowerCase().contains(match.get())
            ).collect(Collectors.toSet());
            receiptTreeTable.setReceipts(hashSet);
            pagination.setPageCount(receiptTreeTable.getObservableList().size() / receiptTreeTable.getRowsPerPage() + 1);
            receiptTreeTable.createPage(0);
            borderpane.setBottom(pagination);
            switchPane(false);
        }
    }
    public void add(){

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
                if ((set = userManagerblService.search(userSearchVO)) != null) {
                    System.out.println(set.size());
                    return true;
                }
                return false;
            };
            GetTask<HashSet<UserListVO>, UserManagerblService> getTask =
                    new GetTask<>(() -> {
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
       /*  try {


            LoadingTask task = new LoadingTask();
            task.valueProperty().addListener(new ChangeListener<Boolean>() {
                @Override
                public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                    if (task.getIntegerProperty() == 1) {
                        try {
                            ulv.setUser(set);
                            pagination = new Pagination((ulv.getObservableList().size() /ulv.getRowsPerPage()+1 ), 0);
                            pagination.setPrefWidth(600);
                            borderpane.setCenter(pagination);
                            switchPane(toSwitch);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else if (task.getIntegerProperty() == 0) {
                        try {
                            JFXDialogLayout jfxDialogLayout = new JFXDialogLayout();
                            jfxDialogLayout.setHeading(new Label("Wrong"));
                            jfxDialogLayout.setBody(new Label("sabi"));
                            JFXButton button = new JFXButton("Last");
                            JFXButton re = new JFXButton("Re");
                            JFXDialog dialog = new JFXDialog(mainpane, jfxDialogLayout, JFXDialog.DialogTransition.CENTER);
                            button.setOnAction(e -> {
                                dialog.close();
                                boardController.Ret();
                            });
                            re.setOnAction(e -> {
                                dialog.close();
                                refresh(false);
                            });
                            jfxDialogLayout.setActions(button, re);
                            dialog.show();

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            });

            new Thread(task).start();
        }catch (Exception e){

            e.printStackTrace();
            /*  JFXDialogLayout scare = new JFXDialogLayout();
            scare.setHeading(new Label("Wrong"));
            scare.setBody(new Label("Runtimerro"));
            JFXDialog d = new JFXDialog(mainpane,scare, JFXDialog.DialogTransition.CENTER);
            d.show();
            new Thread(() -> {
                Platform.runLater(() -> {
                    try {
                        Thread.sleep(1000);

                    }catch (Exception e1){
                        e1.printStackTrace();
                    }
                });}).start();*/

    }

/*
    class LoadingTask extends Task<Boolean> {

        private SimpleIntegerProperty integerProperty = new SimpleIntegerProperty(-1);



        public int getIntegerProperty() {
            return integerProperty.get();
        }

        public SimpleIntegerProperty integerPropertyProperty() {
            return integerProperty;
        }

        @Override
        protected Boolean call() throws Exception{
            set = userManagerblService.getAll();
            if(set!=null){
                Thread.sleep(2000);
                integerProperty.setValue(1);
                return true;
            }else {
                Thread.sleep(2000);
                integerProperty.set(0);
                return false;
            }
        }
    }*/



}
