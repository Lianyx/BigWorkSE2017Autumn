package ui.mainui;

import businesslogic.memberbl.Memberbl;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import util.MemberSearchCondition;
import vo.MemberVO;


public class Launcher extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try{
            Parent root = FXMLLoader.load(getClass().getResource("/userui/login.fxml"));
            Scene scene=new Scene(root);
            ui.util.SetDraggable.setDraggable(scene,primaryStage);
            primaryStage.setScene(scene);
            primaryStage.initStyle(StageStyle.TRANSPARENT);
            primaryStage.show();

        } catch(Exception e) {
            e.printStackTrace();
        }

    }
}
