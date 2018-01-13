package ui.common;

import com.jfoenix.controls.JFXBadge;
import com.jfoenix.controls.JFXRippler;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import org.controlsfx.control.PopOver;
import ui.messageui.MessageListView;
import ui.util.*;

import java.io.IOException;

public class MyTopBar extends HBox {
    @FXML
    private JFXRippler closebutton;

    @FXML
    private JFXRippler hidebutton;

    @FXML
    private JFXBadge message;

    @FXML
    private JFXRippler back;
    @FXML
    private JFXRippler forward;

    @FXML
    private FontAwesomeIconView left;

    @FXML
    private FontAwesomeIconView right;

    @FXML
    private JFXRippler managerpopup;

    @FXML
    private CircleImageView user;

    @FXML
    private Label username;


    private BoardController myBoardController;

    public MyTopBar() {
        super();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/util/topbar.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();

            user.setImage(UserInfomation.userimage);
            username.setText(UserInfomation.username);


            back.disableProperty().addListener(new ChangeListener<Boolean>() {
                @Override
                public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                    if (newValue) {
                        left.setFill(Paint.valueOf("#9fc1d6"));

                    } else {
                        left.setFill(Paint.valueOf("#000000"));
                    }
                }
            });
            forward.disableProperty().addListener(new ChangeListener<Boolean>() {
                @Override
                public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                    if (newValue) {
                        right.setFill(Paint.valueOf("#9fc1d6"));
                    } else {
                        right.setFill(Paint.valueOf("#000000"));
                    }
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        PopOver messagePopOver = new PopOver();
        MessageListView messageListView = new MessageListView();
        BorderPane anchorPane = new BorderPane();

        anchorPane.setCenter(messageListView);

        anchorPane.setPadding(new Insets(10, 10, 10, 10));
        messagePopOver.setContentNode(anchorPane);
        messagePopOver.setDetachable(false);
        messagePopOver.setArrowLocation(PopOver.ArrowLocation.TOP_RIGHT);

        message.setOnMouseClicked(e -> messagePopOver.show(message));


    }

    public void setBoardController(BoardController myBoardController) {
        this.myBoardController = myBoardController;

        back.disableProperty().bind(myBoardController.canBackProperty().not());
        forward.disableProperty().bind(myBoardController.canForwardProperty().not());
    }

    @FXML
    public void close() {
        Stage stage = (Stage) closebutton.getScene().getWindow();
        stage.close();
    }


    @FXML
    public void hide() throws Exception {
        Stage stage = (Stage) hidebutton.getScene().getWindow();
        stage.setIconified(true);

    }

    @FXML
    public void goback() {
        myBoardController.goBack();
    }

    @FXML
    public void goforward() {
        myBoardController.goForward();
    }

    @FXML
    public void refresh() {
        myBoardController.refresh();
    }
}

