package ui.accountantui;

import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import ui.util.PaneFactory;
import vo.billReceiptVO.CashItemVO;
import vo.billReceiptVO.TransferItemVO;


public class CashItemPane extends AnchorPane {

    CashItemVO cashItemVO;

    StackPane mainpane;

    ObservableList<CashItemVO> observableList;

    @FXML
    JFXTextField name;

    @FXML
    JFXTextField price;

    @FXML
    JFXTextField comment;

    JFXDialog jfxDialog;

    public CashItemPane(CashItemVO cashItemVO, ObservableList<CashItemVO> observableList) {
        super();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/accountantui/CashItemPane.fxml"));
            fxmlLoader.setRoot(this);
            fxmlLoader.setController(this);
            fxmlLoader.load();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.cashItemVO = cashItemVO;
        name.setText(cashItemVO.getName());
        price.setText(""+cashItemVO.getPrice());
        comment.setText(cashItemVO.getComment());


        name.setOnKeyPressed(t->{
            if(t.getCode()== KeyCode.ENTER){
                cashItemVO.setName(cashItemVO.getName());
                name.setText(""+cashItemVO.getName());
            }
        });

        price.setOnKeyPressed(t->{
            if(t.getCode()==KeyCode.ENTER){
                cashItemVO.setPrice(Double.parseDouble(price.getText()));
                price.setText(""+cashItemVO.getPrice());
            }
        });

        comment.setOnKeyPressed(t->{
            if(t.getCode()==KeyCode.ENTER){
                cashItemVO.setComment(comment.getText());
                comment.setText(cashItemVO.getComment());
            }
        });

        this.mainpane = PaneFactory.getMainPane();
        this.observableList=observableList;



    }

    public void setDialog(JFXDialog dialog) {
        this.jfxDialog = dialog;
    }




    @FXML
    public void save() {
        if(price.validate()){
            //listGoodsItemVO.setGoodsNum(Integer.parseInt(num.getText()));
            //sum.setText(""+listGoodsItemVO.getSum());
            //listGoodsItemVO.setComment(comment.getText());
            cashItemVO.setName(name.getText());
            cashItemVO.setPrice(Double.parseDouble(price.getText()));
            cashItemVO.setComment(comment.getText());
            jfxDialog.close();

        }
    }

    @FXML
    public void delete() {
        observableList.remove(this.cashItemVO);
        jfxDialog.close();
    }

}