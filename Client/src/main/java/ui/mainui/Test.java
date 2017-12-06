package ui.mainui;

import blService.userblService.UserManagerblService;
import blServiceStub.usermanagerblService_Stub.Usermanagerblservice_Stub;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Pagination;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import ui.userui.usermanagerui.UserTreeTable;

public class Test extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {


        UserManagerblService userManagerblService =new Usermanagerblservice_Stub();

        UserTreeTable userTreeTable = new UserTreeTable();
        userTreeTable.setUser(userManagerblService.getAll());
        Pagination pagination = new Pagination((userTreeTable.getObservableList().size() / userTreeTable.getRowsPerPage()+1 ), 0);
        pagination.setPageFactory(userTreeTable::createPage);
        Scene scene=new Scene(new BorderPane(pagination),400,400);

            primaryStage.setScene(scene);
            primaryStage.show();

    }
}
