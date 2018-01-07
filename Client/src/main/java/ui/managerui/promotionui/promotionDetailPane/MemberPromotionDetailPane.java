package ui.managerui.promotionui.promotionDetailPane;

import blService.promotionblService.MemberPromotionblService;
import blService.promotionblService.PromotionblService;
import businesslogic.promotionbl.PromotionFactory;
import com.jfoenix.controls.*;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import javafx.beans.property.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import ui.managerui.common.MyBoardController;
import vo.promotionVO.MemberPromotionVO;
import vo.promotionVO.PromotionGoodsItemVO;
import vo.promotionVO.PromotionVO;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class MemberPromotionDetailPane extends PromotionDetailPane<MemberPromotionVO> {
//    @FXML
//    private JFXSlider requiredLevelSlider;
    @FXML
    private JFXTextField tokenField, requiredLevelField, discountField;

    @Override
    protected String getURL() {
        return "/managerui/memberPromotionDetailPane.fxml";
    }

    @Override
    protected Class<MemberPromotionblService> getServiceClass() {
        return MemberPromotionblService.class;
    }

    public MemberPromotionDetailPane() {
    }

    public MemberPromotionDetailPane(MemberPromotionVO promotionVO) {
        super(promotionVO);
    }

    @Override
    protected void initialize() {
        super.initialize();
        tokenField.disableProperty().bind(modifyState.not());
        discountField.disableProperty().bind(modifyState.not());
        requiredLevelField.disableProperty().bind(modifyState.not());
    }

    @Override
    protected boolean validate() {
        return super.validate();
    }

    @Override
    protected void updatePromotionVO() {
        super.updatePromotionVO();
        promotionVO.setRequiredLevel(Integer.parseInt(requiredLevelField.getText()));
        promotionVO.setTokenAmount(Double.parseDouble(tokenField.getText()));
        promotionVO.setDiscountFraction(Double.parseDouble(discountField.getText()));
        promotionVO.setGifts(new ArrayList<>(goodsTreeTable.getGifts()));
    }

    @FXML
    @Override
    protected void reset() {
        super.reset();
        requiredLevelField.setText(String.valueOf(promotionVO.getRequiredLevel()));
        tokenField.setText(String.valueOf(promotionVO.getTokenAmount()));
        discountField.setText(String.valueOf(promotionVO.getDiscountFraction()));
        if (promotionVO.getGifts() != null) {
            goodsTreeTable.setAll(promotionVO.getGifts());
        }
    }

}
