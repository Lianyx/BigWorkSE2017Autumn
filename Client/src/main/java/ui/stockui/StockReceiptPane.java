package ui.stockui;

import blService.stockblService.StockblService;
import com.jfoenix.controls.JFXRippler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import ui.userui.usermanagerui.BoardController;
import ui.util.Refreshable;
import vo.ListGoodsItemVO;

import java.nio.file.StandardWatchEventKinds;
import java.util.Stack;

public class StockReceiptPane extends Refreshable {

    @FXML
    StockListItemTreeTable stockListItemTreeTable;

    BoardController boardController;

    StockblService stockblService;

    StackPane mainpane;

    public StockReceiptPane(){
        super();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/stockui/stockreceipt.fxml"));
            fxmlLoader.setRoot(this);
            fxmlLoader.setController(this);
            fxmlLoader.load();
        }catch (Exception e){
            e.printStackTrace();
        }


    }

    public StockReceiptPane(StockblService stockblService,BoardController boardController,StackPane mainpane){
        this();
        this.stockblService = stockblService;
        this.boardController = boardController;
        this.mainpane = mainpane;
        stockListItemTreeTable.setStockblService(stockblService);
        stockListItemTreeTable.setBoardController(boardController);
        stockListItemTreeTable.setMainpane(mainpane);
    }


    public StockReceiptPane(BoardController boardController, StackPane mainpane){
        this(null,boardController,mainpane);
    }


    @FXML
    public void add(){
        stockListItemTreeTable.addGood(new ListGoodsItemVO("a",1,"a",1,1,"a"));

    }







    @Override
    public void refresh(boolean toSwitch) {

    }
}
