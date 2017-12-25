package ui.userui.usermanagerui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import org.controlsfx.control.textfield.TextFields;
import ui.util.Refreshable;
import vo.UserListVO;
import vo.UserVO;

import java.io.File;

public class UserDetailPane  extends Refreshable {

    UserVO userVO;

    UserListVO userListVO;

    final FileChooser fileChooser = new FileChooser();


    BoardController boardController;

    @FXML
    TextField username;
    @FXML
    TextField usertype;
    @FXML
    TextField email;
    @FXML
    TextField phone;
    @FXML
    JFXButton choose;
    @FXML
    ImageView imageview;
    @FXML
    JFXButton sure;
    @FXML
    JFXButton modify;

    SimpleBooleanProperty modifyState = new SimpleBooleanProperty(false);

    public UserDetailPane(UserListVO userListVO,BoardController boardController) {
        super();
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/userui/userdetail.fxml"));
            fxmlLoader.setRoot(this);
            fxmlLoader.setController(this);
            fxmlLoader.load();
        }catch (Exception e){
            e.printStackTrace();
        }
        this.userListVO=userListVO;
        this.boardController = boardController;
        username.setText(userListVO.getUsername());
        usertype.setText(userListVO.getUserCategory().name());
        email.setText(userListVO.getEmail());
        phone.setText(userListVO.getPhone());
        username.disableProperty().bind(modifyState.not());
        usertype.disableProperty().bind(modifyState.not());
        email.disableProperty().bind(modifyState.not());
        phone.disableProperty().bind(modifyState.not());
        sure.visibleProperty().bind(modifyState);


        imageview.setImage(userListVO.getImage());
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
           modify.setBackground(new Background(new BackgroundFill(Color.valueOf("#DA4CEE"), modify.getBackground() == null ? null : modify.getBackground().getFills().get(0).getRadii(), null)));
       }else{
           modify.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, modify.getBackground().getFills().get(0).getRadii(), null)));

       }
    }

    @Override
    public void refresh() {

    }
}
