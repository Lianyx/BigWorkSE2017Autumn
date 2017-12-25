package ui.managerui;

import com.jfoenix.controls.JFXListView;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import ui.userui.usermanagerui.BoardController;
import ui.util.HistoricalRecord;
import ui.util.TopBar;


public class ManagerUIController {
    @FXML
    private JFXListView<Label> navigation;
    @FXML
    private StackPane mainpane;
    @FXML
    private TopBar bar;
    @FXML
    private StackPane board;
    @FXML
    private BoardController boardController;

    @FXML
    public void initialize() {
        bar.setBoardController(boardController);
        // TODO 实际上默认的是checkListPane，但先用promotion来测试，不过就算不改也无伤大雅
        PromotionListPane initialPane = new PromotionListPane(mainpane, boardController);

        // 这里感觉好坑啊？
        board.getChildren().setAll(initialPane);
        HistoricalRecord.addPane(initialPane);
        boardController.switchTo(initialPane);

        navigation.getSelectionModel().selectedItemProperty().addListener((o, oldVal, newVal) -> {
            new Thread(() -> {
                Platform.runLater(() -> {
                    if (newVal != null) {
                        if (newVal.getText().equals("审批单据")) {
                            System.out.println("审批单据");
                        } else if (newVal.getText().equals("促销策略")) {
                            System.out.println("促销微略");
                        }
                    }
                });
            }).start();
        });
    }

}
