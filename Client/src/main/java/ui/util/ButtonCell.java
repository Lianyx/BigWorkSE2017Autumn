package ui.util;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.cells.editors.base.JFXTreeTableCell;
import util.UserCategory;

public class ButtonCell<T> extends JFXTreeTableCell<T,String> {
    private JFXButton civ = new JFXButton("");

    @Override
    public void updateItem(String item,boolean empty){
        super.updateItem(item,empty);
        if(empty){
            setGraphic(null);
        }else{
            civ.setDisable(true);
            civ.setTranslateY(-4);
            civ.setText(item);
            setGraphic(civ);
        }
    }

    public void setButtonStyle(String value){
        ;civ.setStyle(value);
    }
}