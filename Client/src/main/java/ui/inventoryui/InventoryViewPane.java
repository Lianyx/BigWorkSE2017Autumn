package ui.inventoryui;

import blService.inventoryblService.InventoryShowblService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import ui.util.BoardController;
import ui.util.HistoricalRecord;
import ui.util.Refreshable;
import vo.inventoryVO.InventoryViewItemVO;
import vo.inventoryVO.InventoryViewVO;

import java.io.IOException;
import java.util.Set;

public class InventoryViewPane extends Refreshable {

    InventoryViewTreeTable viewTreeTable;

    @FXML
    BorderPane borderpane;

    Set<InventoryViewItemVO> set;

    BoardController boardController;

    InventoryShowblService showblService;

    Pagination pagination;

    StackPane mainpane;

    public boolean historyAdd  = false;



    public InventoryViewPane(InventoryShowblService showblService ,BoardController boardController,StackPane mainPane) throws IOException {
        super();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/inventoryui/inventoryViewPane.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        fxmlLoader.load();

        this.mainpane = mainPane;
        this.boardController = boardController;
        this.showblService = showblService;
        viewTreeTable = new InventoryViewTreeTable(showblService,boardController,mainpane);
    }

    public void setShowblService(InventoryShowblService showblService){this.showblService = showblService;}

    public void switchPane(boolean toSwtich){
        if(toSwtich==true){
            System.out.println("??/**/");
            boardController.switchTo(this);
        }else{
            if(historyAdd){
                HistoricalRecord.addPane(this);
                historyAdd=false;
            }
            boardController.setAll(this);
        }
    }


    @Override
    public void refresh(boolean toSwitch) {
        boardController.Loading();
        try {
            InventoryViewPane.LoadingTask task = new InventoryViewPane.LoadingTask();
            task.valueProperty().addListener(new ChangeListener<Boolean>() {
                @Override
                public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                    if (task.getIntegerProperty() == 1) {
                        try {
                            viewTreeTable.setInventoryView(set);
                            pagination = new Pagination((viewTreeTable.getObservableList().size() /viewTreeTable.getRowsPerPage()+1 ), 0);
                            pagination.setPageFactory(viewTreeTable::createPage);
                            pagination.setPrefWidth(600);
                            borderpane.setCenter(pagination);
                            switchPane(toSwitch);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else if (task.getIntegerProperty() == 0) {
                        try {
                            JFXDialogLayout jfxDialogLayout = new JFXDialogLayout();
                            jfxDialogLayout.setHeading(new Label("Wrong"));
                            jfxDialogLayout.setBody(new Label("sabi"));
                            JFXButton button = new JFXButton("Last");
                            JFXButton re = new JFXButton("Re");
                            JFXDialog dialog = new JFXDialog(mainpane, jfxDialogLayout, JFXDialog.DialogTransition.CENTER);
                            button.setOnAction(e -> {
                                dialog.close();
                                boardController.Ret();
                            });
                            re.setOnAction(e -> {
                                dialog.close();
                                refresh(false);
                            });
                            jfxDialogLayout.setActions(button, re);
                            dialog.show();

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            });

            new Thread(task).start();
        }catch (Exception e) {

            e.printStackTrace();
        }

    }

    class LoadingTask extends Task<Boolean> {

        private SimpleIntegerProperty integerProperty = new SimpleIntegerProperty(-1);



        public int getIntegerProperty() {
            return integerProperty.get();
        }

        public SimpleIntegerProperty integerPropertyProperty() {
            return integerProperty;
        }

        @Override
        protected Boolean call() throws Exception{
            InventoryViewVO inventoryViewVO = showblService.inventoryView("","");
            set = inventoryViewVO.getViewList();
            if(set!=null){
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
