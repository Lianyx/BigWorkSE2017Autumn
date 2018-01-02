package ui.managerui.promotionui;

import blService.promotionblService.PromotionblService;
import businesslogic.promotionbl.PromotionFactory;
import com.jfoenix.controls.*;
import com.jfoenix.validation.NumberValidator;
import com.jfoenix.validation.base.ValidatorBase;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import exceptions.ItemNotFoundException;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import ui.managerui.common.MyConfirmDialog;
import ui.managerui.common.MyInformationDialog;
import ui.util.HistoricalRecord;
import ui.util.Refreshable;
import vo.promotionVO.PromotionGoodsItemVO;
import vo.promotionVO.TotalPromotionVO;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

public class TotalPromotionDetailPane extends PromotionDetailPane {
    @FXML
    private GiftsTreeTable giftsTreeTable;
    @FXML
    private JFXButton modify, reset, save, saveAsDraft;
    @FXML
    private MaterialDesignIconView pen;
    @FXML
    private JFXTextField totalField, tokenField;
    @FXML
    private JFXDatePicker beginTimePicker, endTimePicker;
    @FXML
    private TextArea commentArea; // TODO 设字数限制
    @FXML
    private JFXRippler add;

    private TotalPromotionVO totalPromotionVO;
    private PromotionblService<TotalPromotionVO> totalPromotionblService;

    private BooleanProperty modifyState = new SimpleBooleanProperty(false);

    public TotalPromotionDetailPane(TotalPromotionVO totalPromotionVO) {
        this.totalPromotionVO = totalPromotionVO;
        initialize();
        reset();

        saveAsDraft.setVisible(false);
        reset.setLayoutX(saveAsDraft.getLayoutX());
    }

    public TotalPromotionDetailPane() {
        initialize();
        modifyState.set(true);
    }

    private void initialize() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/managerui/totalPromotionDetailPane.fxml"));
            fxmlLoader.setRoot(this);
            fxmlLoader.setController(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try { // TODO
            totalPromotionblService = PromotionFactory.getTotalPromotionblService();
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        NumberValidator validator1 = new NumberValidator();
        validator1.setMessage("非数值"); // 这里还都要用两个？
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


        modifyState.addListener(((observable, oldValue, newValue) -> {
            if (newValue) {
                modify.setBackground(new Background(new BackgroundFill(Color.valueOf("#03A9F4"), modify.getBackground().getFills().get(0).getRadii(), null)));
                pen.setFill(Paint.valueOf("#FFFFFF"));
            } else {
                modify.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, modify.getBackground().getFills().get(0).getRadii(), null)));
                pen.setFill(Paint.valueOf("#000000"));
            }
        }));


        totalField.disableProperty().bind(modifyState.not());
        tokenField.disableProperty().bind(modifyState.not());
        beginTimePicker.disableProperty().bind(modifyState.not());
        endTimePicker.disableProperty().bind(modifyState.not());
        commentArea.disableProperty().bind(modifyState.not());
        add.visibleProperty().bind(modifyState);
        save.visibleProperty().bind(modifyState);
        reset.visibleProperty().bind(modifyState);
    }

    private boolean validate() {
        return totalField.validate() && tokenField.validate();
    }

    private void updateTotalPromotionVO() {
        totalPromotionVO.setRequiredTotal(Double.parseDouble(totalField.getText()));
        totalPromotionVO.setTokenAmount(Double.parseDouble(tokenField.getText()));
        totalPromotionVO.setBeginTime(LocalDateTime.of(beginTimePicker.getValue(), LocalTime.MIN));
        totalPromotionVO.setEndTime(LocalDateTime.of(endTimePicker.getValue(), LocalTime.MIN));
        totalPromotionVO.setComment(commentArea.getText());
        totalPromotionVO.setGifts(new ArrayList<>(giftsTreeTable.getGifts()));
    }

    @FXML
    private void save() {
        if (validate()) {
            updateTotalPromotionVO();
            try {
                totalPromotionblService.update(totalPromotionVO);
                JFXDialog infomationDialog = new MyInformationDialog("保存成功", () -> {
                    boardController.historicalSwitchTo(HistoricalRecord.pop());
                    boardController.refresh();
                });
                infomationDialog.show();
            } catch(ItemNotFoundException e) {
                JFXDialog warnDialog = new MyConfirmDialog("策略不存在，是否返回单据列表", () -> {
                    boardController.historicalSwitchTo(HistoricalRecord.pop());
                    boardController.refresh();
                });
                warnDialog.show();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void saveAsDraft() {
        System.out.println("save as draft");
    }

    @FXML
    private void addGift() { // TODO
        giftsTreeTable.add(new PromotionGoodsItemVO("id5113", "商品A", 23, new SimpleIntegerProperty(3)));
    }

    @FXML
    private void modify() {
        modifyState.set(!modifyState.get());
    }

    @FXML
    private void delete() {
        Runnable confirmAction = () -> {
            try {
                totalPromotionblService.delete(totalPromotionVO);
                JFXDialog informationDialog = new MyInformationDialog("删除成功", () -> {
                    boardController.historicalSwitchTo(HistoricalRecord.pop());
                    boardController.refresh();
                });
                informationDialog.show();
            } catch(ItemNotFoundException e) {
                JFXDialog warnDialog = new MyConfirmDialog("策略不存在，是否返回单据列表", () -> {
                    boardController.historicalSwitchTo(HistoricalRecord.pop());
                    boardController.refresh();
                });
                warnDialog.show();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        };
        JFXDialog confirmDialog = new MyConfirmDialog("请确认删除", confirmAction);
        confirmDialog.show();
    }

    @FXML
    private void reset() {
        totalField.setText(String.valueOf(totalPromotionVO.getRequiredTotal()));
        tokenField.setText(String.valueOf(totalPromotionVO.getTokenAmount()));
        beginTimePicker.setValue(totalPromotionVO.getBeginTime().toLocalDate());
        endTimePicker.setValue(totalPromotionVO.getEndTime().toLocalDate());
        commentArea.setText(totalPromotionVO.getComment());
        totalPromotionVO.getGifts().forEach(giftsTreeTable::add);
    }


    @Override
    public void refresh(boolean toSwitch) {

    }
}
