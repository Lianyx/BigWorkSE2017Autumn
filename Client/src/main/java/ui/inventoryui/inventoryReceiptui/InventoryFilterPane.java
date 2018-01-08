package ui.inventoryui.inventoryReceiptui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import org.controlsfx.control.PopOver;
import ui.util.BoardController;
import util.ReceiptState;
import vo.inventoryVO.inventoryReceiptVO.InventorySearchVO;

import java.io.IOException;

public class InventoryFilterPane  extends AnchorPane {
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

    InventoryListPane inventoryListPane;

    InventorySearchVO inventorySearchVO;

    PopOver popOver;

    public InventoryFilterPane(InventorySearchVO inventorySearchVO, PopOver popOver) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/inventoryui/inventoryreceiptui/inventoryfilter.fxml"));
            fxmlLoader.setRoot(this);
            fxmlLoader.setController(this);
            fxmlLoader.load();
            this.popOver = popOver;
            this.inventorySearchVO = inventorySearchVO;

            draft.setSelected(true);
            rejected.setSelected(true);
            approved.setSelected(true);
            pending.setSelected(true);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public InventorySearchVO getInventorySearchVO() {
        return inventorySearchVO;
    }

    public void setInventorySearchVO(InventorySearchVO inventorySearchVO) {
        this.inventorySearchVO = inventorySearchVO;
    }

    public void setVO(JFXCheckBox jfxCheckBox){
        if(jfxCheckBox.isSelected()){
            inventorySearchVO.getReceiptStates().add(ReceiptState.map.get(jfxCheckBox.getId().toUpperCase()));
        }else{
            if(inventorySearchVO.getReceiptStates().contains(ReceiptState.map.get(jfxCheckBox.getId().toUpperCase()))){
                inventorySearchVO.getReceiptStates().remove(ReceiptState.map.get(jfxCheckBox.getId().toUpperCase()));
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
