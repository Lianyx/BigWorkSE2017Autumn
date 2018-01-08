package ui.managerui.businessProgressui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import ui.util.Refreshable;

import java.io.IOException;

public class BusinessProgressFilterPane extends AnchorPane {
    @FXML
    private JFXDatePicker beginTime, endTime;
    @FXML
    private JFXButton select;


    public BusinessProgressFilterPane(BusinessProgressPane businessProgressPane) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/managerui/businessProgressFilterPane.fxml"));
            fxmlLoader.setRoot(this);
            fxmlLoader.setController(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        select.setOnAction(e -> businessProgressPane.refresh(false));
    }
}
