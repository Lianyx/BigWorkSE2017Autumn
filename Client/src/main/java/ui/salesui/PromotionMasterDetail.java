package ui.salesui;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import org.controlsfx.control.MasterDetailPane;
import ui.common.bigPane.FXMLAnchorPane;
import vo.promotionVO.MemberPromotionVO;
import vo.promotionVO.PromotionVO;

import java.util.ArrayList;

public class PromotionMasterDetail extends FXMLAnchorPane {


    @FXML
    MasterDetailPane pane;

    PromotionList promotionList = new PromotionList();

    PromotionDetail promotionDetail = new PromotionDetail();

    @Override
    protected String getURL() {
        return "/salesui/choosePromotion.fxml";
    }

    public PromotionMasterDetail(){
        super();
        pane.setMasterNode(promotionList);
        pane.setDetailNode(promotionDetail);

        MemberPromotionVO promotionVO = new MemberPromotionVO();




        promotionList.typeProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                promotionDetail.setPromotion(promotionList.getPromotionVO());
            }
        });


    }


}
