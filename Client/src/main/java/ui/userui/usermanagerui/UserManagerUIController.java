package ui.userui.usermanagerui;

import com.jfoenix.controls.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import ui.common.BoardController;
import ui.common.MyTopBar;

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



