package ui.userui.usermanagerui;

import blService.blServiceFactory.ServiceFactory_Stub;
import blService.userblService.UserManagerblService;
import com.jfoenix.controls.*;
import com.jfoenix.validation.NumberValidator;
import com.sun.org.apache.regexp.internal.RE;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.FileChooser;
import ui.util.*;
import util.UserCategory;
import vo.UserVO;

import java.io.File;
import java.time.LocalDate;
import java.util.function.Predicate;

import static ui.util.ValidatorDecorator.RequireValid;

public class UserDetailPane extends ReceiptDetailPane<UserVO> {

    int userId = -1;

    final FileChooser fileChooser = new FileChooser();

    UserManagerblService userManagerblService;

    private static String g = "";
    private static String f = "";
    private static String t = "";


    @FXML
    JFXRippler github;
    @FXML
    JFXRippler facebook;
    @FXML
    JFXRippler twitter;



    @FXML
    JFXTextField username;
    @FXML
    JFXComboBox<Label> usertype;
    @FXML
    TextField email;
    @FXML
    TextField phone;
    @FXML
    JFXButton choose;
    @FXML
    ImageView imageview;
    @FXML
    JFXButton reset;
    @FXML
    TextArea comment;
    @FXML
    Label date;
    @FXML
    Label password;

    public UserDetailPane(int id) {
        this();
        this.userId = id;
        delete.setVisible(true);
        save.setText("Save");
    }

    public UserDetailPane(){
        super("/userui/userdetail.fxml");
        userManagerblService = ServiceFactory_Stub.getService(UserManagerblService.class.getName());
        usertype.getItems().add(new Label(UserCategory.InventoryManager.name()));
        usertype.getItems().add(new Label(UserCategory.Salesman.name()));
        usertype.getItems().add(new Label(UserCategory.SalesManager.name()));
        usertype.getItems().add(new Label(UserCategory.Accountant.name()));
        usertype.getItems().add(new Label(UserCategory.GeneralManager.name()));
        usertype.getSelectionModel().select(0);

        username.disableProperty().bind(modifyState.not());
        usertype.disableProperty().bind(modifyState.not());
        email.disableProperty().bind(modifyState.not());
        phone.disableProperty().bind(modifyState.not());
        comment.disableProperty().bind(modifyState.not());
        save.visibleProperty().bind(modifyState);
        choose.visibleProperty().bind(modifyState);
        reset.visibleProperty().bind(modifyState);

        delete.setVisible(false);
        save.setText("Add");
        date.setText(LocalDate.now().toString());
        password.setText("");
        RequireValid(username);
        RequireValid(email);
        RequireValid(phone);
    }



    @FXML
    public void choosefile(){
        configureFileChooser(fileChooser);
        File file = fileChooser.showOpenDialog(this.getScene().getWindow());
        //  Image image;
        System.out.println(file.getPath());
  /*      if(image.getWidth()!=image.getHeight()){
            JFXDialog dialog=new JFXDialog();
            dialog.setTransitionType(JFXDialog.DialogTransition.CENTER);
            StackPane stackPane=new StackPane();
            stackPane.getChildren().add(new Label("you are stupid."));
            dialog.show(stackPane);
        }*/

    }

    private static void configureFileChooser(
            final FileChooser fileChooser) {
        fileChooser.setTitle("Pictures Choose");
        fileChooser.setInitialDirectory(
                new File(System.getProperty("user.home"))
        );
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Images", "*.*"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );

    }

    @Override
    public void delete() {

    }


