package ui.memberui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import org.controlsfx.control.PopOver;
import ui.util.BoardController;
import util.MemberCategory;
import util.UserCategory;
import vo.MemberSearchVO;

public class MemberFilterPane extends AnchorPane{

    @FXML
    JFXCheckBox seller;
    @FXML
    JFXCheckBox supplier;
    @FXML
    JFXButton save;

    MemberSearchVO memberSearchVO;

    PopOver popOver;

    public MemberFilterPane(PopOver popOver, MemberSearchVO memberSearchVO) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/memberui/memberfilterpane.fxml"));
            fxmlLoader.setRoot(this);
            fxmlLoader.setController(this);
            fxmlLoader.load();
            this.popOver = popOver;
            this.memberSearchVO = memberSearchVO;
            seller.setSelected(true);
            supplier.setSelected(true);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setVO(JFXCheckBox jfxCheckBox){
        if(jfxCheckBox.isSelected()){
            memberSearchVO.getMemberCategories().add(MemberCategory.map.get(jfxCheckBox.getId().toUpperCase()));
        }else{
            if(memberSearchVO.getMemberCategories().contains(MemberCategory.map.get(jfxCheckBox.getId().toUpperCase()))){
                memberSearchVO.getMemberCategories().remove(MemberCategory.map.get(jfxCheckBox.getId().toUpperCase()));
            }
        }
    }


    @FXML
    public void save() {
        setVO(seller);
        setVO(supplier);
        popOver.hide();
        BoardController.getBoardController().refresh();

    }
}
