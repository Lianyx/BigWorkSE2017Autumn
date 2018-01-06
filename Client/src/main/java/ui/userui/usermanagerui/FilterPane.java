package ui.userui.usermanagerui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import org.controlsfx.control.PopOver;
import ui.salesui.SalesListPane;
import ui.util.BoardController;
import util.ReceiptState;
import util.UserCategory;
import vo.UserSearchVO;

public class FilterPane extends AnchorPane{


    @FXML
    JFXCheckBox UserManager;
    @FXML
    JFXCheckBox InventoryManager;
    @FXML
    JFXCheckBox GeneralManager;
    @FXML
    JFXCheckBox Salesman;
    @FXML
    JFXCheckBox SalesManager;
    @FXML
    JFXCheckBox Accountant;

    @FXML
    JFXButton save;

    UserSearchVO userSearchVO;

    PopOver popOver;

    public FilterPane(PopOver popOver, UserSearchVO userSearchVO) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/userui/filter.fxml"));
            fxmlLoader.setRoot(this);
            fxmlLoader.setController(this);
            fxmlLoader.load();
            this.popOver = popOver;
            this.userSearchVO = userSearchVO;
            UserManager.setSelected(true);
            InventoryManager.setSelected(true);
            Accountant.setSelected(true);
            Salesman.setSelected(true);
            SalesManager.setSelected(true);
            GeneralManager.setSelected(true);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setVO(JFXCheckBox jfxCheckBox){
        if(jfxCheckBox.isSelected()){
            userSearchVO.getUserCategories().add(UserCategory.map.get(jfxCheckBox.getId()));
        }else{
            if(userSearchVO.getUserCategories().contains(UserCategory.map.get(jfxCheckBox.getId()))){
                userSearchVO.getUserCategories().remove(UserCategory.map.get(jfxCheckBox.getId()));
            }
        }
    }


    @FXML
    public void save() {
        setVO(UserManager);
        setVO(Salesman);
        setVO(SalesManager);
        setVO(InventoryManager);
        setVO(GeneralManager);
        setVO(Accountant);
        popOver.hide();
        BoardController.getBoardController().refresh();

    }
}
