package ui.inventoryui.goodsui;

import blService.blServiceFactory.ServiceFactory_Stub;
import blService.goodsblService.GoodsblService;
import blServiceStub.goodsblservice_Stub.GoodsblService_Stub;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

import ui.userui.usermanagerui.FilterPane;
import ui.util.BoardController;
import ui.util.HistoricalRecord;
import ui.util.ReceiptListPane;
import ui.util.Refreshable;
import vo.inventoryVO.GoodSearchVO;
import vo.inventoryVO.GoodsVO;

import java.io.IOException;
import java.util.Set;
import java.util.TreeSet;

public class GoodsListPane extends ReceiptListPane<GoodsVO>{
    GoodsTreeTable glv = new GoodsTreeTable();

    GoodsblService goodsblService;


    private static GoodSearchVO goodSearchVO = new GoodSearchVO();

    private static FilterPane filterPane ;

    SimpleStringProperty match = new SimpleStringProperty("");

    public boolean historyAdd = false;

    public GoodsListPane() throws Exception {
        super("/inventoryui/goodui/goodslistpane.fxml");
        this.goodsblService = ServiceFactory_Stub.getService(GoodsblService.class.getName());

    }

    @Override
    public void refresh(boolean toSwitch) {

    }

    @Override
    public void deleteList() {

    }

    @Override
    public void search() {

    }

    @Override
    public void add() {

    }
}
