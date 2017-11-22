package ui.mainui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Launcher extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        try {
            // Read file fxml and draw interface.
            Parent root = FXMLLoader.load(getClass()
                    .getResource("/userui/usermanager.fxml"));

            primaryStage.setTitle("My Application");
            Scene scene=new Scene(root);
            scene.setFill(Color.TRANSPARENT);
            primaryStage.setScene(scene);
            primaryStage.initStyle(StageStyle.TRANSPARENT);
            primaryStage.show();

        } catch(Exception e) {
            e.printStackTrace();
        }

    }
}
