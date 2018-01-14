package ui.accountantui;

import blService.accountblService.AccountblService;
import businesslogic.accountbl.Accountbl;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;

import ui.common.BoardController;
import ui.util.*;
import vo.AccountListVO;

import java.rmi.RemoteException;
import java.util.function.Predicate;

import static ui.util.ValidatorDecorator.DoubleValid;
import static ui.util.ValidatorDecorator.RequireValid;

public class AccountDetailPane1 extends ReceiptDetailPane<AccountListVO>{



    private AccountblService accountblService;

    private AccountListVO accountListVO;

    @FXML
    JFXTextField name;

    @FXML
    JFXTextField id;

    @FXML
    JFXTextField balance;


    @FXML
    MaterialDesignIconView pen;

    SimpleBooleanProperty modifyState = new SimpleBooleanProperty(false);

    boolean isAdd = false;

    public AccountDetailPane1(AccountListVO accountListVO){
        this(false);
        this.accountListVO = accountListVO;
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

        name.disableProperty().bind(modifyState.not());
        balance.disableProperty().bind(modifyState.not());


    }


    public AccountDetailPane1(boolean isAdd) {
        super("/accountantui/accountdetail.fxml");
        try{
            this.accountblService = new Accountbl();

        }catch (Exception e){
            e.printStackTrace();
        }
        this.isAdd = isAdd;

        id.setText("auto");
        id.setDisable(true);
        delete.setVisible(false);
        DoubleValid(balance);

        updateState.set(false);
        if (isAdd) {
            updateState.set(true);
            switchPane(true);
        }


    }

    @Override
    public void delete() {
        DoubleButtonDialog doubleButtonDialog = new DoubleButtonDialog(mainpane, "Delete", "sabi", "Yes", "No");
        doubleButtonDialog.setButtonOne(() -> {
        });
        doubleButtonDialog.setButtonTwo(() -> {
//            setBack();
            // TODO by 连。
            BoardController.getBoardController().goBack();
        });
        doubleButtonDialog.show();
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
                    if((vo=accountListVO)!=null) {
                        System.out.println("11111");
                        return true;
                    }
                    else
                        return false;
                };
                GetTask task =
                        new GetTask(() -> {
                            id.setText(String.valueOf(vo.getID()));
                            name.setText(vo.getName());
                            balance.setText(String.valueOf(vo.getBalance()));
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

    @Override
    public void save() {
        if (valid()) {
            modify.modifyProperty().set(false);
            DoubleButtonDialog doubleButtonDialog = new DoubleButtonDialog(mainpane, "Pending?", "sabi", "Yes", "No");
            doubleButtonDialog.setButtonTwo(() -> {
            });
            doubleButtonDialog.setButtonOne(() -> {

                try {
                    if(isAdd){
                        accountblService.add(new AccountListVO(
                                0,
                                name.getText(),
                                Double.parseDouble(balance.getText())
                        ));
                    }else{
                        accountblService.update(new AccountListVO(
                                Integer.parseInt(id.getText()),
                                name.getText(),
                                Double.parseDouble(balance.getText())
                        ));
                    }

                    boardController.switchTo(this);
                    OneButtonDialog oneButtonDialog = new OneButtonDialog(mainpane, "???", "Success", "Accept");
                    oneButtonDialog.setButtonOne(() -> {
                    });
                    oneButtonDialog.show();
                    //setBack();
                }catch (RemoteException e){
                    e.printStackTrace();
                }
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
    public void savePendingReceipt() {
    }

    @Override
    public void saveDraftReceipt() {
    }


    @Override
    public boolean valid() {
        if (!name.getText().equals("") && !id.getText().equals("")&&!balance.getText().equals(""))
            return true;
        return false;
    }

}

