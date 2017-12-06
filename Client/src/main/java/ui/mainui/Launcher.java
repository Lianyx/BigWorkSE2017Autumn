package ui.mainui;

import com.jfoenix.controls.JFXDecorator;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import ui.userui.usermanagerui.UserManagerUIController;

public class Launcher extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    double x1;
    double y1;
    double x_stage;
    double y_stage;




    @Override
    public void start(Stage primaryStage) {
        try{
            Parent root = FXMLLoader.load(getClass().getResource("/userui/usermanager.fxml"));
            Scene scene=new Scene(root);
            scene.setOnMouseDragged(new EventHandler<MouseEvent>() {
                @Override public void handle(MouseEvent m) {
                    primaryStage.setX(x_stage + m.getScreenX() - x1);
                    primaryStage.setY(y_stage + m.getScreenY() - y1);

                }
            });
            scene.setOnDragEntered(null);
            scene.setOnMousePressed(new EventHandler<MouseEvent>() {

                @Override public void handle(MouseEvent m) {
                    x1 =m.getScreenX();
                    y1 =m.getScreenY();
                    x_stage = primaryStage.getX();
                    y_stage = primaryStage.getY();
                }
            });

            scene.setFill(Color.TRANSPARENT);
            primaryStage.setScene(scene);
            primaryStage.initStyle(StageStyle.TRANSPARENT);
            primaryStage.show();

        } catch(Exception e) {
            e.printStackTrace();
        }

    }
}
