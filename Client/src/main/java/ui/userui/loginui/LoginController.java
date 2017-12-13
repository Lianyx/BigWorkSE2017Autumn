package ui.userui.loginui;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import ui.util.RippleGenerator;

public class LoginController {
    @FXML
    AnchorPane loginpane;

    public LoginController(){
        final RippleGenerator rippler = new RippleGenerator();
        loginpane.getChildren().add(rippler);
        loginpane.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent event) {
                rippler.setGeneratorCenterX(event.getSceneX());
                rippler.setGeneratorCenterY(event.getSceneY());
                rippler.createRipple();
                rippler.startGenerating();
            }
        });

        loginpane.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent event) {
                rippler.setGeneratorCenterX(event.getSceneX());
                rippler.setGeneratorCenterY(event.getSceneY());
            }
        });

        loginpane.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent event) {
                rippler.stopGenerating();
            }
        });

    }

}
