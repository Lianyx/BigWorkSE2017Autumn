package ui.accountantui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import org.controlsfx.control.PopOver;
import ui.stockui.StockListPane;
import ui.util.BoardController;
import util.ReceiptState;
import vo.StockSearchVO;
import vo.billReceiptVO.BillReceiptSearchVO;


public class BillReceiptFilterPane extends AnchorPane {

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

    BillReceiptSearchVO billReceiptSearchVO;

    public BillReceiptSearchVO getBillReceiptSearchVO() {
        return billReceiptSearchVO;
    }

    public void setBillReceiptSearchVO(BillReceiptSearchVO billReceiptSearchVO) {
        this.billReceiptSearchVO = billReceiptSearchVO;
    }

    PopOver popOver;

    public BillReceiptFilterPane(PopOver popOver, BillReceiptSearchVO billReceiptSearchVO) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/accountantui/billReceiptFilterPane.fxml"));
            fxmlLoader.setRoot(this);
            fxmlLoader.setController(this);
            fxmlLoader.load();
            this.popOver = popOver;
            this.billReceiptSearchVO = billReceiptSearchVO;
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
            billReceiptSearchVO.getReceiptStates().add(ReceiptState.map.get(jfxCheckBox.getId().toUpperCase()));
        }else{
            if(billReceiptSearchVO.getReceiptStates().contains(ReceiptState.map.get(jfxCheckBox.getId().toUpperCase()))){
                billReceiptSearchVO.getReceiptStates().remove(ReceiptState.map.get(jfxCheckBox.getId().toUpperCase()));
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
