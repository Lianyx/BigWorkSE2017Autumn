package ui.managerui.promotionui.promotionDetailPane;

import blService.promotionblService.PromotionblService;
import blService.promotionblService.TotalPromotionblService;
import businesslogic.promotionbl.PromotionFactory;
import com.jfoenix.controls.*;
import com.jfoenix.validation.NumberValidator;
import exceptions.ItemNotFoundException;
import javafx.beans.property.*;
import javafx.fxml.FXML;
import ui.managerui.common.MyBoardController;
import ui.managerui.common.MyTwoButtonDialog;
import ui.managerui.common.MyOneButtonDialog;
import ui.util.GetTask;
import util.PromotionState;
import vo.promotionVO.PromotionGoodsItemVO;
import vo.promotionVO.TotalPromotionVO;

import java.rmi.RemoteException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

public class TotalPromotionDetailPane extends PromotionDetailPane<TotalPromotionVO> {
    @FXML
    private JFXTextField totalField, tokenField;


    @Override
    protected String getURL() {
        return "/managerui/totalPromotionDetailPane.fxml";
    }

    @Override
    protected Class<? extends PromotionblService<TotalPromotionVO>> getServiceClass() {
        return TotalPromotionblService.class;
    }

    public TotalPromotionDetailPane(TotalPromotionVO promotionVO) {
        super(promotionVO);
    }

    public TotalPromotionDetailPane() {
    }

    @Override
    protected void initialize() {
        super.initialize();

        NumberValidator validator1 = new NumberValidator();
        validator1.setMessage("非数值"); // 这里还都要用两个？而且这个非数值怎么感觉只能是整数
        NumberValidator validator2 = new NumberValidator();
        validator2.setMessage("非数值");
        totalField.getValidators().add(validator1);
        totalField.focusedProperty().addListener(((observable, oldValue, newValue) -> {
            if (!newValue) {
                totalField.validate();
            }
        }));
        tokenField.getValidators().add(validator2);
        tokenField.focusedProperty().addListener(((observable, oldValue, newValue) -> {
            if (!newValue) {
                tokenField.validate();
            }
        }));
        totalField.disableProperty().bind(modifyState.not());
        tokenField.disableProperty().bind(modifyState.not());
    }

    @Override
    protected boolean validate() { // TODO 这里的validate还需要改。
        return totalField.validate() && tokenField.validate();
    }

    @Override
    protected void updatePromotionVO() {
        super.updatePromotionVO();
        promotionVO.setRequiredTotal(Double.parseDouble(totalField.getText()));
        promotionVO.setTokenAmount(Double.parseDouble(tokenField.getText()));
        promotionVO.setGifts(new ArrayList<>(goodsTreeTable.getGifts()));
    }


    @FXML
    @Override
    protected void reset() {
        super.reset();
        totalField.setText(String.valueOf(promotionVO.getRequiredTotal()));
        tokenField.setText(String.valueOf(promotionVO.getTokenAmount()));
        if (promotionVO.getGifts() != null) {
            goodsTreeTable.setAll(promotionVO.getGifts());
        }
    }



}
