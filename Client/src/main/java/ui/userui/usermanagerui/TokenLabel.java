package ui.userui.usermanagerui;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

public class TokenLabel extends Label {

    @FXML
    Circle token;
    @FXML
    Label label;


    public TokenLabel(){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/userui/tokenlabel.fxml"));
            fxmlLoader.setRoot(this);
            fxmlLoader.setController(this);
            fxmlLoader.load();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public TokenLabel(String text){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/userui/tokenlabel.fxml"));
            fxmlLoader.setRoot(this);
            fxmlLoader.setController(this);
            fxmlLoader.load();
            label.setText(text);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void setColor(Paint paint){
        token.setFill(paint);
    }
}
