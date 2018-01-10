package ui.managerui;

import com.jfoenix.controls.JFXListView;
import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;
import ui.managerui.common.MyBoardController;
import ui.managerui.common.MyTopBar;
import ui.managerui.common.navigation.ChangePaneLabel;
import ui.managerui.promotionui.PromotionListPane;
import ui.util.*;


public class ManagerUIController {
    @FXML
    private JFXListView<ChangePaneLabel> navigation;
    @FXML
    private StackPane mainpane;
    @FXML
    private MyTopBar bar;
    @FXML
    private StackPane board;
    @FXML
    private BoardController boardController;

    @FXML
    public void initialize() {
        PaneFactory.setMainPane(mainpane);

        BoardController.setBoardController(boardController);
        // 这样再set回去，以后从boardController里面拿的就都是MyBoardController了，但是以后仍然需要强转
        boardController = MyBoardController.getMyBoardController();
        BoardController.setBoardController(boardController);

        bar.setBoardController(boardController);
        // 这个是不得不set，因为是同时生成的，但是这样很不好，希望可以改掉

        PromotionListPane initialPane = new PromotionListPane();
//        boardController.switchTo(initialPane);
        initialPane.refresh(false);

        navigation.getSelectionModel().selectedItemProperty().addListener((o, oldVal, newVal) -> {
//            new Thread(() -> {
//                Platform.runLater(() -> {
//                    if (newVal != null) {
//                        if (newVal.getText().equals("审批单据")) {
//                            System.out.println("审批单据");
//                        } else if (newVal.getText().equals("促销策略")) {
//                            System.out.println("促销微略");
//                        }
//                    }
//                });
//            }).start();
            newVal.getPane().refresh(true); // 这里还是先按照refresh的写法吧
        });
    }

}
