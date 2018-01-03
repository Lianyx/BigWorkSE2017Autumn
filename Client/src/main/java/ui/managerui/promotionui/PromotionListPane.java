package ui.managerui.promotionui;

import blService.promotionblService.PromotionListblService;
import businesslogic.promotionbl.PromotionFactory;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Pagination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import org.controlsfx.control.PopOver;
import ui.userui.usermanagerui.BoardController;
import ui.userui.usermanagerui.FilterPane;
import ui.util.Refreshable;
import vo.PromotionSearchVO;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class PromotionListPane extends Refreshable { // TODO Refreshable改成接口吧？
    private StackPane mainpane;
    private Pagination pagination;
    private PopOver filterPopOver;
    @FXML
    private JFXButton filter;
    @FXML
    private BorderPane borderPane;

    private PromotionTreeTable promotionTreeTable;
    private BoardController boardController;

    private PromotionListblService promotionListblService;
    private PromotionSearchVO promotionSearchVO = new PromotionSearchVO();

    public PromotionListPane(StackPane mainpane, BoardController boardController) {
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

        try {
            promotionListblService = PromotionFactory.getPromotionListblService();
            promotionTreeTable = new PromotionTreeTable(promotionListblService.initPromotion(), boardController, mainpane);
        } catch (RemoteException | NotBoundException | MalformedURLException e) {
            // TODO 而且上面要加线程?
            e.printStackTrace();
        }

        filterPopOver = new PopOver();
        filterPopOver.setDetachable(false);
        filterPopOver.setArrowLocation(PopOver.ArrowLocation.TOP_RIGHT);
        PromotionFilterPane promotionFilterPane = new PromotionFilterPane(this, promotionSearchVO);
        filterPopOver.setContentNode(promotionFilterPane);


        pagination = new Pagination((promotionTreeTable.getListSize() / promotionTreeTable.getRowsPerPage() + 1), 0);
        pagination.currentPageIndexProperty().addListener(((observable, oldValue, newValue) -> {
            promotionTreeTable.createPage(newValue.intValue());
        }));
        borderPane.setBottom(pagination);

        promotionTreeTable.setPagination(pagination); // 之后应该可以不再管pagination
        promotionTreeTable.setPrefSize(600, 450);
        borderPane.setCenter(promotionTreeTable);
    }

    @FXML
    public void delete() {

    }

    @FXML
    public void add() {

    }

    @FXML
    private void showFilterPane() {
        filterPopOver.show(filter);
    }

    @Override
    public void refresh(boolean toSwitch) {
        try {
            promotionTreeTable.refresh(promotionListblService.initPromotion());
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }
}
