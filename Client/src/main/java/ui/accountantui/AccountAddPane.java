package ui.accountantui;

import blService.accountblService.AccountblService;
import blService.userblService.UserManagerblService;
import blServiceStub.accountblservice_Stub.AccountblService_Stub;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import util.UserCategory;
import vo.AccountListVO;
import vo.UserListVO;
import vo.UserVO;

public class AccountAddPane extends AnchorPane {

    @FXML
    AnchorPane pane1;

    @FXML
    JFXTextField name;

    @FXML
    JFXTextField balance;

    AccountListVO accountListVO;

    AccountblService accountblService;

    public AccountAddPane(AccountblService accountblService){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/accountantui/accountAddPane.fxml"));
            fxmlLoader.setRoot(this);
            fxmlLoader.setController(this);
            fxmlLoader.load();
        }catch (Exception e){
            e.printStackTrace();
        }
        this.accountblService = accountblService;

        name.focusedProperty().addListener(colorFocus(pane1));
        balance.focusedProperty().addListener(colorFocus(pane1));

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
        accountListVO = new AccountListVO(name.getText(),Double.parseDouble(balance.getText()));
        ((AccountblService_Stub)accountblService).add(accountListVO);
    }

    public AccountListVO getAccountListVO() {
        return accountListVO;
    }
}
