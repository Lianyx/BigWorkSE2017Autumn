package ui.inventoryManagerui.inventoryGiftReceiptui;

import blService.inventoryblService.InventoryblService;
import com.jfoenix.controls.*;
import com.jfoenix.controls.cells.editors.base.JFXTreeTableCell;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableCell;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.util.Callback;
import javafx.util.Duration;
import ui.util.BoardController;
import util.ReceiptState;
import vo.inventoryVO.uiReceipt.InventoryGiftChosenItem;
import vo.inventoryVO.uiReceipt.InventoryGiftuiVO;

import java.time.LocalDateTime;
import java.util.Set;

public class InventoryGiftTreeTable extends JFXTreeTableView<InventoryGiftuiVO> {
    private ObservableList<InventoryGiftuiVO> observableList = FXCollections.observableArrayList();
    private ObservableList<InventoryGiftuiVO> observableListfilter = observableList;
    private ObservableList<InventoryGiftuiVO> observableListtemp;
    private int rowsPerPage = 7;
    private BoardController boardController;
    private InventoryblService inventoryblService;
    private StackPane mainpane;


    public ObservableList<InventoryGiftuiVO> getObservableList() {
        return observableListfilter;
    }

    public int getRowsPerPage() {
        return rowsPerPage;
    }

