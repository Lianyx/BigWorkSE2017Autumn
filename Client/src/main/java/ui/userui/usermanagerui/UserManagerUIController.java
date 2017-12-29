package ui.userui.usermanagerui;

import blService.userblService.UserManagerblService;
import blServiceStub.usermanagerblService_Stub.Usermanagerblservice_Stub;
import com.jfoenix.controls.*;
import javafx.application.Platform;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.PopOver;
import ui.stockui.StockReceiptPane;
import ui.util.*;
import vo.UserListVO;
import vo.UserVO;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Set;


public class UserManagerUIController implements Initializable{



    @FXML
    AnchorPane UserList;

    @FXML
    TopBar bar;

    @FXML
    JFXListView<Label> navigation;

    @FXML
    Label userlist;

    @FXML
    StackPane board;

    @FXML
    StackPane mainpane;

    @FXML
    private BoardController boardController;


    Set<UserListVO> set;

    UserManagerblService userManagerblService;


    @Override
    public void initialize(URL location, ResourceBundle resources) {


        bar.setBoardController(boardController);
        userManagerblService = new Usermanagerblservice_Stub();


        //set default pane
        try {

            boardController.setPaneSwitchAnimation(new PaneSwitchAnimation(Duration.millis(150),  board));
            try {
                UserListPane userListPane = new UserListPane( userManagerblService, boardController,mainpane);
                userListPane.historyAdd=true;
                userListPane.refresh(false);
            //    board.getChildren().setAll(userListPane);
           //     HistoricalRecord.addPane(userListPane);
            //    board.getChildren().setAll(new StockReceiptPane());
            }catch (Exception e){
                e.printStackTrace();
            }
            navigation.getSelectionModel().selectedItemProperty().addListener((o, oldVal, newVal) -> {
                new Thread(() -> {
                    Platform.runLater(() -> {
                        try {
                            if (newVal != null) {
                                if (newVal.getId().equals("userlist")) {
                                    UserListPane userListPane = new UserListPane(userManagerblService,boardController,mainpane);
                                    userListPane.refresh(true);
                                }


                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    });
                }).start();
            });


        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}


