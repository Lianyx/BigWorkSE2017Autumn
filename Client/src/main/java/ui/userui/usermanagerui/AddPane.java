package ui.userui.usermanagerui;

import blService.userblService.UserManagerblService;
import blServiceStub.usermanagerblService_Stub.Usermanagerblservice_Stub;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import util.UserCategory;
import vo.UserListVO;
import vo.UserVO;

public class AddPane extends AnchorPane {


    @FXML
    AnchorPane pane1;
    @FXML
    AnchorPane pane2;


    @FXML
    JFXTextField password;

    @FXML
    JFXTextField username;

    @FXML
    JFXComboBox usertype;
    @FXML
    JFXTextField email;
    @FXML
    JFXTextField phone;


    UserListVO userListVO;

    UserManagerblService userManagerblService;

    public AddPane(UserManagerblService userManagerblService){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/userui/addpane.fxml"));
            fxmlLoader.setRoot(this);
            fxmlLoader.setController(this);
            fxmlLoader.load();
        }catch (Exception e){
            e.printStackTrace();
        }
        this.userManagerblService = userManagerblService;
        usertype.getItems().add(new Label(UserCategory.InventoryManager.name()));
        usertype.getItems().add(new Label(UserCategory.Salesman.name()));
        usertype.getItems().add(new Label(UserCategory.SalesManager.name()));
        usertype.getItems().add(new Label(UserCategory.Accountant.name()));
        usertype.getItems().add(new Label(UserCategory.GeneralManager.name()));




        username.focusedProperty().addListener(colorFocus(pane1));
        usertype.focusedProperty().addListener(colorFocus(pane1));
        email.focusedProperty().addListener(colorFocus(pane1));
        phone.focusedProperty().addListener(colorFocus(pane1));
        password.focusedProperty().addListener(colorFocus(pane2));


    }

    public ChangeListener<Boolean> colorFocus(AnchorPane pane){
        return new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(newValue==true){
                    pane.setStyle("-fx-background-color: rgb(248,250,253);");
                }else{
                    pane.setStyle("-fx-background-color: white;");
                }
            }
        };
    }
    @FXML
    public void save(){
      userListVO = new UserListVO(username.getText(),UserCategory.map.get(((Label)usertype.getSelectionModel().getSelectedItem()).getText()),email.getText(),phone.getText());
       ((Usermanagerblservice_Stub)userManagerblService).add(userListVO);
    }

    public UserListVO getUserListVO() {
        return userListVO;
    }
}
