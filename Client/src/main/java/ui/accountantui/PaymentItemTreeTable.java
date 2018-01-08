package ui.accountantui;

import blService.billblService.PaymentBillReceiptblService;
import blService.stockblService.StockblService;
import com.jfoenix.controls.*;
import com.jfoenix.controls.cells.editors.DoubleTextFieldEditorBuilder;
import com.jfoenix.controls.cells.editors.IntegerTextFieldEditorBuilder;
import com.jfoenix.controls.cells.editors.TextFieldEditorBuilder;
import com.jfoenix.controls.cells.editors.base.GenericEditableTreeTableCell;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import ui.salesui.SalesReceiptPane;
import ui.stockui.StockListItemPane;
import ui.util.BoardController;
import ui.util.ColumnDecorator;
import ui.util.ListPopup;
import ui.util.PaneFactory;
import vo.ListGoodsItemVO;
import vo.billReceiptVO.TransferItemVO;
import vo.receiptVO.SalesReceiptListVO;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

public class PaymentItemTreeTable extends JFXTreeTableView<TransferItemVO>{

    private ObservableList<TransferItemVO> observableList = FXCollections.observableArrayList();

    private SimpleDoubleProperty sum = new SimpleDoubleProperty(0);

    private StackPane mainpane;

    public PaymentItemTreeTable(){
        super();

        mainpane = PaneFactory.getMainPane();

        ColumnDecorator columnDecorator = new ColumnDecorator();

        JFXTreeTableColumn<TransferItemVO, Integer> accountID = new JFXTreeTableColumn<>("AccountID");
        accountID.setPrefWidth(120);
        columnDecorator.setupCellValueFactory(accountID, l -> l.accountIDProperty().asObject());
        accountID.setCellFactory((TreeTableColumn<TransferItemVO, Integer> param) -> { return new GenericEditableTreeTableCell<>(new IntegerTextFieldEditorBuilder());
        });
        accountID.setOnEditCommit((TreeTableColumn.CellEditEvent<TransferItemVO, Integer> t) -> {
            t.getTreeTableView().getTreeItem(t.getTreeTablePosition().getRow()).getValue().accountIDProperty().set(t.getNewValue());
        });

        JFXTreeTableColumn<TransferItemVO, Double> price = new JFXTreeTableColumn<>("Price");
        price.setPrefWidth(115.5);
        columnDecorator.setupCellValueFactory(price, l -> l.sumProperty().asObject());
        price.setCellFactory((TreeTableColumn<TransferItemVO, Double> param) -> { return new GenericEditableTreeTableCell<>(new DoubleTextFieldEditorBuilder());
        });
        price.setOnEditCommit((TreeTableColumn.CellEditEvent<TransferItemVO, Double> t) -> {
            t.getTreeTableView().getTreeItem(t.getTreeTablePosition().getRow()).getValue().sumProperty().set(t.getNewValue());
        });

        JFXTreeTableColumn<TransferItemVO, String> comment = new JFXTreeTableColumn<>("Comment");
        comment.setPrefWidth(120);
        columnDecorator.setupCellValueFactory(comment,TransferItemVO::commentProperty);
        comment.setCellFactory((TreeTableColumn<TransferItemVO, String> param) -> { return new GenericEditableTreeTableCell<>(new TextFieldEditorBuilder());
        });
        comment.setOnEditCommit((TreeTableColumn.CellEditEvent<TransferItemVO, String> t) -> {
            t.getTreeTableView().getTreeItem(t.getTreeTablePosition().getRow()).getValue().commentProperty().set(t.getNewValue());
        });


        this.setRowFactory(tableView -> {
            JFXTreeTableRow row = new JFXTreeTableRow();
            row.setStyle("-fx-border-color: rgb(233,237,239); -fx-border-width: 0.3;");

            row.setOnMouseClicked((MouseEvent event) -> {
                TransferItemVO transferItemVO = (TransferItemVO)row.getTreeItem().getValue();
                //TransferItemVO listGoodsItemVO = (ListGoodsItemVO) row.getTreeItem().getValue();

                /**
                 * 右键时候，弹出popup，里面有view和delete，getListview命名不好，就是弹窗中listview里面的view，getlistdelete同样
                 **/
                if (event.getButton() == MouseButton.SECONDARY) {
                    ListPopup listPopup = new ListPopup();
                    JFXPopup popup = new JFXPopup(listPopup);
                    listPopup.getListview().setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            /**
                             进入listpane
                             **/
                            TransferItemPane transferItemPane = new TransferItemPane(transferItemVO,observableList);
                            JFXDialog dialog = new JFXDialog(mainpane, transferItemPane, JFXDialog.DialogTransition.CENTER);
                            transferItemPane.setDialog(dialog);
                            dialog.show();
                            popup.hide();
                        }
                    });
                    listPopup.getListdelete().setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            observableList.remove(transferItemVO);
                        }
                    });
                    popup.show(row, JFXPopup.PopupVPosition.TOP, JFXPopup.PopupHPosition.RIGHT);
                }
                if(event.getClickCount() == 2){
                    TransferItemPane transferItemPane = new TransferItemPane(transferItemVO, observableList);
                    JFXDialog dialog = new JFXDialog(mainpane, transferItemPane, JFXDialog.DialogTransition.CENTER);
                    transferItemPane.setDialog(dialog);
                    dialog.show();
                }
            });

            row.selectedProperty().addListener(e -> {
                if (row.isSelected()) {
                    row.toFront();
                } else {
                    row.setEffect(null);
                }
            });
            return row;
        });

        observableList.addListener(new ListChangeListener<TransferItemVO>() {
            @Override
            public void onChanged(Change<? extends TransferItemVO> c) {
                sum.set(0);
                double s = 0;
                for(TransferItemVO transferItemVO:observableList){
                    s+=transferItemVO.getSum();
                }
                sum.set(s);
            }
        });

        TreeItem<TransferItemVO> root = new RecursiveTreeItem<>(observableList, RecursiveTreeObject::getChildren);
        this.setRoot(root);
        this.setEditable(true);
        this.setShowRoot(false);
        this.getColumns().setAll(accountID,price,comment);


    }


    public void setList(List<TransferItemVO> list){
        observableList.setAll(list);
    }

    public void remove(TransferItemVO transferItemVO){
        observableList.remove(transferItemVO);
    }

    public ArrayList<TransferItemVO> getList(){
        return new ArrayList<>(observableList);
    }


    public void add(TransferItemVO transferItemVO){
        observableList.add(transferItemVO);
    }

    public double getSum() {
        return sum.get();
    }

    public SimpleDoubleProperty sumProperty() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum.set(sum);
    }
}
