package ui.accountantui;

import blService.accountblService.AccountblService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import ui.userui.usermanagerui.UserDetailPane;
import ui.util.BoardController;
import ui.util.Loading;
import ui.util.Refreshable;
import vo.AccountListVO;
import vo.UserVO;

public class AccountDetailPane extends Refreshable{


    AccountListVO accountListVO;

    private AccountblService accountblService;

    BoardController boardController;

    StackPane mainpane;

    @FXML
    JFXTextField name;

    @FXML
    JFXTextField id;

    @FXML
    JFXTextField balance;

    @FXML
    JFXButton modify;

    @FXML
    MaterialDesignIconView pen;

    SimpleBooleanProperty modifyState = new SimpleBooleanProperty(false);


    public AccountDetailPane(AccountListVO accountListVO,AccountblService accountblService,BoardController boardController,StackPane mainpane){

        super();
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/accountantui/accountDetail.fxml"));
            fxmlLoader.setRoot(this);
            fxmlLoader.setController(this);
            fxmlLoader.load();
        }catch (Exception e){
            e.printStackTrace();
        }

        this.accountListVO = accountListVO;
        this.accountblService  = accountblService;
        this.boardController = boardController;
        this.mainpane = mainpane;

        name.disableProperty().bind(modifyState.not());
        id.disableProperty().bind(modifyState.not());
        balance.disableProperty().bind(modifyState.not());

    }


    @FXML
    public void modify(){
        modifyState.setValue(!modifyState.getValue());
        if(modifyState.getValue()==true){
            modify.setBackground(new Background(new BackgroundFill(Color.valueOf("#03A9F4"), modify.getBackground().getFills().get(0).getRadii(), null)));
            pen.setFill(Paint.valueOf("#FFFFFF"));
        }else{
            modify.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, modify.getBackground().getFills().get(0).getRadii(), null)));
            pen.setFill(Paint.valueOf("#000000"));
        }
    }

    public void switchPane(boolean toSwtich){
        if(toSwtich==true){
            System.out.println("??/**/");
            boardController.switchTo(this);
        }else{
            boardController.setAll(this);
        }
    }

    @Override
    public void refresh(boolean toSwitch){
        boardController.Loading();
        try{
            AccountDetailPane.SwitchTask task = new AccountDetailPane.SwitchTask(accountListVO);
            task.valueProperty().addListener(new ChangeListener<Boolean>() {
                @Override
                public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                    if(task.getIntegerProperty()==1){
                        try{
                            //userVO=task.getUserVO();
                            //username.setText(userVO.getUsername());
                            //usertype.getSelectionModel().select(userVO.getUsertype().ordinal());
                            id.setText(String.valueOf(accountListVO.getID()));
                            name.setText(accountListVO.getName());
                            balance.setText(String.valueOf(accountListVO.getBalance()));
                            switchPane(toSwitch);
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }else if(task.getIntegerProperty()==0) {
                        try {

                            JFXDialogLayout jfxDialogLayout = new JFXDialogLayout();
                            jfxDialogLayout.setHeading(new Label("Wrong"));
                            jfxDialogLayout.setBody(new Label("sabi"));
                            JFXButton button = new JFXButton("Accept");
                            JFXButton re = new JFXButton("Re");
                            JFXDialog dialog = new JFXDialog(mainpane,jfxDialogLayout,JFXDialog.DialogTransition.CENTER);
                            button.setOnAction(e->{
                                dialog.close();
                                boardController.Ret();
                            });
                            re.setOnAction(e->{
                                dialog.close();
                                refresh(false);
                            });
                            jfxDialogLayout.setActions(button,re);
                            dialog.show();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
            new Thread(task).start();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    public void save(){
        JFXDialog dialog = new JFXDialog(mainpane, new BorderPane(new Loading()), JFXDialog.DialogTransition.CENTER);
        dialog.show();
    }


    private class SwitchTask extends Task<Boolean> {

        private SimpleIntegerProperty integerProperty = new SimpleIntegerProperty(-1);
        private AccountListVO accountListVO;

        public SwitchTask(AccountListVO accountListVO){
            this.accountListVO = accountListVO;
        }



        public int getIntegerProperty() {
            return integerProperty.get();
        }

        public SimpleIntegerProperty integerPropertyProperty() {
            return integerProperty;
        }

        public AccountListVO getAccountListVO() {
            return accountListVO;
        }

        public void setAccountListVO(AccountListVO accountListVO) {
            this.accountListVO = accountListVO;
        }

        @Override
        protected Boolean call() throws Exception{

            if(accountListVO!=null){
                Thread.sleep(2000);
                integerProperty.setValue(1);
                return true;
            }else {
                Thread.sleep(2000);
                integerProperty.set(0);
                return false;
            }
        }
    }

}
