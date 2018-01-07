package ui.inventoryui;

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

import java.io.IOException;

import static ui.util.SetDraggable.setDraggable;

public class Laucher extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        UserInfomation.usertype = UserCategory.InventoryManager;
        UserInfomation.username = "Tedy";
        UserInfomation.userimage = new Image("/default/light.jpg");

        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/inventoryui/inventoryMain.fxml"));
            StackPane stackPane = PaneFactory.getMainPane();
            stackPane.getChildren().add(root);
            Scene scene=new Scene(stackPane);
            setDraggable(scene,primaryStage);
            primaryStage.setScene(scene);
            primaryStage.initStyle(StageStyle.TRANSPARENT);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

