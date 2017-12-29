package ui.inventoryManagerui;

import blService.goodsblService.GoodsblService;
import blServiceStub.goodsblService_Stub.GoodsblService_Stub;
import businesslogic.goodsbl.Goods;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Pagination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import org.controlsfx.control.PopOver;
import ui.userui.usermanagerui.BoardController;
import ui.userui.usermanagerui.FilterPane;
import ui.util.Refreshable;
import util.GoodsList;
import vo.inventoryVO.GoodsVO;

import java.io.IOException;
import java.util.Set;
import java.util.TreeSet;

public class GoodsListPane extends Refreshable{
    GoodsTreeTable glv = new GoodsTreeTable();

    @FXML
    BorderPane borderpane;


    BoardController boardController;

    GoodsblService goodsblService;

    Pagination pagination;

    StackPane mainpane;

    @FXML
    JFXButton filter;

    public GoodsListPane(GoodsblService goodsblService,BoardController boardController) throws IOException {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/inventoryui/goodsListPane.fxml"));
            fxmlLoader.setRoot(this);
            fxmlLoader.setController(this);
            fxmlLoader.load();

            this.goodsblService = goodsblService;
            setListView();

            this.boardController = boardController;
            glv.setBoardController(boardController);


            //这是点击filter按钮后弹出的筛选小窗口，弹出小窗口均可这样做，借鉴！
            PopOver filterPopOver = new PopOver();
            filterPopOver.setDetachable(false);
            filterPopOver.setArrowLocation(PopOver.ArrowLocation.TOP_RIGHT);
            filterPopOver.setContentNode(new FilterPane());
            filter.setOnMouseClicked(e -> filterPopOver.show(filter));


           pagination = new Pagination((glv.getObservableList().size() /glv.getRowsPerPage()+1 ), 0);
           pagination.setPageFactory(glv::createPage);
           pagination.setPrefWidth(600);
           borderpane.setCenter(pagination);
    }

    public void setGoodsblService(GoodsblService goodsblService){this.goodsblService = goodsblService;}

    public void setMainpane(StackPane mainpane) {
        this.mainpane = mainpane;
    }

    public void setListView()
    {
        Set<GoodsVO> set = new TreeSet<>(goodsblService.show());
        glv.setGoods(set);
    }

    @FXML
    public void deleteList(){
        for(GoodsVO GoodsVO: GoodsChosenItem.getSet()) {
            glv.removeGood(GoodsVO);
            ((GoodsblService_Stub)goodsblService).deleteGoods(GoodsVO);
        }
        int current=pagination.getCurrentPageIndex();
        pagination = new Pagination((glv.getObservableList().size() /glv.getRowsPerPage()+1 ), 0);
        pagination.setPageFactory(glv::createPage);
        pagination.setPrefWidth(600);
        borderpane.setCenter(pagination);
        if(current-1>=0)
            pagination.setCurrentPageIndex(current-1);
        else
            pagination.setCurrentPageIndex(0);
        GoodsChosenItem.getSet().clear();
    }

    //就是方法也直接上@FXML
    @FXML
    public void add(){
        /*
        弹出的话也可以采用这种方式，dialog的方式
         */
        JFXDialog dialog = new JFXDialog(mainpane,new GoodAddPane(),JFXDialog.DialogTransition.CENTER);
        dialog.show();
    }


    @Override
    public void refresh() {
        setListView();
    }
}
