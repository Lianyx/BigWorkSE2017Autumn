package ui.managerui.businessProgressui;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import org.controlsfx.control.PopOver;
import ui.managerui.common.MyBoardController;
import ui.managerui.promotionui.PromotionFilterPane;
import ui.util.Refreshable;
import util.ReceiptSearchCondition;

import java.io.IOException;

public class BusinessProgressPane extends Refreshable {
    @FXML
    private JFXButton filter;

    private ReceiptSearchCondition searchCondition;

    public BusinessProgressPane() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/managerui/businessProgressPane.fxml"));
            fxmlLoader.setRoot(this);
            fxmlLoader.setController(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        searchCondition = new ReceiptSearchCondition();

        PopOver filterPopOver = new PopOver();
        filterPopOver.setDetachable(false);
        filterPopOver.setArrowLocation(PopOver.ArrowLocation.TOP_RIGHT);
        BusinessProgressFilterPane businessProgressFilterPane = new BusinessProgressFilterPane(this, searchCondition);
        filterPopOver.setContentNode(businessProgressFilterPane);
        filter.setOnAction(e -> filterPopOver.show(filter));
    }

    @Override
    public void refresh(boolean toSwitch) {
        MyBoardController myBoardController = MyBoardController.getMyBoardController();
        myBoardController.switchTo(this);
    }
}