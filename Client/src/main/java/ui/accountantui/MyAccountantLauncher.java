package ui.accountantui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import ui.util.PaneFactory;
import ui.util.UserInfomation;
import util.UserCategory;

import static ui.util.SetDraggable.setDraggable;

public class MyAccountantLauncher extends Application {
    public void start(Stage primaryStage)throws Exception{
        UserInfomation.username = "lolix";
        UserInfomation.usertype = UserCategory.GeneralManager;
        UserInfomation.userimage = new Image("/default/timg.jpg");

        // 这里没有按照最新的main pane来写，但是等登陆集成的时候再说吧
        Parent root = FXMLLoader.load(getClass().getResource("/myAccountantui/myAccountantMain.fxml"));
        Scene scene = new Scene(root);
        setDraggable(scene, primaryStage);
        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.show();
    }
}
