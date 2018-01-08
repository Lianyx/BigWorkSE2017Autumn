package ui.inventoryui.goodsui;

import blService.blServiceFactory.ServiceFactory_Stub;
import blService.goodsblService.GoodsblService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import ui.util.*;
import vo.inventoryVO.GoodsVO;


import java.io.File;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.function.Predicate;

import static ui.util.ValidatorDecorator.RequireValid;

public class GoodDetailPane extends ReceiptDetailPane<GoodsVO> {

    String goodId = "-1";

    GoodsblService goodsblService;

    @FXML
    JFXTextField goodName;
    @FXML
    TextField goodTextId;
    @FXML
    TextField goodType;
    @FXML
    TextField inventoryNum;
    @FXML
    TextField classifyId;
    @FXML
    TextField alarmNum;
    @FXML
    TextField purPrice;
    @FXML
    TextField salePrice;
    @FXML
    TextField recentPurPrice;
    @FXML
    TextField recentSalePrice;

    @FXML
    JFXButton reset;

    @FXML
    Label date;

    public GoodDetailPane(String id) {
        this(false);
        this.goodId = id;

        delete.setVisible(true);
        modify.setVisible(true);
        save.setText("Save");
        this.modifyState.bind(modify.modifyProperty());
        this.modifyState.addListener((b, o, n) -> {
            if (!n) {
                if (valid()) {
                    modify.modifyProperty().set(false);
                } else {
                    modify.modifyProperty().set(true);
                }
            }
        });

        goodName.disableProperty().bind(modifyState.not());



       reset.visibleProperty().bind(modifyState);
       save.visibleProperty().bind(modifyState);
    }

    public GoodDetailPane(boolean isAdd) {
        super("/inventoryui/goodui/goodsdetail.fxml");
        //goodsblService =  GoodsListPane.goodsblService;//ServiceFactory_Stub.getService(GoodsblService.class.getName());
        this.goodsblService = ServiceFactory_Stub.getService(GoodsblService.class.getName());

        delete.setVisible(false);
        date.setText(LocalDate.now().toString());

        RequireValid(goodName);

        updateState.set(false);
        if (isAdd) {
            updateState.set(true);
            switchPane(true);
        }

        reset.setOnMouseClicked(t -> {
            TextFieldPane textFieldPane = new TextFieldPane();
            JFXDialog dialog = new JFXDialog(mainpane, textFieldPane, JFXDialog.DialogTransition.CENTER);
            textFieldPane.cencel(() -> {
                dialog.close();
            });
            textFieldPane.save(() -> {
                dialog.close();
                //password.setText(textFieldPane.getText());
            });
          //  textFieldPane.setText(password.getText());
            textFieldPane.setPrompt("Password");
            dialog.show();
        });

    }

    @Override
    public void delete() {
        DoubleButtonDialog doubleButtonDialog = new DoubleButtonDialog(mainpane, "Delete", "sabi", "Yes", "No");
        doubleButtonDialog.setButtonOne(() -> {
        });
        doubleButtonDialog.setButtonTwo(() -> {
            setBack();
        });
        doubleButtonDialog.show();

    }

    @Override
    public void savePendingReceipt() {

    }

    @Override
    public void saveDraftReceipt() {

    }

    @Override
    public boolean valid() {
        return true;
    }

    @Override
    public void save() {
        if (valid()) {
            modify.modifyProperty().set(false);
            DoubleButtonDialog doubleButtonDialog = new DoubleButtonDialog(mainpane, "Pending?", "sabi", "Yes", "No");
            doubleButtonDialog.setButtonTwo(() -> {
            });
            doubleButtonDialog.setButtonOne(() -> {
                if (goodId.equals("-1")) {
                    goodId = goodsblService.getID("123",4);
                    try {
                        goodsblService.addGoods(new GoodsVO(goodId,goodName.getText(),goodType.getText(),classifyId.getText(),
                        Integer.parseInt(inventoryNum.getText()),
                                Double.parseDouble(purPrice.getText()),
                                Double.parseDouble(salePrice.getText()),
                                Double.parseDouble(recentPurPrice.getText()),
                                Double.parseDouble(recentSalePrice.getText()),Integer.parseInt(alarmNum.getText())));
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        goodsblService.updateGoods(new GoodsVO(goodId,goodName.getText(),goodType.getText(),classifyId.getText(),
                                Integer.parseInt(inventoryNum.getText()),
                                Double.parseDouble(purPrice.getText()),
                                Double.parseDouble(salePrice.getText()),
                                Double.parseDouble(recentPurPrice.getText()),
                                Double.parseDouble(recentSalePrice.getText()),
                        Integer.parseInt(alarmNum.getText())));
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
                setBack();
            });
            doubleButtonDialog.show();

        } else {
            OneButtonDialog oneButtonDialog = new OneButtonDialog(mainpane, "???", "Stupid!", "Accept");
            oneButtonDialog.setButtonOne(() -> {
            });
            oneButtonDialog.show();
        }
    }

    @Override
    public void refresh(boolean toSwitch) {
        boardController.Loading();
        try {
            if (!updateState.get()) {
                DoubleButtonDialog buttonDialog =
                        new DoubleButtonDialog(mainpane, "Wrong", "sabi", "Last", "Ret");
                buttonDialog.setButtonTwo(() -> boardController.Ret());
                buttonDialog.setButtonTwo(() -> refresh(false));
                Predicate<Integer> p = (i) -> {
                    try {
                        if ((vo = goodsblService.showDetail(goodId)) != null) return true;
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    return false;
                };
                GetTask task =
                        new GetTask(() -> {
                            goodName.setText(vo.getGoodName());

                            //date.setText(vo.getDate());

                            switchPane(toSwitch);
                        }, buttonDialog, p);

                new Thread(task).start();
            } else {
                switchPane(toSwitch);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
