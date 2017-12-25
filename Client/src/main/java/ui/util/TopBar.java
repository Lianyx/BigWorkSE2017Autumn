package ui.util;

import com.jfoenix.controls.JFXBadge;
import com.jfoenix.controls.JFXRippler;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import org.controlsfx.control.PopOver;
import ui.userui.usermanagerui.BoardController;
import ui.userui.usermanagerui.MessageListView;

import java.io.IOException;

public class TopBar extends HBox {
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

    BoardController boardController;
    public TopBar(){
        super();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/userui/topbar.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
            user.setImage(UserInfomation.userimage);
            username.setText(UserInfomation.username);

            back.disableProperty().bind(HistoricalRecord.canBack.not());
            forward.disableProperty().bind(HistoricalRecord.canForward.not());

            back.disableProperty().addListener(new ChangeListener<Boolean>() {
                @Override
                public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                    if(newValue==true){
                        left.setFill(Paint.valueOf("#9fc1d6"));
                    }else{
                        left.setFill(Paint.valueOf("#000000"));
                    }
                }
            });
            forward.disableProperty().addListener(new ChangeListener<Boolean>() {
                @Override
                public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                    if(newValue==true){
                        right.setFill(Paint.valueOf("#9fc1d6"));
                    }else{
                        right.setFill(Paint.valueOf("#000000"));
                    }
                }
            });
        }
        catch (IOException e) {
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

        anchorPane.setPadding(new Insets(10,10,10,10));
        messagePopOver.setContentNode(anchorPane);
        messagePopOver.setDetachable(false);
        messagePopOver.setArrowLocation(PopOver.ArrowLocation.TOP_RIGHT);

        message.setOnMouseClicked(e -> messagePopOver.show(message));





    }

    public void setBoardController(BoardController boardController) {
        this.boardController = boardController;
    }

    @FXML
    public void close(){
        Stage stage = (Stage) closebutton.getScene().getWindow();
        stage.close();
    }


    @FXML
    public void hide() throws Exception{
        Stage stage=(Stage)hidebutton.getScene().getWindow();
        stage.setIconified(true);

    }

    @FXML
    public void goback(){
        boardController.setRightAnimation();
        boardController.historicalSwitchTo((AnchorPane) HistoricalRecord.pop());
    }

    @FXML
    public void goforward(){
        boardController.setLeftAnimation();
        boardController.historicalSwitchTo((AnchorPane)HistoricalRecord.push());
    }


}