    public InventoryGiftTreeTable( InventoryblService inventoryblService, BoardController boardController,StackPane mainpane) {
        super();
        this.boardController = boardController;
        this.inventoryblService = inventoryblService;
        this.mainpane = mainpane;

        JFXTreeTableColumn<InventoryGiftuiVO,Boolean> choose = new JFXTreeTableColumn("  ");
        choose.setPrefWidth(40);
        Callback<TreeTableColumn<InventoryGiftuiVO,Boolean>, TreeTableCell<InventoryGiftuiVO,Boolean>> chooseCellFactory = (TreeTableColumn<InventoryGiftuiVO,Boolean> p) -> new ChooseBoxCell();
        choose.setCellValueFactory((TreeTableColumn.CellDataFeatures<InventoryGiftuiVO, Boolean> param) -> {
            if (choose.validateValue(param)) {
                return param.getValue().getValue().selectedProperty();
            } else {
                return choose.getComputedValue(param);
            }
        });
        choose.setCellFactory(chooseCellFactory);

        JFXTreeTableColumn<InventoryGiftuiVO, String> receiptId = new JFXTreeTableColumn("单据编号");
        receiptId.setPrefWidth(80);
        receiptId.setCellValueFactory((TreeTableColumn.CellDataFeatures<InventoryGiftuiVO, String> param) -> {
            if (receiptId.validateValue(param)) {
                return new ReadOnlyObjectWrapper(param.getValue().getValue().getId());
            } else {
                return receiptId.getComputedValue(param);
            }
        });

        JFXTreeTableColumn<InventoryGiftuiVO, String> operatorId = new JFXTreeTableColumn("操作员");
        operatorId.setPrefWidth(80);
        operatorId.setCellValueFactory((TreeTableColumn.CellDataFeatures<InventoryGiftuiVO, String> param) -> {
            if (operatorId.validateValue(param)) {
                return new ReadOnlyObjectWrapper(param.getValue().getValue().getOperatorId());
            } else {
                return operatorId.getComputedValue(param);
            }
        });

        JFXTreeTableColumn<InventoryGiftuiVO, LocalDateTime> createTime = new JFXTreeTableColumn("创建时间");
        createTime.setPrefWidth(80);
        createTime.setCellValueFactory((TreeTableColumn.CellDataFeatures<InventoryGiftuiVO,LocalDateTime> param) -> {
            if (createTime.validateValue(param)) {
                return new ReadOnlyObjectWrapper(param.getValue().getValue().getCreateTime());
            } else {
                return createTime.getComputedValue(param);
            }
        });

        JFXTreeTableColumn<InventoryGiftuiVO, LocalDateTime> lastModifiedTime = new JFXTreeTableColumn("上次修改时间");
        lastModifiedTime.setPrefWidth(80);
        lastModifiedTime.setCellValueFactory((TreeTableColumn.CellDataFeatures<InventoryGiftuiVO,LocalDateTime> param) -> {
            if (lastModifiedTime.validateValue(param)) {
                return new ReadOnlyObjectWrapper(param.getValue().getValue().getCreateTime());
            } else {
                return lastModifiedTime.getComputedValue(param);
            }
        });

        JFXTreeTableColumn<InventoryGiftuiVO, ReceiptState> state = new JFXTreeTableColumn("状态");
        state.setPrefWidth(80);
        state.setCellValueFactory((TreeTableColumn.CellDataFeatures<InventoryGiftuiVO,ReceiptState> param) -> {
            if (state.validateValue(param)) {
                return new ReadOnlyObjectWrapper(param.getValue().getValue().getReceiptState());
            } else {
                return state.getComputedValue(param);
            }
        });
        Callback<TreeTableColumn<InventoryGiftuiVO, ReceiptState>, TreeTableCell<InventoryGiftuiVO, ReceiptState>> typeCellFactory = (TreeTableColumn<InventoryGiftuiVO,ReceiptState> p) -> new ButtonCell();
       state.setCellFactory(typeCellFactory);


        this.setRowFactory(tableView->{
            JFXTreeTableRow row=new JFXTreeTableRow();
            row.setPrefHeight(55);
            row.setStyle("-fx-border-color: rgb(233,237,239); -fx-border-width: 0.3;");
            row.setOnMouseClicked((MouseEvent event) -> {
                if(event.getClickCount()==2){
                    try{
                        InventoryGiftDetailPane inventoryGiftDetailPane = new InventoryGiftDetailPane((InventoryGiftuiVO)row.getTreeItem().getValue(),boardController,mainpane);
                        boardController.switchTo(inventoryGiftDetailPane);
                        inventoryGiftDetailPane.refresh(true);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }

            });
            row.selectedProperty().addListener(e->{
                if(row.isSelected()){
                    row.toFront();
                }else{
                    row.setEffect(null);
                }
            });
            return row;
        });
        setCurrentItemsCount(rowsPerPage);
        this.setShowRoot(false);
        
        this.getColumns().addAll(choose,receiptId,operatorId,createTime,lastModifiedTime,state);

    }
    
    public void setGiftReceipts(Set<InventoryGiftuiVO> receipts){observableList.setAll(receipts);}
    
    public void removeGiftReceipt(InventoryGiftuiVO vo){observableList.remove(vo);}

    public Node createPage(int pageIndex) {


      /*  if(userSearchVO.getUserCategory()!=null)
            observableListfilter=observableList.filtered(t->{
                return userSearchVO.getUserCategory()==t.getUserCategory();
            });
        else*/
            observableListfilter=observableList;
        int fromIndex = pageIndex * rowsPerPage;
        int toIndex = Math.min(fromIndex + rowsPerPage, observableListfilter.size());
        observableListtemp = FXCollections.observableList(observableListfilter.subList(fromIndex, toIndex));
        final TreeItem<InventoryGiftuiVO> root = new RecursiveTreeItem<>(observableListtemp, RecursiveTreeObject::getChildren);
        this.setRoot(root);
        this.setStyle("-fx-border-color: transparent; -fx-padding: 0; -fx-background-color: transparent");


        Timeline timeline = new Timeline();
        timeline.getKeyFrames().add(
                new KeyFrame(Duration.ZERO,
                        new KeyValue(this.opacityProperty(), 0)
                )
        );

        for (int i = 1; i <= 10; i++) {
            timeline.getKeyFrames().add(
                    new KeyFrame(new Duration(i * 80),
                            new KeyValue(this.opacityProperty(), i / 10.0)
                    )
            );
        }

        return new BorderPane(this);
    }



    private class ChooseBoxCell extends JFXTreeTableCell<InventoryGiftuiVO,Boolean> {
        private JFXCheckBox cb = new JFXCheckBox("");

        public ChooseBoxCell(){
            cb.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    if(getTreeTableRow()!=null&&getTreeTableRow().getItem()!=null){
                        InventoryGiftuiVO InventoryGiftuiVO=getTreeTableRow().getItem();
                        System.out.println(InventoryGiftuiVO);
                        InventoryGiftuiVO.setSelected(!InventoryGiftuiVO.isSelected());
                        if(InventoryGiftuiVO.isSelected()){
                            InventoryGiftChosenItem.addItem(InventoryGiftuiVO);
                        }else{
                            InventoryGiftChosenItem.removeItem(InventoryGiftuiVO);
                        }
                    }
                }
            });


        }


        @Override
        public void updateItem(Boolean item,boolean empty){
            super.updateItem(item,empty);

            if(empty){
                setGraphic(null);
            }else{
                setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                cb.setSelected(item);
                setGraphic(cb);
            }


        }



    }

    private class ButtonCell extends JFXTreeTableCell<InventoryGiftuiVO,ReceiptState> {
        private JFXButton civ = new JFXButton("");

        @Override
        public void updateItem(ReceiptState item,boolean empty){
            super.updateItem(item,empty);
            if(empty){
                setGraphic(null);
            }else{
                setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                civ.setText(item.toString());
                setGraphic(civ);
            }
        }
    }

}
