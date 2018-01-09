package ui.util;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;


import java.util.function.Predicate;

import static ui.util.ValidatorDecorator.NumberValid;
import static ui.util.ValidatorDecorator.RequireValid;

public abstract class ReceiptDetailPane<T> extends Refreshable {


    protected T vo;

    protected BoardController boardController;

    protected SimpleBooleanProperty modifyState = new SimpleBooleanProperty(false);

    protected StackPane mainpane;

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

    private boolean check;
    protected SimpleBooleanProperty updateState = new SimpleBooleanProperty();

    public ReceiptDetailPane(String url,boolean check) {
        super();
        this.check = check;
        boardController = BoardController.getBoardController();
        mainpane = PaneFactory.getMainPane();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(url));
            fxmlLoader.setRoot(this);
            fxmlLoader.setController(this);
            fxmlLoader.load();
        } catch (Exception e) {
            e.printStackTrace();
        }
        updateState.set(false);
        this.modifyState.bind(modify.modifyProperty());
        this.modifyState.addListener((b,o,n)->{if(!n){
                    if(valid()) {
                        modify.modifyProperty().set(false);
                    }else{
                        modify.modifyProperty().set(true);
                    }
                }
            });
        if(!check) {
            save.visibleProperty().bind(modifyState);
        }else{
            save.setText("Accept");
            save.setStyle("-fx-background-color: rgb(56,174,103)");
            reject.setVisible(true);
            delete.setVisible(false);
        }
    }

    public ReceiptDetailPane(String url){
        super();
        boardController = BoardController.getBoardController();
        mainpane = PaneFactory.getMainPane();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(url));
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
    }






    @FXML
    public abstract void delete();


    public void switchPane(boolean toSwtich) {
        if (toSwtich == true) {
            boardController.switchTo(this);
        } else {
            boardController.setAll(this);
        }
    }

    @FXML
    public void save(){
        if(!check){
            saveReceipt();
        }else{
            accept();
        }
    }
    public void accept(){

    }

    public void saveReceipt(){
        modify.modifyProperty().set(false);
        if(valid()){
            savePendingReceipt();
        }else{
            saveDraftReceipt();
        }
    }

    public abstract void savePendingReceipt();
    public abstract void saveDraftReceipt();

    public abstract boolean valid();

    public void setBack(){
        boardController.setRightAnimation();
        boardController.historicalSwitchTo((Refreshable) HistoricalRecord.pop());
        boardController.refresh();
        HistoricalRecord.removeAndPop();
    }

}
