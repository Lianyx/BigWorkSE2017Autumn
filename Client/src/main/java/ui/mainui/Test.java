package ui.mainui;

import businesslogic.logbl.Logbl;
import com.jfoenix.controls.JFXDialog;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import po.LogPO;
import ui.logui.LogPane;
import ui.salesui.PromotionMasterDetail;
import util.EventCategory;
import util.UserCategory;

import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Test extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        StackPane root = new StackPane();
        Carousel carousel = new Carousel();
        root.getChildren().add(carousel);
        Scene scene=new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
