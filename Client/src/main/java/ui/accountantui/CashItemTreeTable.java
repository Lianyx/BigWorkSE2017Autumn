package ui.accountantui;

import blService.billblService.CashBillReceiptblService;
import blService.billblService.ChargeBillReceiptblService;
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
import po.CashItemPO;
import ui.stockui.StockListItemPane;
import ui.util.BoardController;
import ui.util.ColumnDecorator;
import ui.util.ListPopup;
import ui.util.PaneFactory;
import vo.ListGoodsItemVO;
import vo.billReceiptVO.CashItemVO;
import vo.billReceiptVO.TransferItemVO;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

public class CashItemTreeTable extends JFXTreeTableView<CashItemVO>{

    private ObservableList<CashItemVO> observableList = FXCollections.observableArrayList();

    private SimpleDoubleProperty sum = new SimpleDoubleProperty(0);


    StackPane mainpane;

    public CashItemTreeTable(){
        super();

        mainpane = PaneFactory.getMainPane();

        ColumnDecorator columnDecorator = new ColumnDecorator();

        JFXTreeTableColumn<CashItemVO, String> name = new JFXTreeTableColumn<>("Name");
        name.setPrefWidth(120);
        columnDecorator.setupCellValueFactory(name,CashItemVO::nameProperty);
        name.setCellFactory((TreeTableColumn<CashItemVO, String> param) -> { return new GenericEditableTreeTableCell<>(new TextFieldEditorBuilder());
        });
        name.setOnEditCommit((TreeTableColumn.CellEditEvent<CashItemVO, String> t) -> {
            t.getTreeTableView().getTreeItem(t.getTreeTablePosition().getRow()).getValue().nameProperty().set(t.getNewValue());
        });

        JFXTreeTableColumn<CashItemVO, Double> price = new JFXTreeTableColumn<>("Price");
        price.setPrefWidth(115.5);
        columnDecorator.setupCellValueFactory(price, l -> l.priceProperty().asObject());
        price.setCellFactory((TreeTableColumn<CashItemVO, Double> param) -> { return new GenericEditableTreeTableCell<>(new DoubleTextFieldEditorBuilder());
        });
        price.setOnEditCommit((TreeTableColumn.CellEditEvent<CashItemVO, Double> t) -> {
            t.getTreeTableView().getTreeItem(t.getTreeTablePosition().getRow()).getValue().priceProperty().set(t.getNewValue());
        });

        JFXTreeTableColumn<CashItemVO, String> comment = new JFXTreeTableColumn<>("Comment");
        comment.setPrefWidth(120);
        columnDecorator.setupCellValueFactory(comment,CashItemVO::commentProperty);
        comment.setCellFactory((TreeTableColumn<CashItemVO, String> param) -> { return new GenericEditableTreeTableCell<>(new TextFieldEditorBuilder());
        });
        comment.setOnEditCommit((TreeTableColumn.CellEditEvent<CashItemVO, String> t) -> {
            t.getTreeTableView().getTreeItem(t.getTreeTablePosition().getRow()).getValue().commentProperty().set(t.getNewValue());
        });


        this.setRowFactory(tableView -> {
            JFXTreeTableRow row = new JFXTreeTableRow();
            row.setStyle("-fx-border-color: rgb(233,237,239); -fx-border-width: 0.3;");

            row.setOnMouseClicked((MouseEvent event) -> {
                CashItemVO cashItemVO = (CashItemVO)row.getTreeItem().getValue();

                if (event.getButton() == MouseButton.SECONDARY) {
                    ListPopup listPopup = new ListPopup();
                    JFXPopup popup = new JFXPopup(listPopup);
                    listPopup.getListview().setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {

                            CashItemPane cashItemPane = new CashItemPane(cashItemVO,observableList);
                            JFXDialog dialog = new JFXDialog(mainpane, cashItemPane, JFXDialog.DialogTransition.CENTER);
                            cashItemPane.setDialog(dialog);
                            dialog.show();
                            popup.hide();
                        }
                    });
                    listPopup.getListdelete().setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            observableList.remove(cashItemVO);
                        }
                    });
                    popup.show(row, JFXPopup.PopupVPosition.TOP, JFXPopup.PopupHPosition.RIGHT);
                }
                if(event.getClickCount() == 2){
                    CashItemPane cashItemPane = new CashItemPane(cashItemVO, observableList);
                    JFXDialog dialog = new JFXDialog(mainpane, cashItemPane, JFXDialog.DialogTransition.CENTER);
                    cashItemPane.setDialog(dialog);
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

        observableList.addListener(new ListChangeListener<CashItemVO>() {
            @Override
            public void onChanged(Change<? extends CashItemVO> c) {
                sum.set(0);
                double s = 0;
                for(CashItemVO cashItemVO:observableList){
                    s+=cashItemVO.getPrice();
                }
                sum.set(s);
            }
        });

        TreeItem<CashItemVO> root = new RecursiveTreeItem<>(observableList, RecursiveTreeObject::getChildren);
        this.setRoot(root);
        this.setEditable(true);
        this.setShowRoot(false);
        this.getColumns().setAll(name,price,comment);


    }


    public void setList(List<CashItemVO> list){
            observableList.setAll(list);
    }

    public void remove(CashItemVO cashItemVO){
        observableList.remove(cashItemVO);
    }

    public void add(CashItemVO cashItemVO){
        observableList.add(cashItemVO);
    }

    public ArrayList<CashItemVO> getList(){
        return new ArrayList<>(observableList);
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
