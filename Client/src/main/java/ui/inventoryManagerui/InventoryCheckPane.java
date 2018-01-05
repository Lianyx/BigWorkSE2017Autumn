package ui.inventoryManagerui;

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
import vo.inventoryVO.InventoryCheckItemVO;
import vo.inventoryVO.InventoryCheckVO;

import java.io.IOException;
import java.util.Set;

public class InventoryCheckPane extends Refreshable{

    InventoryCheckTreeTable checkTreeTable;

    @FXML
    BorderPane borderpane;

    Set<InventoryCheckItemVO> set;

    BoardController boardController;

    InventoryShowblService showblService;

    Pagination pagination;

    StackPane mainpane;

    public boolean historyAdd  = false;

    public InventoryCheckPane(InventoryShowblService showblService,BoardController boardController,StackPane mainpane) throws IOException {
        super();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/inventoryui/inventoryCheckPane.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        fxmlLoader.load();

        this.mainpane = mainpane;
        this.showblService = showblService;
        this.boardController = boardController;

        checkTreeTable = new InventoryCheckTreeTable(showblService,boardController,mainpane);

    }

    public void setShowblService(InventoryShowblService showblService){this.showblService = showblService;}

    public void refreshCheckTreeTable(){
        int current=pagination.getCurrentPageIndex();
        int max = checkTreeTable.getObservableList().size() /checkTreeTable.getRowsPerPage()+1;
        pagination = new Pagination((checkTreeTable.getObservableList().size() /checkTreeTable.getRowsPerPage()+1 ), 0);
        System.out.println((checkTreeTable.getObservableList().size() /checkTreeTable.getRowsPerPage()+1 ));
        pagination.setPageFactory(checkTreeTable::createPage);
        pagination.setPrefWidth(600);
        borderpane.setCenter(pagination);
        if(current-1>=0&&current-1<=max)
            pagination.setCurrentPageIndex(current-1);
        else
            pagination.setCurrentPageIndex(0);
    }

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
            InventoryCheckPane.LoadingTask task = new InventoryCheckPane.LoadingTask();
            task.valueProperty().addListener(new ChangeListener<Boolean>() {
                @Override
                public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                    if (task.getIntegerProperty() == 1) {
                        try {
                            checkTreeTable.setInventoryCheck(set);
                            pagination = new Pagination((checkTreeTable.getObservableList().size() /checkTreeTable.getRowsPerPage()+1 ), 0);
                            pagination.setPageFactory(checkTreeTable::createPage);
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
            InventoryCheckVO inventoryCheckVO = showblService.inventoryCheck();
            set = inventoryCheckVO.getCheckList();
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
