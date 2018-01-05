package ui.mainui;

import com.jfoenix.controls.JFXDialog;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import ui.memberui.ChoosePane;

public class Test extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        StackPane root = new StackPane();
        Scene scene=new Scene(root,400,600);

        JFXDialog jfxDialog =new JFXDialog(root,new ChoosePane(), JFXDialog.DialogTransition.CENTER);
        primaryStage.setScene(scene);
        primaryStage.show();
        jfxDialog.show();
    }
}
