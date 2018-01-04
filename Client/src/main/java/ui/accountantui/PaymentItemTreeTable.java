package ui.accountantui;

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
import vo.billReceiptVO.TransferItemVO;

import java.util.List;
import java.util.Set;
import java.util.function.Function;

public class PaymentItemTreeTable extends JFXTreeTableView<TransferItemVO>{

    private ObservableList<TransferItemVO> observableList = FXCollections.observableArrayList();

    //StockblService stockblService;
    PaymentBillReceiptblService paymentBillReceiptblService;
    BoardController boardController;
    StackPane mainpane;

    public PaymentItemTreeTable(){
        super();

        //private int accountID;
        //private double sum;
        //private String commnet;

        JFXTreeTableColumn<TransferItemVO,Integer> accountID = new JFXTreeTableColumn<>("accountID");
        accountID.setPrefWidth(97);
        setupCellValueFactory(accountID,l->l.accountIDProperty().asObject());

        JFXTreeTableColumn<TransferItemVO, Double> price = new JFXTreeTableColumn<>("Price");
        price.setPrefWidth(97);
        setupCellValueFactory(price,l->l.sumProperty().asObject());


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


        JFXTreeTableColumn<TransferItemVO, String> comment = new JFXTreeTableColumn<>("Comment");
        comment.setPrefWidth(96);
        setupCellValueFactory(comment,TransferItemVO::commentProperty);


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


    public void setPaymentBillReceiptblService(PaymentBillReceiptblService paymentBillReceiptblService) {
        this.paymentBillReceiptblService = paymentBillReceiptblService;
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

    public void addGood(TransferItemVO transferItemVO){
        observableList.add(transferItemVO);
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

}
