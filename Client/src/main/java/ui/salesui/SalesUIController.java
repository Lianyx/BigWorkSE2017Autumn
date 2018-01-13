package ui.salesui;

import com.jfoenix.controls.JFXListView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import ui.common.MyBoardController;
import ui.common.MyTopBar;
import ui.memberui.MemberListPane;
import ui.salesui.salesRetui.SalesRetListPane;
import ui.salesui.salesSellui.SalesSellListPane;
import ui.stockui.StockListPane;
import ui.stockui.stockPurui.StockPurListPane;
import ui.stockui.stockRetui.StockRetListPane;
import ui.util.BoardController;

import java.net.URL;
import java.util.ResourceBundle;

public class SalesUIController implements Initializable{

    @FXML
    private JFXListView<HBox> navigation;
    @FXML
    private MyTopBar bar;
    @FXML
    private StackPane board;
    @FXML
    private BoardController boardController;



    @Override
    public void initialize(URL location, ResourceBundle resources) {


        BoardController.setBoardController(boardController);
        boardController = MyBoardController.getMyBoardController();
        // 这样再set回去，以后从boardController里面拿的就都是MyBoardController了，但是以后仍然需要强转
        BoardController.setBoardController(boardController);
        //set default pane
        // 这个是不得不set，因为是同时生成的，但是这样很不好，希望可以改掉
        bar.setBoardController(boardController);

        StockListPane init =new StockPurListPane();
        init.refresh(true);

        navigation.getSelectionModel().selectedItemProperty().addListener((o, oldVal, newVal) -> {
            try {
                if (newVal != null) {
                    if (newVal.getId().equals("stockpur")) {
                        StockListPane stockListPane = new StockPurListPane();
                        stockListPane.refresh(true);
                    }else if(newVal.getId().equals("stockret")){
                        StockListPane stockListPane = new StockRetListPane();
                        stockListPane.refresh(true);
                    }else if(newVal.getId().equals("salessell")){
                        SalesListPane salesListPane = new SalesSellListPane();
                        salesListPane.refresh(true);
                    }else if(newVal.getId().equals("salesret")){
                        SalesListPane salesListPane = new SalesRetListPane();
                        salesListPane.refresh(true);
                    }
                    else if(newVal.getId().equals("member")){
                        MemberListPane memberListPane = new MemberListPane();
                        memberListPane.refresh(true);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

    }
    /*
    @FXML
    TopBar bar;

    @FXML
    JFXListView<HBox> navigation;

    @FXML
    StackPane board;

    @FXML
    BoardController boardController;



    @Override
    public void initialize(URL location, ResourceBundle resources) {

        BoardController.setBoardController(boardController);

        bar.setBoardController(boardController);

        //set default pane
            boardController.setPaneSwitchAnimation(new PaneSwitchAnimation(Duration.millis(150),  board));
            try{
                StockListPane stockListPane = new StockListPane(true);
                stockListPane.historyAdd = true;
                stockListPane.refresh(false);
            }catch (Exception e){
                e.printStackTrace();
            }



         navigation.getSelectionModel().selectedItemProperty().addListener((o, oldVal, newVal) -> {
                        try {
                            if (newVal != null) {
                                if (newVal.getId().equals("stockpur")) {
                                    StockListPane stockListPane = new StockListPane(true);
                                    stockListPane.refresh(true);
                                }else if(newVal.getId().equals("stockret")){
                                    StockListPane stockListPane = new StockListPane(false);
                                    stockListPane.refresh(true);
                                }else if(newVal.getId().equals("salessell")){
                                    SalesListPane salesListPane = new SalesListPane(true);
                                    salesListPane.refresh(true);
                                }else if(newVal.getId().equals("salesret")){
                                    SalesListPane salesListPane = new SalesListPane(false);
                                    salesListPane.refresh(true);
                                }else if(newVal.getId().equals("member")){
                                    MemberListPane memberListPane = new MemberListPane();
                                    memberListPane.refresh(true);
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
            });

    }*/
}
