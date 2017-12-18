package ui.userui.usermanagerui;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;

import java.io.IOException;

public class MessageHBox extends HBox{


    public MessageHBox(){
         super();
         FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/userui/messagehbox.fxml"));
         fxmlLoader.setRoot(this);
         fxmlLoader.setController(this);
         try {
             fxmlLoader.load();
         }
         catch (IOException e) {
              e.printStackTrace();
          }


    }
}
