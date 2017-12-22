package ui.userui.usermanagerui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRippler;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;


import javax.swing.text.IconView;
import java.net.URL;
import java.util.ResourceBundle;

public class Mytabpane implements Initializable {

    private IconButton lastClicked = null;
    private static final String DEFAULT_OPACITY = "33";

    @FXML
    IconButton rippler1;

    @FXML
    IconButton rippler2;

    @FXML
    IconButton rippler3;

    @Override
    public void initialize(URL location, ResourceBundle resources) {



        setListener(rippler1);
        setListener(rippler2);
        setListener(rippler3);
    }

    public void setListener(IconButton button){
        button.setOnMouseClicked(event -> {
            if (lastClicked != null) {
             //   lastClicked.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, lastClicked.getBackground().getFills().get(0).getRadii(), null)));
                lastClicked.setBorder(new Border(new BorderStroke(Color.TRANSPARENT, BorderStrokeStyle.NONE, lastClicked.getBorder().getStrokes().get(0).getRadii(), new BorderWidths(0))));
                lastClicked.setRipplerFill(Paint.valueOf("0x000000"));
                lastClicked.setIconFill(Paint.valueOf("0x000000"));
            }

    /*      button.setBackground(new Background(new BackgroundFill(Color.valueOf("0x000000" + DEFAULT_OPACITY),
                    button.getBackground() == null ? null : button.getBackground().getFills().get(0).getRadii(),
                    null)));*/
            button.setBorder(new Border(new BorderStroke(Color.valueOf("0x0000cd"),BorderStrokeStyle.SOLID,button.getBorder()==null?null:button.getBorder().getStrokes().get(0).getRadii(),new BorderWidths(1))));
            button.setRipplerFill(Paint.valueOf("0x0000cd"));
            button.setIconFill(Paint.valueOf("0x0000cd"));
            lastClicked = button;
        });
    }

}
