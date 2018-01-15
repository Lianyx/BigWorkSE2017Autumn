package ui.managerui;

import com.jfoenix.controls.JFXListView;
import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;
import ui.common.BoardController;
import ui.common.MyTopBar;
import ui.common.ChangePaneLabel;
import ui.managerui.promotionui.PromotionListPane;


public class ManagerUIController {
    @FXML
    private JFXListView<ChangePaneLabel> navigation;
    @FXML
    private MyTopBar bar;
    @FXML
    private StackPane board;
    @FXML
    private BoardController boardController;

    @FXML
    public void initialize() {
        bar.setBoardController(boardController); // 这个是不得不set，因为是同时生成的，但是这样很不好，希望可以改掉

        PromotionListPane initialPane = new PromotionListPane();
        initialPane.refresh(false);

        navigation.getSelectionModel().selectedItemProperty().addListener((o, oldVal, newVal) -> {
            newVal.getPane().refresh(true); // 这里还是先按照refresh的写法吧
        });
    }

}
