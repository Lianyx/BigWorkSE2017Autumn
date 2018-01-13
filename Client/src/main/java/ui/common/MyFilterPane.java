package ui.common;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import org.controlsfx.control.PopOver;
import ui.common.FXMLAnchorPane;
import ui.util.Refreshable;
import util.ReceiptState;
import util.RespectiveReceiptSearchCondition;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class MyFilterPane extends FXMLAnchorPane {
    @FXML
    JFXComboBox<Label> state;
    @FXML
    JFXDatePicker from;
    @FXML
    JFXDatePicker to;
    @FXML
    JFXButton cancel, save;

    private RespectiveReceiptSearchCondition respectiveReceiptSearchCondition;


    public MyFilterPane(PopOver popOver, Refreshable refreshable, RespectiveReceiptSearchCondition respectiveReceiptSearchCondition) {
        this.respectiveReceiptSearchCondition = respectiveReceiptSearchCondition;

        cancel.setOnAction(e -> popOver.hide());
        save.setOnAction(e -> {
            updateSearchCondition();
            popOver.hide();
            refreshable.refresh(false);
        });
    }

    @Override
    protected String getURL() {
        return "/util/filter.fxml";
    }

    private void updateSearchCondition() {
        if (from.getValue() != null) {
            respectiveReceiptSearchCondition.setCreateTimeFloor(LocalDateTime.of(from.getValue(), LocalTime.MIN));
        }
        if (to.getValue() != null) {
            respectiveReceiptSearchCondition.setCreateTimeCeil(LocalDateTime.of(to.getValue(), LocalTime.MIN));
        }
        respectiveReceiptSearchCondition.setReceiptState(ReceiptState.map.get(state.getValue().getText().toUpperCase()));
    }

    @FXML
    private void cancel() {

    }

    @FXML
    private void save() {

    }


}
