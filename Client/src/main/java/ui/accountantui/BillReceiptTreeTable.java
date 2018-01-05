package ui.accountantui;

import blService.checkblService.ReceiptblService;
import blServiceStub.billblservice_Stub.CashblService_Stub;
import blServiceStub.billblservice_Stub.ChargeblService_Stub;
import blServiceStub.billblservice_Stub.PaymentblService_Stub;
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
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.util.Callback;
import javafx.util.Duration;
import ui.util.BoardController;
import ui.util.CircleImageView;
import vo.AccountListVO;
import vo.billReceiptVO.CashBillReceiptVO;
import vo.billReceiptVO.ChargeBillReceiptVO;
import vo.billReceiptVO.PaymentBillReceiptVO;
import vo.receiptVO.ReceiptVO;

import java.io.Serializable;
import java.util.List;

public class BillReceiptTreeTable extends JFXTreeTableView<ReceiptVO> implements Serializable{

    private ObservableList<ReceiptVO> observableList = FXCollections.observableArrayList();
    private ObservableList<ReceiptVO> observableListtemp;
    private BoardController boardController;
    private StackPane mainpane;
    private int rowsPerPage = 7;

    private ReceiptblService receiptblService;

    public ObservableList<ReceiptVO> getObservableList() {
        return observableList;
    }

    public int getRowsPerPage() {
        return rowsPerPage;
    }


