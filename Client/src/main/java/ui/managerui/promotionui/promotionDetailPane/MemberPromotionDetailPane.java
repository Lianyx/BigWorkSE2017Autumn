package ui.managerui.promotionui.promotionDetailPane;

import blService.promotionblService.MemberPromotionblService;
import com.jfoenix.controls.*;
import javafx.fxml.FXML;
import vo.promotionVO.MemberPromotionVO;

import java.util.ArrayList;

public class MemberPromotionDetailPane extends PromotionDetailPane<MemberPromotionVO> {
//    @FXML
//    private JFXSlider requiredLevelSlider;
    @FXML
    private JFXTextField tokenField, requiredLevelField, discountField;


    /**
     * Constructor related
     * */
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

    /**
     * implement
     * */
    @Override
    protected String getURL() {
        return "/managerui/memberPromotionDetailPane.fxml";
    }

    @Override
    protected Class<MemberPromotionblService> getServiceClass() {
        return MemberPromotionblService.class;
    }


    /**
     * Override
     * */
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
