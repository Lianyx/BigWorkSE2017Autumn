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
import util.EventCategory;
import util.UserCategory;

import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDateTime;

public class Test extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        StackPane root = new StackPane();

        Scene scene=new Scene(root);
/*
        try{
            Logbl logbl = new Logbl();
            logbl.insert(new LogPO(1, LocalDateTime.now(),"sabi", UserCategory.UserManager, EventCategory.CreateSalesReceipt,"sbbbb"));
        }catch (Exception e){
            e.printStackTrace();
        }

*/
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
