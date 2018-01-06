package ui.salesui;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import org.controlsfx.control.PopOver;
import ui.util.BoardController;
import util.ReceiptState;
import vo.SalesSearchVO;
import vo.StockSearchVO;


public class SalesFilterPane extends AnchorPane {

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

    SalesListPane slp;


    SalesSearchVO salesSearchVO;

    PopOver popOver;

    public SalesFilterPane(PopOver popOver, SalesSearchVO salesSearchVO) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/stockui/stockfilter.fxml"));
            fxmlLoader.setRoot(this);
            fxmlLoader.setController(this);
            fxmlLoader.load();
            this.popOver = popOver;
            this.salesSearchVO = salesSearchVO;
            draft.setSelected(true);
            rejected.setSelected(true);
            approved.setSelected(true);
            pending.setSelected(true);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public SalesSearchVO getSalesSearchVO() {
        return salesSearchVO;
    }

    public void setSalesSearchVO(SalesSearchVO salesSearchVO) {
        this.salesSearchVO = salesSearchVO;
    }

    public void setVO(JFXCheckBox jfxCheckBox){
        if(jfxCheckBox.isSelected()){
            salesSearchVO.getReceiptStates().add(ReceiptState.map.get(jfxCheckBox.getId().toUpperCase()));
        }else{
            if(salesSearchVO.getReceiptStates().contains(ReceiptState.map.get(jfxCheckBox.getId().toUpperCase()))){
                salesSearchVO.getReceiptStates().remove(ReceiptState.map.get(jfxCheckBox.getId().toUpperCase()));
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
