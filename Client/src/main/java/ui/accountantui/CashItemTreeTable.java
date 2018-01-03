package ui.accountantui;

import blService.billblService.CashBillReceiptblService;
import blService.billblService.ChargeBillReceiptblService;
import blService.billblService.PaymentBillReceiptblService;
import blService.stockblService.StockblService;
import com.jfoenix.controls.*;
import com.jfoenix.controls.cells.editors.IntegerTextFieldEditorBuilder;
import com.jfoenix.controls.cells.editors.base.GenericEditableTreeTableCell;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
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
import ui.userui.usermanagerui.BoardController;
import ui.util.ListPopup;
import vo.ListGoodsItemVO;
import vo.billReceiptVO.CashItemVO;
import vo.billReceiptVO.TransferItemVO;

import java.util.List;
import java.util.Set;
import java.util.function.Function;

public class CashItemTreeTable extends JFXTreeTableView<CashItemVO>{

    private ObservableList<CashItemVO> observableList = FXCollections.observableArrayList();

    //StockblService stockblService;
    //PaymentBillReceiptblService paymentBillReceiptblService;
    //ChargeBillReceiptblService chargeBillReceiptblService;
    CashBillReceiptblService cashBillReceiptblService;
    BoardController boardController;
    StackPane mainpane;

    public CashItemTreeTable(){
        super();

        //private int accountID;
        //private double sum;
        //private String commnet;

        JFXTreeTableColumn<CashItemVO,String> name = new JFXTreeTableColumn<>("Name");
        name.setPrefWidth(97);
        setupCellValueFactory(name,CashItemVO::nameProperty);

        JFXTreeTableColumn<CashItemVO, Double> price = new JFXTreeTableColumn<>("Price");
        price.setPrefWidth(97);
        setupCellValueFactory(price,l->l.priceProperty().asObject());


        /*JFXTreeTableColumn<ListGoodsItemVO, Integer> goodsNum = new JFXTreeTableColumn<>("Num");
        goodsNum.setPrefWidth(97);
        setupCellValueFactory(goodsNum,l->l.goodsNumProperty().asObject());
        //setupCellValueFactory(goodsName,1->1.goodsNumProperty().asObject());
        goodsNum.setCellFactory((TreeTableColumn<ListGoodsItemVO, Integer> param) -> {
            return new GenericEditableTreeTableCell<>(
                    new IntegerTextFieldEditorBuilder());
        });
        goodsNum.setOnEditCommit((TreeTableColumn.CellEditEvent<ListGoodsItemVO, Integer> t) -> {
            t.getTreeTableView()
                    .getTreeItem(t.getTreeTablePosition()
                            .getRow())
                    .getValue().goodsNumProperty().set(t.getNewValue());
        });*/


        JFXTreeTableColumn<CashItemVO, String> comment = new JFXTreeTableColumn<>("Comment");
        comment.setPrefWidth(96);
        setupCellValueFactory(comment,CashItemVO::commentProperty);


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

        TreeItem<CashItemVO> root = new RecursiveTreeItem<>(observableList, RecursiveTreeObject::getChildren);
        this.setRoot(root);
        this.setEditable(true);
        this.setShowRoot(false);
        this.getColumns().setAll(name,price,comment);

        //  observableList.addListener(setupChanger(this));

    }


    public void setCashBillReceiptblService(CashBillReceiptblService cashBillReceiptblService) {
        this.cashBillReceiptblService = cashBillReceiptblService;
    }

    public void setBoardController(BoardController boardController) {
        this.boardController = boardController;
    }

    public void setMainpane(StackPane mainpane) {
        this.mainpane = mainpane;
    }

    public void setList(List<CashItemVO> list){
            observableList.setAll(list);
    }

    public void remove(CashItemVO cashItemVO){
        observableList.remove(cashItemVO);
    }

    public void addGood(CashItemVO cashItemVO){
        observableList.add(cashItemVO);
    }

    private <T> void setupCellValueFactory(JFXTreeTableColumn<CashItemVO, T> column, Function<CashItemVO, ObservableValue<T>> mapper) {
        column.setCellValueFactory((TreeTableColumn.CellDataFeatures<CashItemVO, T> param) -> {
            if (column.validateValue(param)) {
                return mapper.apply(param.getValue().getValue());
            } else {
                return column.getComputedValue(param);
            }
        });
    }

}
