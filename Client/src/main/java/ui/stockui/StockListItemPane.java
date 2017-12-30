package ui.stockui;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import vo.ListGoodsItemVO;

public class StockListItemPane extends AnchorPane {


    ListGoodsItemVO listGoodsItemVO;


    public StockListItemPane(ListGoodsItemVO listGoodsItemVO){
        super();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/stockui/listitempane.fxml"));
            fxmlLoader.setRoot(this);
            fxmlLoader.setController(this);
            fxmlLoader.load();
        }catch (Exception e){
            e.printStackTrace();
        }
        this.listGoodsItemVO = listGoodsItemVO;

    }

}
