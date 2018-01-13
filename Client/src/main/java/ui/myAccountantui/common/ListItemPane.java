package ui.myAccountantui.common;

import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import ui.myAccountantui.common.MyListItemPane;
import ui.myAccountantui.common.MyListItemTablePane;
import vo.ListGoodsItemVO;

import static ui.util.ValidatorDecorator.NumberValid;
import static ui.util.ValidatorDecorator.RequireValid;

public class ListItemPane extends MyListItemPane<ListGoodsItemVO>{
    @FXML
    Label goodId;
    @FXML
    Label goodType;
    @FXML
    Label goodName;
    @FXML
    Label sum;
    @FXML
    JFXTextField num;
    @FXML
    JFXTextArea comment;



    public ListItemPane(ListGoodsItemVO listGoodsItemVO, MyListItemTablePane myListItmeTablePane) {
        super(getURL(), listGoodsItemVO, myListItmeTablePane);
        RequireValid(num);
        goodId.setText(""+listGoodsItemVO.getGoodsId());
        goodName.setText(listGoodsItemVO.getGoodsName());
        goodType.setText(listGoodsItemVO.getType());
        NumberValid(num);
        num.setOnKeyPressed(t->{
            if(t.getCode()== KeyCode.ENTER&&num.validate()){
                vo.setGoodsNum(Integer.parseInt(num.getText()));
                sum.setText(""+vo.getSum());
            }
        });
        num.setText(""+listGoodsItemVO.getGoodsNum());
        sum.setText(""+listGoodsItemVO.getSum());
        comment.setText(listGoodsItemVO.getComment());
        comment.setOnKeyPressed(t->{
            if(t.getCode()== KeyCode.ENTER){
                vo.setComment(comment.getText());
            }
        });

    }

    public static String getURL(){
        return "/stockui/listitempane.fxml";
    }


    @Override
    public void saveItem() {
        vo.setGoodsNum(Integer.parseInt(num.getText()));
        System.out.println(vo.getGoodsNum()+"   /.."+vo.getPrice());
        sum.setText(""+vo.getSum());
        vo.setComment(comment.getText());
        myListItmeTablePane.refresh();
    }

    @Override
    public void deleteItem() {
        myListItmeTablePane.remove(vo);
    }

    @Override
    public boolean validate() {
        return num.validate();
    }
}
