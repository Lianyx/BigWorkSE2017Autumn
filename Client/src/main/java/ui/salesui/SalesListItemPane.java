package ui.salesui;

import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import vo.ListGoodsItemVO;

import static ui.util.ValidatorDecorator.NumberValid;

public class SalesListItemPane extends AnchorPane {


    ListGoodsItemVO listGoodsItemVO;

    StackPane mainpane;

    ObservableList<ListGoodsItemVO> observableList;
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

    JFXDialog dialog;


    public SalesListItemPane(ListGoodsItemVO listGoodsItemVO, StackPane mainpane, ObservableList<ListGoodsItemVO> observableList) {
        super();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/stockui/listitempane.fxml"));
            fxmlLoader.setRoot(this);
            fxmlLoader.setController(this);
            fxmlLoader.load();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.listGoodsItemVO = listGoodsItemVO;

        goodId.setText(""+listGoodsItemVO.getGoodsId());
        goodName.setText(listGoodsItemVO.getGoodsName());
        goodType.setText(listGoodsItemVO.getType());
        NumberValid(num);
        num.setOnKeyPressed(t->{
            if(t.getCode()== KeyCode.ENTER&&num.validate()){
                listGoodsItemVO.setGoodsNum(Integer.parseInt(num.getText()));
                sum.setText(""+listGoodsItemVO.getSum());
            }
        });
        num.setText(""+listGoodsItemVO.getGoodsNum());
        sum.setText(""+listGoodsItemVO.getSum());
        comment.setText(listGoodsItemVO.getComment());
        comment.setOnKeyPressed(t->{
            if(t.getCode()== KeyCode.ENTER){
                listGoodsItemVO.setComment(comment.getText());
            }
        });

        this.mainpane = mainpane;
        this.observableList=observableList;



    }

    public void setDialog(JFXDialog dialog) {
        this.dialog = dialog;
    }



    @FXML
    public void save() {
        if(num.validate()){
            listGoodsItemVO.setGoodsNum(Integer.parseInt(num.getText()));
            sum.setText(""+listGoodsItemVO.getSum());
            listGoodsItemVO.setComment(comment.getText());
            dialog.close();

        }
    }

    @FXML
    public void delete() {
        observableList.remove(this.listGoodsItemVO);
        dialog.close();
    }

}