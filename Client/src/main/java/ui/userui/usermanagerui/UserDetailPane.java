package ui.userui.usermanagerui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import vo.UserListVO;
import vo.UserVO;

import java.io.File;

public class UserDetailPane extends AnchorPane{

    UserVO userVO;

    UserListVO userListVO;

    final FileChooser fileChooser = new FileChooser();


    BoardController boardController;

    @FXML
    Label username;
    @FXML
    Label usertype;
    @FXML
    JFXButton choose;
    @FXML
    ImageView imageview;

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
        try{
            boardController.switchTo(new UserModifyPane(userListVO));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
