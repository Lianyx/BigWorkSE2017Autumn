package ui.mainui;
import javafx.scene.Group;
import javafx.scene.shape.Rectangle;
import ui.common.bigPane.FXMLAnchorPane;
import ui.common.mixer.FXMLLoadableMixer;


public class PromotionCard extends FXMLAnchorPane{
    public PromotionCard(){
       super();
    }

    @Override
    protected String getURL() {
        return "/salesui/promotionCard.fxml";
    }


}