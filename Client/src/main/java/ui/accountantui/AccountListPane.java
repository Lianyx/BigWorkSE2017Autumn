package ui.accountantui;

import blService.accountblService.AccountblService;
import blServiceStub.accountblservice_Stub.AccountblService_Stub;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import org.controlsfx.control.PopOver;
import ui.userui.usermanagerui.*;
import ui.util.BoardController;
import ui.util.HistoricalRecord;
import ui.util.Refreshable;
import vo.AccountListVO;

import java.util.List;

public class AccountListPane extends Refreshable{

    AccountTreeTable ulv;

    @FXML
    BorderPane borderpane;


    BoardController boardController;


    AccountblService accountblService;

    Pagination pagination;

    StackPane mainpane;

    public boolean historyAdd = false;

    List<AccountListVO> list;



    @FXML
    JFXButton filter;

    public AccountListPane(AccountblService accountblService,BoardController boardController,StackPane mainpane) throws Exception{
        super();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/accountantui/accountListPane.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        fxmlLoader.load();
        this.accountblService = accountblService;
        this.boardController = boardController;
        this.mainpane = mainpane;
        ulv = new AccountTreeTable(accountblService,boardController,mainpane);


        PopOver filterPopOver = new PopOver();
        filterPopOver.setDetachable(false);
        filterPopOver.setArrowLocation(PopOver.ArrowLocation.TOP_RIGHT);
        filter.setOnMouseClicked(e -> filterPopOver.show(filter));



    }

    public void setAccountblService(AccountblService accountblService){
        this.accountblService = accountblService;
    }

    public void setMainpane(StackPane mainpane) {
        this.mainpane = mainpane;
    }


    @FXML
    public void deleteList(){

        for(AccountListVO accountListVO: AccountChosenItem.getList()) {
            ulv.removeAccount(accountListVO);
            ((AccountblService_Stub)accountblService).delete(accountListVO.getID());
        }
        int current=pagination.getCurrentPageIndex();
        pagination = new Pagination((ulv.getObservableList().size() /ulv.getRowsPerPage()+1 ), 0);
        pagination.setPageFactory(ulv::createPage);
        pagination.setPrefSize(600,450);
        borderpane.setCenter(pagination);
        if(current-1>=0)
            pagination.setCurrentPageIndex(current-1);
        else
            pagination.setCurrentPageIndex(0);
        AccountChosenItem.getList().clear();
    }

    @FXML
    public void add(){
        AccountAddPane accountAddPane = new AccountAddPane(accountblService);
        JFXDialog dialog = new JFXDialog(mainpane,accountAddPane,JFXDialog.DialogTransition.CENTER);
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
    public void refresh(boolean toSwitch){
        boardController.Loading();
        try {
            AccountListPane.LoadingTask task = new AccountListPane.LoadingTask();
            task.valueProperty().addListener(new ChangeListener<Boolean>() {
                @Override
                public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                    if (task.getIntegerProperty() == 1) {
                        try {
                            ulv.setAccount(list);
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
            list = accountblService.showAllAccounts();
            if(list!=null){
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
