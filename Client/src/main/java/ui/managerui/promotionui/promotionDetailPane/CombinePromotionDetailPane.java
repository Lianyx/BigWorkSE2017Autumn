package ui.managerui.promotionui.promotionDetailPane;

import blService.promotionblService.CombinePromotionblService;
import com.jfoenix.controls.*;
import javafx.fxml.FXML;
import vo.promotionVO.CombinePromotionVO;

import java.util.ArrayList;

public class CombinePromotionDetailPane extends PromotionDetailPane<CombinePromotionVO> {
    @FXML
    private JFXTextField discountField;

    @Override
    protected String getURL() {
        return "/managerui/combinePromotionDetailPane.fxml";
    }

    @Override
    protected Class<CombinePromotionblService> getServiceClass() {
        return CombinePromotionblService.class;
    }

    public CombinePromotionDetailPane(CombinePromotionVO promotionVO) {
        super(promotionVO);
    }

    public CombinePromotionDetailPane() {
    }

    protected void initialize() {
        super.initialize();
        discountField.disableProperty().bind(modifyState.not());
    }

    @Override
    protected boolean validate() {
        return super.validate();
    }

    @Override
    protected void updatePromotionVO() {
        super.updatePromotionVO();
        promotionVO.setDiscountAmount(Double.parseDouble(discountField.getText()));
        promotionVO.setGoodsCombination(new ArrayList<>(goodsTreeTable.getGifts()));
    }

    @FXML
    @Override
    protected void reset() {
        super.reset();
        discountField.setText(String.valueOf(promotionVO.getDiscountAmount()));
        if (promotionVO.getGoodsCombination() != null) {
            goodsTreeTable.setAll(promotionVO.getGoodsCombination());
        }
    }
}
