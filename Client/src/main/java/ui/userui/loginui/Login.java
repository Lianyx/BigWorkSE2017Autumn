package ui.userui.loginui;

import blService.userblService.LoginblService;
import blServiceStub.loginblservice_Stub.LoginblService_Stub;
import com.jfoenix.controls.*;
import java.io.*;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import ui.util.*;
import util.ResultMessage;

import java.net.URL;
import java.util.ResourceBundle;

import static ui.util.SetDraggable.setDraggable;

public class Login implements Initializable{
    @FXML
    AnchorPane pane;
    @FXML
    JFXRippler close;
    @FXML
    JFXRippler hide;
    @FXML
    JFXButton login;
    @FXML
    JFXTextField account;
    @FXML
    JFXPasswordField password;

    @FXML
    JFXCheckBox keep;

    LoginblService ls =new LoginblService_Stub();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            DataInputStream dataInputStream = new DataInputStream(new FileInputStream(new File(getClass().getResource("/default/userinfo").toURI())));
            String accounttext = dataInputStream.readLine();
            String passwordtext = dataInputStream.readLine();
            if(accounttext!=null){
                accounttext=deleteEven(accounttext);
                account.setText(accounttext);
            }
            if(passwordtext!=null){
                passwordtext=deleteEven(passwordtext);
                password.setText(passwordtext);
                keep.selectedProperty().setValue(true);
            }

            dataInputStream.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @FXML
    public void close(){
        Stage stage = (Stage) close.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void login() throws Exception{
        LoginTask task = new LoginTask(account.getText(),password.getText());

        pane.getChildren().remove(3,8);
        Loading loading = new Loading();
        loading.setLayoutX(95);
        loading.setLayoutY(170);
        pane.getChildren().add(loading);
        task.valueProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(task.getIntegerProperty()==0){
                    try{
                        changeToLogin();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }else if(task.getIntegerProperty()==1){
                    try{
                        DataOutputStream outputStream = new DataOutputStream(new FileOutputStream(new File(getClass().getResource("/default/userinfo").toURI())));
                        if(keep.selectedProperty().getValue()==true){
                            outputStream.writeChars(account.getText()+"\n"+password.getText());
                            outputStream.flush();
                            outputStream.close();
                        }else{
                            outputStream.writeChars("");
                            outputStream.close();
                        }


                        changeToUserManger();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        });

        new Thread(task).start();


    }

    @FXML
    public void forgetpassword(){

    }
    @FXML
    public void hide() throws Exception{
        Stage stage=(Stage)hide.getScene().getWindow();
        stage.setIconified(true);

    }

    public void changeToLogin() throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/userui/login.fxml"));
        Stage stage = (Stage) pane.getScene().getWindow();
        StackPane stackPane = PaneFactory.getLoginPane();
        stackPane.getChildren().add(root);
        Scene scene = new Scene(stackPane);
        stage.setScene(scene);
        setDraggable(scene,stage);
        OneButtonDialog dialog = new OneButtonDialog(stackPane,"Wrong","Wrong account or password...","Accept");
        dialog.setButtonOne(()->{});
        dialog.show();
    }


    public void changeTo(String url) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource(url));
        Stage stage = (Stage) pane.getScene().getWindow();
        StackPane stackPane = PaneFactory.getMainPane();
        stackPane.getChildren().add(root);
        Scene scene = new Scene(stackPane);
        NodeHolder nodeHolder = new NodeHolder(stage,Duration.millis(1000), NodeAnimation.FADE);
        nodeHolder.apply();
        stage.setScene(scene);
        setDraggable(scene,stage);
    }

    public void changeToUserManger() throws Exception{
        changeTo("/userui/usermanager.fxml");
    }

    public void changeToSales() throws Exception{
        changeTo("/salesui/sales.fxml");
    }




    public String deleteEven(String str){
        String s="";
        for(int i=1;i<str.length();i+=2){
            s+=str.charAt(i);
        }
        return s;
    }
    private class LoginTask extends Task<Boolean> {

        private SimpleIntegerProperty integerProperty = new SimpleIntegerProperty(-1);
        private String account;
        private String password;


        public LoginTask(String account,String password){
            this.account = account;
            this.password = password;


        }

        public int getIntegerProperty() {
            return integerProperty.get();
        }

        public SimpleIntegerProperty integerPropertyProperty() {
            return integerProperty;
        }

        @Override
        protected Boolean call() throws Exception {

            if(ls.login(account,password)== ResultMessage.SUCCESS){
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
