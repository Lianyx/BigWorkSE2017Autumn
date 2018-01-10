package ui.accountantui;

import blService.billblservice.PaymentBillReceiptblService;
import blService.blServiceFactory.ServiceFactory_Stub;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableRow;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.scene.control.Pagination;
import javafx.scene.control.TreeTableCell;
import javafx.scene.control.TreeTableColumn;
import javafx.util.Callback;
import ui.util.*;
import util.ReceiptState;
import vo.billReceiptVO.BillReceiptSearchVO;
import vo.billReceiptVO.PaymentReceiptListVO;
import vo.billReceiptVO.PaymentReceiptVO;
import vo.receiptVO.SalesReceiptListVO;

import java.rmi.RemoteException;

public class PaymentReceiptTreeTable extends ReceiptTreeTable<PaymentReceiptListVO>{

    private PaymentBillReceiptblService paymentBillReceiptblService;
    private static BillReceiptSearchVO billReceiptSearchVO;
    public PaymentReceiptTreeTable() {
        super();
        rowsPerPage = 7;
        paymentBillReceiptblService = ServiceFactory_Stub.getService(PaymentBillReceiptblService.class.getName());


        JFXTreeTableColumn<PaymentReceiptListVO, Boolean> choose = new JFXTreeTableColumn("  ");
        choose.setPrefWidth(50);
        columnDecorator.setupCellValueFactory(choose, s -> s.selectedProperty().asObject());
        choose.setCellFactory(t -> new ChooseCell<PaymentReceiptListVO>(chosenItem));


        JFXTreeTableColumn<PaymentReceiptListVO, String> id = new JFXTreeTableColumn("Id");
        id.setPrefWidth(180);
        columnDecorator.setupCellValueFactory(id, s -> new ReadOnlyObjectWrapper(s.getId()));
        id.setCellFactory(t->new SearchableStringCell<>(keyword));

        JFXTreeTableColumn<PaymentReceiptListVO, Integer> operator = new JFXTreeTableColumn("Operator");
        operator.setPrefWidth(80);
        columnDecorator.setupCellValueFactory(operator, s -> new ReadOnlyObjectWrapper(s.getOperator()));

        JFXTreeTableColumn<PaymentReceiptListVO, Double> sum = new JFXTreeTableColumn("Sum");
        sum.setPrefWidth(80);
        columnDecorator.setupCellValueFactory(sum, s -> new ReadOnlyObjectWrapper(s.getSum()));

        JFXTreeTableColumn<PaymentReceiptListVO, String> state = new JFXTreeTableColumn("State");
        state.setPrefWidth(150);
        state.setCellFactory(new Callback<TreeTableColumn<PaymentReceiptListVO, String>, TreeTableCell<PaymentReceiptListVO, String>>() {
            @Override
            public TreeTableCell<PaymentReceiptListVO, String> call(TreeTableColumn<PaymentReceiptListVO, String> param) {
                return new ButtonCell() {
                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if(item!=null){
                            setButtonStyle(ReceiptState.color.get(item));
                        }
                    }
                };
            }
        });
        columnDecorator.setupCellValueFactory(state, s -> new ReadOnlyObjectWrapper(s.getReceiptState().name()));

        JFXTreeTableColumn<PaymentReceiptListVO, Boolean> more = new JFXTreeTableColumn("");
        more.setPrefWidth(55);
        columnDecorator.setupCellValueFactory(more, s -> new ReadOnlyObjectWrapper(s.isMultiple()));
        more.setCellFactory(new Callback<TreeTableColumn<PaymentReceiptListVO, Boolean>, TreeTableCell<PaymentReceiptListVO, Boolean>>() {
            @Override
            public TreeTableCell<PaymentReceiptListVO, Boolean> call(TreeTableColumn<PaymentReceiptListVO, Boolean> param) {
                MultiCell multiCell = new MultiCell();
                multiCell.setRunnable1(()->{PaymentDetailPane paymentDetailPane = new PaymentDetailPane(((PaymentReceiptListVO)multiCell.getTreeTableRow().getTreeItem().getValue()).getId()); paymentDetailPane.refresh(true);});
//                multiCell.setRunnable2(()->{paymentBillReceiptblService.delete(((PaymentReceiptListVO)multiCell.getTreeTableRow().getTreeItem().getValue()).getId()); BoardController.getBoardController().refresh();});
                return multiCell;
            }
        });

        this.setRowFactory(tableView -> {
            JFXTreeTableRow<PaymentReceiptListVO> row = new JFXTreeTableRow();
            RowSetter(row,()->{ PaymentDetailPane paymentDetailPane = new PaymentDetailPane(row.getTreeItem().getValue().getId()); paymentDetailPane.refresh(true);});
            return row;
        });
        this.getColumns().addAll(choose,id,operator,sum,state,more);

    }


    public void delete(Pagination p){
        System.out.println(chosenItem);
        System.out.println(observableList);


        chosenItem.getSet().forEach(s -> {
            observableList.remove(s);
            /*try {
                paymentBillReceiptblService.delete(s);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }*/
        });
        p.setPageCount(observableList.size() / getRowsPerPage() + 1);
        createPage(p.getCurrentPageIndex());
        p.setCurrentPageIndex(p.getCurrentPageIndex());
        chosenItem.getSet().clear();
    }
}
