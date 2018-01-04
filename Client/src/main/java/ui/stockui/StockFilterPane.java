package ui.stockui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import org.controlsfx.control.PopOver;
import ui.util.BoardController;
import util.ReceiptState;
import vo.StockSearchVO;


public class StockFilterPane extends AnchorPane {

    @FXML
    JFXCheckBox draft;
    @FXML
    JFXCheckBox rejected;
    @FXML
    JFXCheckBox approved;
    @FXML
    JFXCheckBox pending;

    @FXML
    JFXButton save;

    StockListPane slp;

    public StockSearchVO getStockSearchVO() {
        return stockSearchVO;
    }

    public void setStockSearchVO(StockSearchVO stockSearchVO) {
        this.stockSearchVO = stockSearchVO;
    }

    StockSearchVO stockSearchVO;

    PopOver popOver;

    public StockFilterPane(PopOver popOver, StockSearchVO stockSearchVO) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/stockui/stockfilter.fxml"));
            fxmlLoader.setRoot(this);
            fxmlLoader.setController(this);
            fxmlLoader.load();
            this.popOver = popOver;
            this.stockSearchVO = stockSearchVO;
            draft.setSelected(true);
            rejected.setSelected(true);
            approved.setSelected(true);
            pending.setSelected(true);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void setVO(JFXCheckBox jfxCheckBox){
        if(jfxCheckBox.isSelected()){
            stockSearchVO.getReceiptStates().add(ReceiptState.map.get(jfxCheckBox.getId().toUpperCase()));
        }else{
            if(stockSearchVO.getReceiptStates().contains(ReceiptState.map.get(jfxCheckBox.getId().toUpperCase()))){
                stockSearchVO.getReceiptStates().remove(ReceiptState.map.get(jfxCheckBox.getId().toUpperCase()));
            }
        }
    }


    @FXML
    public void save() {
        setVO(draft);
        setVO(pending);
        setVO(rejected);
        setVO(approved);
        popOver.hide();
        BoardController.getBoardController().refresh();

    }

}
