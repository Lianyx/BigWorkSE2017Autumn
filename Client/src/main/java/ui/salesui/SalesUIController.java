package ui.salesui;

import com.jfoenix.controls.JFXListView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import ui.stockui.StockListPane;
import ui.util.BoardController;
import ui.util.PaneSwitchAnimation;
import ui.util.TopBar;
import vo.UserListVO;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Set;

public class SalesUIController implements Initializable{


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

                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
            });

    }
}
