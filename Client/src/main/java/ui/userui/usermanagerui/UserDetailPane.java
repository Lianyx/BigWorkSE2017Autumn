package ui.userui.usermanagerui;

import blService.userblService.UserManagerblService;
import com.jfoenix.controls.*;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.FileChooser;
import javafx.util.StringConverter;
import org.controlsfx.control.textfield.TextFields;
import ui.util.HistoricalRecord;
import ui.util.Refreshable;
import util.UserCategory;
import vo.UserListVO;
import vo.UserVO;

import java.io.File;

public class UserDetailPane  extends Refreshable {

    UserVO userVO;

    int userId;

    final FileChooser fileChooser = new FileChooser();

    UserManagerblService userManagerblService;

    BoardController boardController;

    StackPane mainpane;

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
    JFXButton modify;
    @FXML
    TextArea comment;
    @FXML
    Label password;
    @FXML
    JFXButton save;

    @FXML
    MaterialDesignIconView pen;


    SimpleBooleanProperty modifyState = new SimpleBooleanProperty(false);

    public UserDetailPane(int id,UserManagerblService userManagerblService,BoardController boardController,StackPane mainpane) {
        super();
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/userui/userdetail.fxml"));
            fxmlLoader.setRoot(this);
            fxmlLoader.setController(this);
            fxmlLoader.load();
        }catch (Exception e){
            e.printStackTrace();
        }
        this.userManagerblService = userManagerblService;
        this.mainpane = mainpane;
        this.boardController = boardController;
        this.userId = id;

        usertype.getItems().add(new Label(UserCategory.InventoryManager.name()));
        usertype.getItems().add(new Label(UserCategory.Salesman.name()));
        usertype.getItems().add(new Label(UserCategory.SalesManager.name()));
        usertype.getItems().add(new Label(UserCategory.Accountant.name()));
        usertype.getItems().add(new Label(UserCategory.GeneralManager.name()));


/*

        username.setText(userVO.getUsername());
        usertype.getSelectionModel().select(userVO.getUsertype().ordinal());
        email.setText(userVO.getEmail());
        phone.setText(userVO.getPhone());
        comment.setText(userVO.getComment());
        password.setText(userVO.getPassword());
        imageview.setImage(userVO.getImage());
*/

        username.disableProperty().bind(modifyState.not());
        usertype.disableProperty().bind(modifyState.not());
        email.disableProperty().bind(modifyState.not());
        phone.disableProperty().bind(modifyState.not());
        phone.disableProperty().bind(modifyState.not());
        comment.disableProperty().bind(modifyState.not());
        save.visibleProperty().bind(modifyState);
        choose.visibleProperty().bind(modifyState);
        reset.visibleProperty().bind(modifyState);




    }
    public void setMainpane(StackPane mainpane) {
        this.mainpane = mainpane;
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

    @FXML
    public void modify(){
       modifyState.setValue(!modifyState.getValue());
       if(modifyState.getValue()==true){
           modify.setBackground(new Background(new BackgroundFill(Color.valueOf("#03A9F4"), modify.getBackground().getFills().get(0).getRadii(), null)));
           pen.setFill(Paint.valueOf("#FFFFFF"));
       }else{
           modify.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, modify.getBackground().getFills().get(0).getRadii(), null)));
           pen.setFill(Paint.valueOf("#000000"));
       }
    }
    public void switchPane(boolean toSwtich){
        if(toSwtich==true){
            System.out.println("??/**/");
            boardController.switchTo(this);
        }else{
            boardController.setAll(this);
        }
    }

    @Override
    public void refresh(boolean toSwitch) {
        boardController.Loading();
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
        }
    }

    @FXML
    public void save(){
        JFXDialog dialog = new JFXDialog(mainpane, new BorderPane(new Loading()), JFXDialog.DialogTransition.CENTER);
        dialog.show();
    }

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
    }
}