    public BillReceiptTreeTable(ReceiptblService receiptblService, BoardController boardController, StackPane mainpane) {
        super();
        this.receiptblService = receiptblService;
        this.boardController = boardController;
        this.mainpane = mainpane;
        JFXTreeTableColumn<ReceiptVO,Boolean> choose = new JFXTreeTableColumn("  ");
        choose.setPrefWidth(40);
        Callback<TreeTableColumn<ReceiptVO,Boolean>, TreeTableCell<ReceiptVO,Boolean>> chooseCellFactory = (TreeTableColumn<ReceiptVO,Boolean> p) -> new ChooseBoxCell();
        choose.setCellValueFactory((TreeTableColumn.CellDataFeatures<ReceiptVO, Boolean> param) -> {
            if (choose.validateValue(param)) {
                return param.getValue().getValue().selectedProperty();
            } else {
                return choose.getComputedValue(param);
            }
        });
        choose.setCellFactory(chooseCellFactory);

        JFXTreeTableColumn<ReceiptVO, String> id = new JFXTreeTableColumn("ID");
        id.setPrefWidth(100);
        id.setCellValueFactory((TreeTableColumn.CellDataFeatures<ReceiptVO, String> param) -> {
            if (id.validateValue(param)) {
                return new ReadOnlyObjectWrapper(param.getValue().getValue().getId());
            } else {
                return id.getComputedValue(param);
            }
        });


        JFXTreeTableColumn<ReceiptVO, Integer> operator = new JFXTreeTableColumn("Operator");
        operator.setPrefWidth(100);
        operator.setCellValueFactory((TreeTableColumn.CellDataFeatures<ReceiptVO, Integer> param) -> {
            if (operator.validateValue(param)) {
                return new ReadOnlyObjectWrapper(param.getValue().getValue().getOperatorId());
            } else {
                return operator.getComputedValue(param);
            }
        });

        JFXTreeTableColumn<ReceiptVO, String> time = new JFXTreeTableColumn("CreateTime");
        time.setPrefWidth(100);
        time.setCellValueFactory((TreeTableColumn.CellDataFeatures<ReceiptVO, String> param) -> {
            if (time.validateValue(param)) {
                return new ReadOnlyObjectWrapper(param.getValue().getValue().getCreateTime().toString());
            } else {
                return time.getComputedValue(param);
            }
        });


        JFXTreeTableColumn<ReceiptVO, String> state = new JFXTreeTableColumn("State");
        state.setPrefWidth(100);
        state.setCellValueFactory((TreeTableColumn.CellDataFeatures<ReceiptVO, String> param) -> {
            if (state.validateValue(param)) {
                return new ReadOnlyObjectWrapper(param.getValue().getValue().getReceiptState());
            } else {
                return state.getComputedValue(param);
            }
        });

        this.setRowFactory(tableView->{
            JFXTreeTableRow row=new JFXTreeTableRow();
            row.setPrefHeight(55);
            row.setStyle("-fx-border-color: rgb(233,237,239); -fx-border-width: 0.3;");
            row.setOnMouseClicked((MouseEvent event) -> {
                if(event.getClickCount()==2){
                    try{
                        if(receiptblService instanceof PaymentblService_Stub){
                            System.out.print("pay");
                            //boardController.switchTo(new PaymentBillReceiptDetailPane((PaymentBillReceiptVO)row.getTreeItem().getValue(),boardController));
                            //boardController.switchTo(new PaymentDetailPane(boardController,mainpane));
                            PaymentDetailPane paymentDetailPane = new PaymentDetailPane((PaymentBillReceiptVO) row.getTreeItem().getValue(),boardController,mainpane);
                            paymentDetailPane.refresh(true);
                        }
                        else if(receiptblService instanceof ChargeblService_Stub){
                            System.out.print("charge");
                            ChargeDetailPane chargeDetailPane = new ChargeDetailPane((ChargeBillReceiptVO) row.getTreeItem().getValue(),boardController,mainpane);
                            chargeDetailPane.refresh(true);
                        }
                        else if(receiptblService instanceof CashblService_Stub){
                            System.out.print("Cash");
                            CashDetailPane cashDetailPane = new CashDetailPane((CashBillReceiptVO) row.getTreeItem().getValue(),boardController,mainpane);
                            cashDetailPane.refresh(true);
                        }
                        //boardController.switchTo(new UserDetailPane((UserListVO) row.getTreeItem().getValue(),boardController));
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

        this.getColumns().addAll(choose,id,operator,time,state);
    }

    public void setReceipt(List<ReceiptVO> list){
        observableList.setAll(list);
    }

    public void removeReceipt(ReceiptVO receiptVO){
        observableList.remove(receiptVO);
    }

    public Node createPage(int pageIndex) {
        int fromIndex = pageIndex * rowsPerPage;
        int toIndex = Math.min(fromIndex + rowsPerPage, observableList.size());
        observableListtemp = FXCollections.observableList(observableList.subList(fromIndex, toIndex));
        final TreeItem<ReceiptVO> root = new RecursiveTreeItem<>(observableListtemp, RecursiveTreeObject::getChildren);
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
        timeline.play();
        return new BorderPane(this);
    }

    private class ChooseBoxCell extends JFXTreeTableCell<ReceiptVO,Boolean> {
        private JFXCheckBox cb = new JFXCheckBox("");

        public ChooseBoxCell(){
            cb.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    if(getTreeTableRow()!=null&&getTreeTableRow().getItem()!=null){
                        ReceiptVO receiptVO = getTreeTableRow().getItem();
                        receiptVO.setSelected(!receiptVO.isSelected());
                        System.out.println(receiptVO);
                        if(receiptVO.isSelected()){
                            BillReceiptChosenItem.addItem(receiptVO);
                        }else{
                            BillReceiptChosenItem.removeItem(receiptVO);
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

    private class ImageCell extends JFXTreeTableCell {
        private CircleImageView civ = new CircleImageView();

        @Override
        public void updateItem(Object item,boolean empty){
            super.updateItem(item,empty);
            if(empty){
                setGraphic(null);
            }else{
                civ.setImage((Image)item);
                civ.setRadius(17);
                civ.setTranslateY(-8);
                setGraphic(civ);
            }
        }
    }

    private class ButtonCell extends JFXTreeTableCell {
        private JFXButton civ = new JFXButton("sabi");

        @Override
        public void updateItem(Object item,boolean empty){
            super.updateItem(item,empty);
            if(empty){
                setGraphic(null);
            }else{
                setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                civ.setStyle("-fx-background-color: rgba(182,0,107,0.19); -fx-text-fill: white;-fx-background-radius: 10;");
                setGraphic(civ);
            }
        }
    }
}
