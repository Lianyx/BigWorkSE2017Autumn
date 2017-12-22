package ui.userui.usermanagerui;

import com.jfoenix.controls.JFXRippler;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;

import java.io.IOException;

public class IconButton extends JFXRippler {

    @FXML
    private MaterialDesignIconView view;


    public IconButton(){
        super();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/userui/iconbutton.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public IconButton(MaterialDesignIconView view){
        this();
        this.view=view;
    }
    public void setIconFill(Paint paint){
        view.setFill(paint);
    }

}
