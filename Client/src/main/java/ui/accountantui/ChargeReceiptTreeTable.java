package ui.accountantui;

import blService.billblservice.ChargeBillReceiptblService;
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
import vo.billReceiptVO.ChargeReceiptListVO;
import vo.billReceiptVO.PaymentReceiptListVO;

/*public class ChargeReceiptTreeTable extends ReceiptTreeTable<ChargeReceiptListVO> {

    private ChargeBillReceiptblService chargeBillReceiptblService;
    private static BillReceiptSearchVO billReceiptSearchVO;
    public ChargeReceiptTreeTable() {
        super();
        rowsPerPage = 7;
        chargeBillReceiptblService = ServiceFactory_Stub.getService(ChargeBillReceiptblService.class.getName());

        JFXTreeTableColumn<ChargeReceiptListVO, Boolean> choose = new JFXTreeTableColumn("  ");
        choose.setPrefWidth(50);
        columnDecorator.setupCellValueFactory(choose, s -> s.selectedProperty().asObject());
        choose.setCellFactory(t -> new ChooseCell<ChargeReceiptListVO>(chosenItem));


        JFXTreeTableColumn<ChargeReceiptListVO, String> id = new JFXTreeTableColumn("Id");
        id.setPrefWidth(180);
        columnDecorator.setupCellValueFactory(id, s -> new ReadOnlyObjectWrapper(s.getId()));
        id.setCellFactory(t->new SearchableStringCell<>(keyword));

        JFXTreeTableColumn<ChargeReceiptListVO, Integer> operator = new JFXTreeTableColumn("Operator");
        operator.setPrefWidth(80);
        columnDecorator.setupCellValueFactory(operator, s -> new ReadOnlyObjectWrapper(s.getOperator()));

        JFXTreeTableColumn<ChargeReceiptListVO, Double> sum = new JFXTreeTableColumn("Sum");
        sum.setPrefWidth(80);
        columnDecorator.setupCellValueFactory(sum, s -> new ReadOnlyObjectWrapper(s.getSum()));

        JFXTreeTableColumn<ChargeReceiptListVO, String> state = new JFXTreeTableColumn("State");
        state.setPrefWidth(150);
        state.setCellFactory(new Callback<TreeTableColumn<ChargeReceiptListVO, String>, TreeTableCell<ChargeReceiptListVO, String>>() {
            @Override
            public TreeTableCell<ChargeReceiptListVO, String> call(TreeTableColumn<ChargeReceiptListVO, String> param) {
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

        JFXTreeTableColumn<ChargeReceiptListVO, Boolean> more = new JFXTreeTableColumn("");
        more.setPrefWidth(55);
        columnDecorator.setupCellValueFactory(more, s -> new ReadOnlyObjectWrapper(s.isMultiple()));
        more.setCellFactory(new Callback<TreeTableColumn<ChargeReceiptListVO, Boolean>, TreeTableCell<ChargeReceiptListVO, Boolean>>() {
            @Override
            public TreeTableCell<ChargeReceiptListVO, Boolean> call(TreeTableColumn<ChargeReceiptListVO, Boolean> param) {
                MultiCell multiCell = new MultiCell();
                multiCell.setRunnable1(()->{ChargeDetailPane chargeDetailPane = new ChargeDetailPane(((ChargeReceiptListVO)multiCell.getTreeTableRow().getTreeItem().getValue()).getId()); chargeDetailPane.refresh(true);});
                multiCell.setRunnable2(()->{chargeBillReceiptblService.delete(((ChargeReceiptListVO)multiCell.getTreeTableRow().getTreeItem().getValue()).getId()); BoardController.getBoardController().refresh();});
                return multiCell;
            }
        });

        this.setRowFactory(tableView -> {
            JFXTreeTableRow<ChargeReceiptListVO> row = new JFXTreeTableRow();
            RowSetter(row,()->{ ChargeDetailPane chargeDetailPane = new ChargeDetailPane(row.getTreeItem().getValue().getId()); chargeDetailPane.refresh(true);});
            return row;
        });
        this.getColumns().addAll(choose,id,operator,sum,state,more);

    }


    public void delete(Pagination p){
        System.out.println(chosenItem);
        System.out.println(observableList);


        chosenItem.getSet().forEach(s -> {
            observableList.remove(s);*/
            /*try {
                chargeBillReceiptblService.delete(s);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }*/
       /* });
        p.setPageCount(observableList.size() / getRowsPerPage() + 1);
        createPage(p.getCurrentPageIndex());
        p.setCurrentPageIndex(p.getCurrentPageIndex());
        chosenItem.getSet().clear();
    }


}*/
