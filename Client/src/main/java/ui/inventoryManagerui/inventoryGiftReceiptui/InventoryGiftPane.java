package ui.inventoryManagerui.inventoryGiftReceiptui;

import blService.inventoryblService.InventoryblService;
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
import ui.userui.usermanagerui.BoardController;
import ui.userui.usermanagerui.FilterPane;
import ui.util.HistoricalRecord;
import ui.util.Refreshable;
import vo.inventoryVO.uiReceipt.InventoryGiftuiVO;

import java.io.IOException;
import java.util.Set;

public class InventoryGiftPane extends Refreshable {

    InventoryGiftTreeTable inventoryGiftTreeTable;

    @FXML
    BorderPane borderpane;

    Set<InventoryGiftuiVO> set;

    BoardController boardController;

    InventoryblService inventoryblService;

    Pagination pagination;

    StackPane mainpane;

    FilterPane filterPane;

    public boolean historyAdd = false;

    @FXML
    JFXButton filter;

    public InventoryGiftPane(InventoryblService inventoryblService, BoardController boardController, StackPane mainpane) throws IOException {
        super();
        this.boardController = boardController;
        this.inventoryblService = inventoryblService;
        this.mainpane = mainpane;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/inventoryui/inventoryGiftPane.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        fxmlLoader.load();
        inventoryGiftTreeTable = new InventoryGiftTreeTable(inventoryblService, boardController, mainpane);
        /*
        PopOver filterPopOver = new PopOver();
        filterPopOver.setDetachable(false);
        filterPopOver.setArrowLocation(PopOver.ArrowLocation.TOP_RIGHT);
        filterPane=new FilterPane();
        filterPane.setUserSearchVO(this.userSearchVO);
        filterPane.setUlp(this);
        filterPopOver.setContentNode(filterPane);

        filter.setOnMouseClicked(e -> filterPopOver.show(filter));
         */
    }

    public void setInventoryblService(InventoryblService inventoryblService) {
        this.inventoryblService = inventoryblService;
    }

    @FXML
    public void deleteList() {

    }

    public void refreshUlv() {
        int current = pagination.getCurrentPageIndex();
        int max = inventoryGiftTreeTable.getObservableList().size() / inventoryGiftTreeTable.getRowsPerPage() + 1;
        pagination = new Pagination((inventoryGiftTreeTable.getObservableList().size() / inventoryGiftTreeTable.getRowsPerPage() + 1), 0);
        System.out.println((inventoryGiftTreeTable.getObservableList().size() / inventoryGiftTreeTable.getRowsPerPage() + 1));
        pagination.setPageFactory(inventoryGiftTreeTable::createPage);
        pagination.setPrefWidth(600);
        borderpane.setCenter(pagination);
        if (current - 1 >= 0 && current - 1 <= max)
            pagination.setCurrentPageIndex(current - 1);
        else
            pagination.setCurrentPageIndex(0);
    }

    @FXML
    public void add() {

    }

    public void switchPane(boolean toSwtich) {
        if (toSwtich == true) {
            System.out.println("??/**/");
            boardController.switchTo(this);
        } else {
            if (historyAdd) {
                HistoricalRecord.addPane(this);
                historyAdd = false;
            }
            boardController.setAll(this);
        }
    }

    @Override
    public void refresh(boolean toSwitch) {
        boardController.Loading();
        try {
            LoadingTask task = new LoadingTask();
            task.valueProperty().addListener(new ChangeListener<Boolean>() {
                @Override
                public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                    if (task.getIntegerProperty() == 1) {
                        try {
                            inventoryGiftTreeTable.setGiftReceipts(set);
                            pagination = new Pagination((inventoryGiftTreeTable.getObservableList().size() / inventoryGiftTreeTable.getRowsPerPage() + 1), 0);
                            pagination.setPageFactory(inventoryGiftTreeTable::createPage);
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
        } catch (Exception e) {
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
        protected Boolean call() throws Exception {
            set = inventoryblService.getAll();
            if (set != null) {
                Thread.sleep(2000);
                integerProperty.setValue(1);
                return true;
            } else {
                Thread.sleep(2000);
                integerProperty.set(0);
                return false;
            }
        }
    }


}
