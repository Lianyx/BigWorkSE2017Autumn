package ui.userui.usermanagerui;

import blServiceStub.usermanagerblService_Stub.Usermanagerblservice_Stub;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRippler;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import util.UserCategory;
import vo.UserListVO;

import java.net.URL;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.TreeSet;

public class UserManagerUIController implements Initializable{
    @FXML
    UserListView ulv;

    @FXML
    JFXRippler close;

    @FXML
    JFXButton delete;

    Usermanagerblservice_Stub usermanagerblservice_stub;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        usermanagerblservice_stub=new Usermanagerblservice_Stub();
        setListView();
    }


    public void setListView()
    {
        ulv.setUser(usermanagerblservice_stub.getAll());
    }

    @FXML
    public void deleteList(){
        for(UserListVO userListVO:ChosenItem.getSet())
        ulv.removeUser(userListVO);
        ChosenItem.getSet().clear();
    }

    @FXML
    public void close(){
        Stage stage = (Stage) close.getScene().getWindow();
        stage.close();
    }




}
