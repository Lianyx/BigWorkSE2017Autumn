package ui.userui.usermanagerui;

import blService.userblService.UserManagerblService;
import blServiceStub.usermanagerblService_Stub.Usermanagerblservice_Stub;
import com.jfoenix.controls.*;
import com.sun.javafx.scene.control.skin.FXVK;
import exception.ProblemException;
import javafx.application.Platform;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.util.Callback;
import org.controlsfx.control.PopOver;
import ui.util.HistoricalRecord;
import ui.util.Refreshable;
import vo.UserListVO;
import vo.UserVO;

import javax.annotation.PostConstruct;
import java.beans.EventHandler;
import java.util.Set;
import java.util.TreeSet;

public class UserListPane extends Refreshable{

    UserTreeTable ulv;

    @FXML
    BorderPane borderpane;

    Set<UserListVO> set;

    BoardController boardController;

    UserManagerblService userManagerblService;

    Pagination pagination;

    StackPane mainpane;

    public boolean historyAdd = false;

    @FXML
    JFXButton filter;
/*
    public UserListPane(Set<UserListVO> set,UserManagerblService userManagerblService,BoardController boardController) throws Exception{
        super();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/userui/userlistpane.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        fxmlLoader.load();
        this.userManagerblService=userManagerblService;
        this.boardController=boardController;
        this.set=set;
        ulv.setUser(set);
        ulv.setBoardController(boardController);
        ulv.setUserManagerblService(userManagerblService);
        PopOver filterPopOver = new PopOver();
        filterPopOver.setDetachable(false);
        filterPopOver.setArrowLocation(PopOver.ArrowLocation.TOP_RIGHT);
        filterPopOver.setContentNode(new FilterPane());
        filter.setOnMouseClicked(e -> filterPopOver.show(filter));
        pagination = new Pagination((ulv.getObservableList().size() /ulv.getRowsPerPage()+1 ), 0);
        pagination.setPageFactory(ulv::createPage);
        pagination.setPrefWidth(600);
        borderpane.setCenter(pagination);
    }
*/
    public UserListPane(UserManagerblService userManagerblService,BoardController boardController,StackPane mainPane) throws Exception{
        super();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/userui/userlistpane.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        fxmlLoader.load();
        this.userManagerblService=userManagerblService;
        this.boardController=boardController;
        this.mainpane=mainPane;
        ulv=new UserTreeTable(userManagerblService,boardController,mainpane);

        PopOver filterPopOver = new PopOver();
        filterPopOver.setDetachable(false);
        filterPopOver.setArrowLocation(PopOver.ArrowLocation.TOP_RIGHT);
        filterPopOver.setContentNode(new FilterPane());
        filter.setOnMouseClicked(e -> filterPopOver.show(filter));
    }

    public void setUserManagerblService(UserManagerblService userManagerblService){
        this.userManagerblService=userManagerblService;
    }

    @FXML
    public void deleteList(){
        for(UserListVO userListVO:ChosenItem.getSet()) {
            ulv.removeUser(userListVO);
            ((Usermanagerblservice_Stub)userManagerblService).delete(userListVO);
        }
        int current=pagination.getCurrentPageIndex();
        pagination = new Pagination((ulv.getObservableList().size() /ulv.getRowsPerPage()+1 ), 0);
        pagination.setPageFactory(ulv::createPage);
        pagination.setPrefWidth(600);
        borderpane.setCenter(pagination);
        if(current-1>=0)
        pagination.setCurrentPageIndex(current-1);
        else
        pagination.setCurrentPageIndex(0);
        ChosenItem.getSet().clear();
    }

    @FXML
    public void add(){
        JFXDialog dialog = new JFXDialog(mainpane,new AddPane(),JFXDialog.DialogTransition.CENTER);
        dialog.show();
    }

    public void switchPane(boolean toSwtich){
        if(toSwtich==true){
            System.out.println("??/**/");
            boardController.switchTo(this);
        }else{
            if(historyAdd){
                HistoricalRecord.addPane(this);
                historyAdd=false;
            }
            boardController.setAll(this);
        }
    }

    @Override
    public void refresh(boolean toSwitch) {
        boardController.Loading();
        try {
            LoadingTask task = new LoadingTask();
            task.valueProperty().addListener(new ChangeListener<Boolean>() {
                @Override
                public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                    if (task.getIntegerProperty() == 1) {
                        try {
                            ulv.setUser(set);
                            pagination = new Pagination((ulv.getObservableList().size() /ulv.getRowsPerPage()+1 ), 0);
                            pagination.setPageFactory(ulv::createPage);
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
                });}).start();
            System.exit(1);
            */
        }
    }


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
    }



}
