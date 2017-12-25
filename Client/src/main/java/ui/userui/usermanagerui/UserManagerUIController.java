package ui.userui.usermanagerui;

import blService.userblService.UserManagerblService;
import blServiceStub.usermanagerblService_Stub.Usermanagerblservice_Stub;
import com.jfoenix.controls.*;
import javafx.application.Platform;
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
import ui.util.*;

import java.net.URL;
import java.util.ResourceBundle;


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



    UserManagerblService usermanagerblservice_stub;


    @Override
    public void initialize(URL location, ResourceBundle resources) {


        bar.setBoardController(boardController);
        usermanagerblservice_stub = new Usermanagerblservice_Stub();


        //set default pane
        try {

            boardController.setPaneSwitchAnimation(new PaneSwitchAnimation(Duration.millis(150),  board));
            UserListPane userListPane = new UserListPane(usermanagerblservice_stub,boardController);
            userListPane.setMainpane(mainpane);
            board.getChildren().setAll(userListPane);
            HistoricalRecord.addPane(userListPane);
            navigation.getSelectionModel().selectedItemProperty().addListener((o, oldVal, newVal) -> {
                new Thread(() -> {
                    Platform.runLater(() -> {
                        try {
                            if (newVal != null) {
                              /*  if (newVal.getId().equals("usermodify")) {
                                    boardController.switchTo(new UserModifyPane());
                                } else*/ if (newVal.getId().equals("userlist")) {
                                    UserListPane listPane = new UserListPane(usermanagerblservice_stub,boardController);
                                    userListPane.setMainpane(mainpane);
                                    boardController.switchTo(listPane);
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


