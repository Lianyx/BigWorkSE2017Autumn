package ui.accountantui;

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
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import ui.stockui.StockListItemPane;
import ui.util.BoardController;
import ui.util.ListPopup;
import vo.ListGoodsItemVO;
import vo.billReceiptVO.TransferItemVO;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

public class ChargeItemTreeTable extends JFXTreeTableView<TransferItemVO>{

    private ObservableList<TransferItemVO> observableList = FXCollections.observableArrayList();

    private SimpleDoubleProperty sum = new SimpleDoubleProperty(0);

    ChargeBillReceiptblService chargeBillReceiptblService;
    BoardController boardController;
    StackPane mainpane;

    public ChargeItemTreeTable(){
        super();

        //private int accountID;
        //private double sum;
        //private String commnet;

        JFXTreeTableColumn<TransferItemVO,Integer> accountID = new JFXTreeTableColumn<>("accountID");
        accountID.setPrefWidth(120);
        setupCellValueFactory(accountID,l->l.accountIDProperty().asObject());
        accountID.setCellFactory((TreeTableColumn<TransferItemVO, Integer> param) -> {
            return new GenericEditableTreeTableCell<>(
                    new IntegerTextFieldEditorBuilder());
        });
        accountID.setOnEditCommit((TreeTableColumn.CellEditEvent<TransferItemVO, Integer> t) -> {
            t.getTreeTableView()
                    .getTreeItem(t.getTreeTablePosition()
                            .getRow())
                    .getValue().accountIDProperty().set(t.getNewValue());
        });

        JFXTreeTableColumn<TransferItemVO, Double> price = new JFXTreeTableColumn<>("Price");
        price.setPrefWidth(120);
        setupCellValueFactory(price,l->l.sumProperty().asObject());
        price.setCellFactory((TreeTableColumn<TransferItemVO,Double> param) -> {
            return new GenericEditableTreeTableCell<>(
                    new DoubleTextFieldEditorBuilder());
        });
        price.setOnEditCommit((TreeTableColumn.CellEditEvent<TransferItemVO, Double> t) -> {
            t.getTreeTableView()
                    .getTreeItem(t.getTreeTablePosition()
                            .getRow())
                    .getValue().sumProperty().set(t.getNewValue());
        });

        JFXTreeTableColumn<TransferItemVO, String> comment = new JFXTreeTableColumn<>("Comment");
        comment.setPrefWidth(120);
        setupCellValueFactory(comment,TransferItemVO::commentProperty);
        comment.setCellFactory((TreeTableColumn<TransferItemVO, String> param) -> {
            return new GenericEditableTreeTableCell<>(
                    new TextFieldEditorBuilder());
        });
        comment.setOnEditCommit((TreeTableColumn.CellEditEvent<TransferItemVO, String> t) -> {
            t.getTreeTableView()
                    .getTreeItem(t.getTreeTablePosition()
                            .getRow())
                    .getValue().commentProperty().set(t.getNewValue());
        });



        this.setRowFactory(tableView->{
            JFXTreeTableRow row=new JFXTreeTableRow();
            row.setStyle("-fx-border-color: rgb(233,237,239); -fx-border-width: 0.3;");
            row.setOnMouseClicked((MouseEvent event) -> {
                if(event.getButton() == MouseButton.SECONDARY){
                    ListPopup listPopup = new ListPopup();
                    JFXPopup popup = new JFXPopup(listPopup);
                    listPopup.getListview().setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            //StockListItemPane stockListItemPane = new StockListItemPane((ListGoodsItemVO)row.getTreeItem().getValue());
                            //JFXDialog dialog = new JFXDialog(mainpane, stockListItemPane, JFXDialog.DialogTransition.CENTER);
                            //dialog.show();
                            popup.hide();
                        }
                    });
                    popup.show(row, JFXPopup.PopupVPosition.TOP,JFXPopup.PopupHPosition.RIGHT);
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

        TreeItem<TransferItemVO> root = new RecursiveTreeItem<>(observableList, RecursiveTreeObject::getChildren);
        this.setRoot(root);
        this.setEditable(true);
        this.setShowRoot(false);
        this.getColumns().setAll(accountID,price,comment);

        //  observableList.addListener(setupChanger(this));

    }


    public void setChargeBillReceiptblService(ChargeBillReceiptblService chargeBillReceiptblService) {
        this.chargeBillReceiptblService = chargeBillReceiptblService;
    }

    public void setBoardController(BoardController boardController) {
        this.boardController = boardController;
    }

    public void setMainpane(StackPane mainpane) {
        this.mainpane = mainpane;
    }

    public void setList(List<TransferItemVO> list){
        observableList.setAll(list);
    }

    public void remove(TransferItemVO transferItemVO){
        observableList.remove(transferItemVO);
    }

    public void add(TransferItemVO transferItemVO){
        observableList.add(transferItemVO);
    }

    public ArrayList<TransferItemVO> getList(){
        return new ArrayList<>(observableList);
    }

    private <T> void setupCellValueFactory(JFXTreeTableColumn<TransferItemVO, T> column, Function<TransferItemVO, ObservableValue<T>> mapper) {
        column.setCellValueFactory((TreeTableColumn.CellDataFeatures<TransferItemVO, T> param) -> {
            if (column.validateValue(param)) {
                return mapper.apply(param.getValue().getValue());
            } else {
                return column.getComputedValue(param);
            }
        });
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
