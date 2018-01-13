package ui.userui.usermanagerui;

import com.jfoenix.controls.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import ui.managerui.common.MyBoardController;
import ui.managerui.common.MyTopBar;
import ui.managerui.common.navigation.ChangePaneLabel;
import ui.util.*;

import java.net.URL;
import java.util.ResourceBundle;


public class UserManagerUIController implements Initializable{


    @FXML
    private JFXListView<Label> navigation;
    @FXML
    private MyTopBar bar;
    @FXML
    private StackPane board;
    @FXML
    private BoardController boardController;



    @Override
    public void initialize(URL location, ResourceBundle resources) {


        BoardController.setBoardController(boardController);
        boardController = MyBoardController.getMyBoardController();
        // 这样再set回去，以后从boardController里面拿的就都是MyBoardController了，但是以后仍然需要强转
        BoardController.setBoardController(boardController);
        //set default pane
        // 这个是不得不set，因为是同时生成的，但是这样很不好，希望可以改掉
        bar.setBoardController(boardController);


        UserListPane initialPane = new UserListPane();
        initialPane.refresh(false);

        navigation.getSelectionModel().selectedItemProperty().addListener((o, oldVal, newVal) -> {
                if(newVal.getText().equals("UserList")){
                    UserListPane userListPane = new UserListPane();
                    userListPane.refresh(false);
                }
        });

    }

}



