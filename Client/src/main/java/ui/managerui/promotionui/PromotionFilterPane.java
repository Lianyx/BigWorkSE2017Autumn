package ui.managerui.promotionui;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXDatePicker;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import vo.PromotionSearchVO;

import java.io.IOException;

public class PromotionFilterPane extends AnchorPane {
    @FXML
    private JFXCheckBox memberBox, combineBox, totalBox;
    @FXML
    private JFXDatePicker beginTime, endTime;
    private PromotionListPane promotionListPane;
//    private PromotionSearchVO promotionSearchVO;

    public PromotionFilterPane(PromotionListPane promotionListPane, PromotionSearchVO promotionSearchVO) {
//        this.promotionSearchVO = promotionSearchVO;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/managerui/promotionFilterPane.fxml"));
            fxmlLoader.setRoot(this);
            fxmlLoader.setController(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        beginTime.valueProperty().bind(promotionSearchVO.endFloorPropertyProperty());
        endTime.valueProperty().bind(promotionSearchVO.beginCeilPropertyProperty());
        memberBox.selectedProperty().bind(promotionSearchVO.containMemberPropertyProperty());
        combineBox.selectedProperty().bind(promotionSearchVO.containCombinePropertyProperty());
        combineBox.selectedProperty().bind(promotionSearchVO.containTotalPropertyProperty());
    }

    @FXML
    private void selectByCondition() {
        promotionListPane.refresh(false);
    }
}
