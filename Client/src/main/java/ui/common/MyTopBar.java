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
    JFXRippler closebutton;

    @FXML
    JFXRippler hidebutton;

    @FXML
    JFXBadge message;

    @FXML
    JFXRippler back;
    @FXML
    JFXRippler forward;

    @FXML
    FontAwesomeIconView left;

    @FXML
    FontAwesomeIconView right;

    @FXML
    JFXRippler managerpopup;

    @FXML
    CircleImageView user;

    @FXML
    Label username;

    @FXML
    JFXRippler refresh;

    MyBoardController boardController;

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
        /*
        PopOver managerPopOver = new PopOver();
        managerPopOver.setContentNode(new Manager());
        managerPopOver.setDetachable(false);
        managerPopOver.setArrowLocation(PopOver.ArrowLocation.TOP_RIGHT);

        managerpopup.setOnMouseClicked(e -> managerPopOver.show(managerpopup));

*/
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

    public void setBoardController(BoardController boardController) {
        if (boardController instanceof MyBoardController) {
            this.boardController = (MyBoardController) boardController;

            // TODO 这两句只能放这了。这样也不好，有点还不如写到boardController里面，不要内部类。不过可能如果一开始就用MyBoardController会好
            back.disableProperty().bind(this.boardController.canBackProperty().not());
            forward.disableProperty().bind(this.boardController.canForwardProperty().not());
        } else {
            System.err.println("not support !!!!!!!!!!!!" +
                    "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!" +
                    "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!" +
                    "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!" +
                    "!!!!!!!!!!!");
        }
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
        boardController.goBack();
    }

    @FXML
    public void goforward() {
        boardController.goForward();
    }

    @FXML
    public void refresh() {
        boardController.refresh();
    }
}