    @Override
    public void refresh(boolean toSwitch) {
        boardController.Loading();
        try{
            DoubleButtonDialog buttonDialog =
                    new DoubleButtonDialog(mainpane,"Wrong","sabi","Last","Ret");
            buttonDialog.setButtonTwo(()->boardController.Ret());
            buttonDialog.setButtonTwo(()->refresh(false));
            Predicate<Integer> p = (i)->{if((vo = userManagerblService.showDetail(userId))!=null) return true;return false;};
            GetTask<UserVO,UserManagerblService> task =
                    new GetTask<>(()-> {
                        username.setText(vo.getUsername());
                        usertype.getSelectionModel().select(vo.getUsertype().ordinal());
                        email.setText(vo.getEmail());
                        phone.setText(vo.getPhone());
                        comment.setText(vo.getComment());
                        password.setText(vo.getPassword());
                        imageview.setImage(vo.getImage());
                        date.setText(vo.getDate());
                        g = vo.getGithub();
                        f = vo.getFacebook();
                        t = vo.getTwitter();
                        switchPane(toSwitch);
                    }, buttonDialog,p);

            new Thread(task).start();
        }catch (Exception e){
            e.printStackTrace();
        }


   /*     boardController.Loading();
        try{
            SwitchTask task = new SwitchTask(userId);
            task.valueProperty().addListener(new ChangeListener<Boolean>() {
                @Override
                public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                    if(task.getIntegerProperty()==1){
                        try{
                            userVO=task.getUserVO();
                            username.setText(userVO.getUsername());
                            usertype.getSelectionModel().select(userVO.getUsertype().ordinal());
                            email.setText(userVO.getEmail());
                            phone.setText(userVO.getPhone());
                            comment.setText(userVO.getComment());
                            password.setText(userVO.getPassword());
                            imageview.setImage(userVO.getImage());
                            switchPane(toSwitch);
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }else if(task.getIntegerProperty()==0) {
                        try {

                            JFXDialogLayout jfxDialogLayout = new JFXDialogLayout();
                            jfxDialogLayout.setHeading(new Label("Wrong"));
                            jfxDialogLayout.setBody(new Label("sabi"));
                            JFXButton button = new JFXButton("Accept");
                            JFXButton re = new JFXButton("Re");
                            JFXDialog dialog = new JFXDialog(mainpane,jfxDialogLayout,JFXDialog.DialogTransition.CENTER);
                            button.setOnAction(e->{
                                dialog.close();
                                boardController.Ret();
                            });
                            re.setOnAction(e->{
                                dialog.close();
                                refresh(false);
                            });
                            jfxDialogLayout.setActions(button,re);
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
        }*/
    }

    @Override
    public void save(){
        if(valid()){
            if(userId==-1){
                userId = userManagerblService.getId();
                userManagerblService.add(new UserVO(
                        userManagerblService.getId(),
                        imageview.getImage(),
                        username.getText(),
                        UserCategory.map.get(usertype.getSelectionModel().getSelectedItem().getText()),
                        f,g,t,
                        email.getText(),
                        phone.getText(),
                        comment.getText(),
                        date.getText(),
                        password.getText()
                ));
            }

        }else{
            OneButtonDialog oneButtonDialog = new OneButtonDialog(mainpane,"???","Stupid!","Accept");
            oneButtonDialog.setButtonOne(()->{});
        }
    }

    @Override
    public void savePendingReceipt() {
    }

    @Override
    public void saveDraftReceipt() {
    }


    @Override
    public boolean valid()
    {
        if(!username.getText().equals("")&&!password.getText().equals(""))
            return true;
        return false;
    }
/*
    private class SwitchTask extends Task<Boolean> {

        private SimpleIntegerProperty integerProperty = new SimpleIntegerProperty(-1);
        private UserVO userVO;
        private int userId;


        public SwitchTask(int id){
            this.userId=id;
        }

        public int getIntegerProperty() {
            return integerProperty.get();
        }

        public SimpleIntegerProperty integerPropertyProperty() {
            return integerProperty;
        }

        public UserVO getUserVO() {
            return userVO;
        }

        public void setUserVO(UserVO userVO) {
            this.userVO = userVO;
        }

        @Override
        protected Boolean call() throws Exception{

            if((userVO=userManagerblService.showDetail(userId))!=null){
                Thread.sleep(2000);
                integerProperty.setValue(1);
                return true;
            }else {
                Thread.sleep(2000);
                integerProperty.set(0);
                return false;
            }
        }
    }*/
}
