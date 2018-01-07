package ui.userui.usermanagerui;

import com.jfoenix.controls.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import ui.util.*;

import java.net.URL;
import java.util.ResourceBundle;


public class UserManagerUIController implements Initializable{



    @FXML
    TopBar bar;

    @FXML
    JFXListView<Label> navigation;


    @FXML
    StackPane board;

    @FXML
    BoardController boardController;



    @Override
    public void initialize(URL location, ResourceBundle resources) {

        BoardController.setBoardController(boardController);
        bar.setBoardController(boardController);
        //set default pane
        try {

            boardController.setPaneSwitchAnimation(new PaneSwitchAnimation(Duration.millis(150),  board));
            try {
                UserListPane userListPane = new UserListPane();
                userListPane.historyAdd = true;
                userListPane.refresh(false);


              //  board.getChildren().setAll(new StockReceiptPane(boardController,mainpane));
            }catch (Exception e){
                e.printStackTrace();
            }
            navigation.getSelectionModel().selectedItemProperty().addListener((o, oldVal, newVal) -> {
                        try {
                            if (newVal != null) {
                                if (newVal.getId().equals("userlist")) {
                                    UserListPane userListPane = new UserListPane();
                                    userListPane.refresh(true);
                                }


                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
            });


        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}


