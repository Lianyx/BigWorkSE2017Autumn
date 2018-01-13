package ui.accountantui;

import blService.accountblService.AccountblService;
import businesslogic.accountbl.Accountbl;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.StackPane;

import ui.common.MyBoardController;
import ui.util.*;
import vo.AccountListVO;

import java.rmi.RemoteException;
import java.util.function.Predicate;

import static ui.util.ValidatorDecorator.DoubleValid;
import static ui.util.ValidatorDecorator.RequireValid;
import static ui.util.ValidatorDecorator.isDouble;

public class AccountDetailPane extends Refreshable{

    BoardController boardController;

    StackPane mainpane;

    AccountListVO vo;

    private AccountblService accountblService;

    private AccountListVO accountListVO;

    @FXML
    JFXTextField name;

    @FXML
    JFXTextField id;

    @FXML
    JFXTextField balance;

    @FXML
    protected ModifyButton modify;
    @FXML
    protected MaterialDesignIconView pen;
    @FXML
    protected JFXButton save;

    @FXML
    protected JFXButton reject;

    @FXML
    protected JFXButton delete;

    SimpleBooleanProperty modifyState = new SimpleBooleanProperty(false);
    SimpleBooleanProperty updateState = new SimpleBooleanProperty();

    boolean isAdd = false;

    public AccountDetailPane(AccountListVO accountListVO){
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


    public AccountDetailPane(boolean isAdd) {
        super();
        boardController = BoardController.getBoardController();
        mainpane = PaneFactory.getMainPane();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/accountantui/accountdetail.fxml"));
            fxmlLoader.setRoot(this);
            fxmlLoader.setController(this);
            fxmlLoader.load();
        } catch (Exception e) {
            e.printStackTrace();
        }
        save.setText("Add");
        updateState.set(true);
        modify.setVisible(false);
        delete.setVisible(false);

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


    public void delete() {
        DoubleButtonDialog doubleButtonDialog = new DoubleButtonDialog(mainpane, "", "请确定是否删除", "是", "否");
        doubleButtonDialog.setButtonOne(() -> {
            try{
                accountblService.delete(Integer.parseInt(id.getText()));
            }catch (RemoteException e){
                e.printStackTrace();
            }
            setBack();
        });
        doubleButtonDialog.setButtonTwo(() -> {});
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
                            MyBoardController.getMyBoardController().switchTo(this);
                        }, buttonDialog, p);

                new Thread(task).start();
            } else {
                switchPane(toSwitch);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void switchPane(boolean toSwtich) {
        if (toSwtich == true) {
            boardController.switchTo(this);
        } else {
            boardController.setAll(this);
        }
    }

    @FXML
    public void save() {
        if (valid()) {
            modify.modifyProperty().set(false);
            if(!isAdd&&name.getText().equals(accountListVO.getName())&&balance.getText().equals(String.valueOf(accountListVO.getBalance()))){
                OneButtonDialog oneButtonDialog = new OneButtonDialog(mainpane,"","您未做修改","继续");
                oneButtonDialog.setButtonOne(()->{});
                oneButtonDialog.show();
            }else {

                DoubleButtonDialog doubleButtonDialog = new DoubleButtonDialog(mainpane, "", "请确定是否保存", "是", "否");
                doubleButtonDialog.setButtonTwo(() -> {
                });
                doubleButtonDialog.setButtonOne(() -> {
                    try {
                        if (isAdd) {
                            accountblService.add(new AccountListVO(
                                    0,
                                    name.getText(),
                                    Double.parseDouble(balance.getText())
                            ));
                        } else {
                            accountblService.update(new AccountListVO(
                                    Integer.parseInt(id.getText()),
                                    name.getText(),
                                    Double.parseDouble(balance.getText())
                            ));
                        }
                        boardController.switchTo(this);
                        OneButtonDialog oneButtonDialog = new OneButtonDialog(mainpane, "提示", "操作成功", "继续");
                        oneButtonDialog.setButtonOne(() -> {
                        });
                        oneButtonDialog.show();
                        setBack();
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                });
                doubleButtonDialog.show();
            }

        } else {
            OneButtonDialog oneButtonDialog = new OneButtonDialog(mainpane, "Error！", "数据错误！", "继续");
            oneButtonDialog.setButtonOne(() -> {
            });
            oneButtonDialog.show();
        }
    }


    public boolean valid() {
        if (!name.getText().equals("") && !id.getText().equals("")&&!balance.getText().equals("")&&isDouble(balance.getText()))
            return true;
        return false;
    }

    public void setBack(){

        MyBoardController.getMyBoardController().goBack();
    }

}

