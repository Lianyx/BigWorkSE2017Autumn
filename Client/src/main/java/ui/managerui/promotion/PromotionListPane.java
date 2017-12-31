package ui.managerui.promotion;

import blService.promotionblService.PromotionListblService;
import businesslogic.promotionbl.PromotionFactory;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Pagination;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import ui.userui.usermanagerui.BoardController;
import ui.util.Refreshable;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class PromotionListPane extends Refreshable { // TODO Refreshable改成接口吧？
    private StackPane mainpane;
    private Pagination pagination;
    @FXML
    private BorderPane borderPane;

    private PromotionTreeTable promotionTreeTable;
    private BoardController boardController;

    PromotionListblService promotionListblService;

    public PromotionListPane(StackPane mainpane, BoardController boardController) {
        this.mainpane = mainpane;
        this.boardController = boardController;
        try {
            promotionListblService = PromotionFactory.getPromotionListblService();
            promotionTreeTable = new PromotionTreeTable(promotionListblService.initPromotion(), boardController);
        } catch (RemoteException | NotBoundException | MalformedURLException e) {
            e.printStackTrace();
        }

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/managerui/promotionListPane.fxml"));
            fxmlLoader.setRoot(this);
            fxmlLoader.setController(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        pagination = new Pagination((promotionTreeTable.getListSize() / promotionTreeTable.getRowsPerPage() + 1), 0);
        pagination.setPageFactory(promotionTreeTable::createPage);
        pagination.setPrefSize(600, 450);
        borderPane.setCenter(pagination);
    }

    @FXML
    public void delete() {

    }

    @FXML
    public void add() {

    }


    @Override
    public void refresh(boolean toSwitch) {

    }
}
