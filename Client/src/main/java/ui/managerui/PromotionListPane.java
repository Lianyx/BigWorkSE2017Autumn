package ui.managerui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Pagination;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import ui.userui.usermanagerui.BoardController;
import ui.userui.usermanagerui.UserListPane;
import ui.util.Refreshable;

import java.io.IOException;

public class PromotionListPane extends Refreshable { // TODO 抽象类？
    private StackPane mainpane;
    private Pagination pagination;
    private PromotionTreeTable ulv;

    @FXML
    private BorderPane borderPane;

    private BoardController boardController;

    PromotionListPane(StackPane mainpane, BoardController boardController) {
        this.mainpane = mainpane;
        this.boardController = boardController;

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/managerui/promotionListPane.fxml"));
            fxmlLoader.setRoot(this);
            fxmlLoader.setController(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

//        pagination = new Pagination((ulv.getObservableList().size() /ulv.getRowsPerPage()+1 ), 0);
//        pagination.setPageFactory(ulv::createPage);
//        pagination.setPrefSize(600,450);
//        borderPane.setCenter(pagination);
    }

    @FXML
    public void delete() {

    }

    @FXML
    public void add() {

    }

    @Override
    public void refresh(boolean toSwtich) {

    }
}
