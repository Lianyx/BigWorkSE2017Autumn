package ui.accountantui;

import blService.billblservice.CashBillReceiptblService;
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
import vo.billReceiptVO.CashReceiptListVO;
import vo.billReceiptVO.PaymentReceiptListVO;

public class CashReceiptTreeTable extends ReceiptTreeTable<CashReceiptListVO>{

    private CashBillReceiptblService cashBillReceiptblService;
    private static BillReceiptSearchVO billReceiptSearchVO;
    public CashReceiptTreeTable() {
        super();
        rowsPerPage = 7;
        cashBillReceiptblService = ServiceFactory_Stub.getService(CashBillReceiptblService.class.getName());


        JFXTreeTableColumn<CashReceiptListVO, Boolean> choose = new JFXTreeTableColumn("  ");
        choose.setPrefWidth(50);
        columnDecorator.setupCellValueFactory(choose, s -> s.selectedProperty().asObject());
        choose.setCellFactory(t -> new ChooseCell<CashReceiptListVO>(chosenItem));


        JFXTreeTableColumn<CashReceiptListVO, String> id = new JFXTreeTableColumn("Id");
        id.setPrefWidth(180);
        columnDecorator.setupCellValueFactory(id, s -> new ReadOnlyObjectWrapper(s.getId()));
        id.setCellFactory(t->new SearchableStringCell<>(keyword));

        JFXTreeTableColumn<CashReceiptListVO, Integer> operator = new JFXTreeTableColumn("Operator");
        operator.setPrefWidth(80);
        columnDecorator.setupCellValueFactory(operator, s -> new ReadOnlyObjectWrapper(s.getOperator()));

        JFXTreeTableColumn<CashReceiptListVO, Double> sum = new JFXTreeTableColumn("Sum");
        sum.setPrefWidth(80);
        columnDecorator.setupCellValueFactory(sum, s -> new ReadOnlyObjectWrapper(s.getSum()));

        JFXTreeTableColumn<CashReceiptListVO, String> state = new JFXTreeTableColumn("State");
        state.setPrefWidth(150);
        state.setCellFactory(new Callback<TreeTableColumn<CashReceiptListVO, String>, TreeTableCell<CashReceiptListVO, String>>() {
            @Override
            public TreeTableCell<CashReceiptListVO, String> call(TreeTableColumn<CashReceiptListVO, String> param) {
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

        JFXTreeTableColumn<CashReceiptListVO, Boolean> more = new JFXTreeTableColumn("");
        more.setPrefWidth(55);
        columnDecorator.setupCellValueFactory(more, s -> new ReadOnlyObjectWrapper(s.isMultiple()));
        more.setCellFactory(new Callback<TreeTableColumn<CashReceiptListVO, Boolean>, TreeTableCell<CashReceiptListVO, Boolean>>() {
            @Override
            public TreeTableCell<CashReceiptListVO, Boolean> call(TreeTableColumn<CashReceiptListVO, Boolean> param) {
                MultiCell multiCell = new MultiCell();
                multiCell.setRunnable1(()->{CashDetailPane cashDetailPane = new CashDetailPane(((CashReceiptListVO)multiCell.getTreeTableRow().getTreeItem().getValue()).getId()); cashDetailPane.refresh(true);});
                multiCell.setRunnable2(()->{cashBillReceiptblService.delete(((CashReceiptListVO)multiCell.getTreeTableRow().getTreeItem().getValue()).getId()); BoardController.getBoardController().refresh();});
                return multiCell;
            }
        });

        this.setRowFactory(tableView -> {
            JFXTreeTableRow<CashReceiptListVO> row = new JFXTreeTableRow();
            RowSetter(row,()->{ CashDetailPane cashDetailPane = new CashDetailPane(row.getTreeItem().getValue().getId()); cashDetailPane.refresh(true);});
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
                cashBillReceiptblService.delete(s);
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
