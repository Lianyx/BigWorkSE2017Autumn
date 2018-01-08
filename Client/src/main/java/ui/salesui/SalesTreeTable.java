package ui.salesui;

import blService.blServiceFactory.ServiceFactory_Stub;
import blService.salesblService.SalesblService;
import blService.stockblService.StockblService;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableRow;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.scene.control.Pagination;
import javafx.scene.control.TreeTableCell;
import javafx.scene.control.TreeTableColumn;
import javafx.util.Callback;
import ui.stockui.StockReceiptPane;
import ui.util.*;
import util.ReceiptState;
import vo.StockSearchVO;
import vo.receiptVO.SalesReceiptListVO;


public class SalesTreeTable extends ReceiptTreeTable<SalesReceiptListVO> {

    //   private ObservableList<SalesReceiptListVO> observableListfilter = observableList;
    //  private ObservableList<SalesReceiptListVO> observableListtemp;
    private SalesblService salesblService;
    private static StockSearchVO stockSearchVO;
    public SalesTreeTable() {
        super();
        rowsPerPage = 7;
        salesblService = ServiceFactory_Stub.getService(SalesblService.class.getName());
        JFXTreeTableColumn<SalesReceiptListVO, Boolean> choose = new JFXTreeTableColumn("  ");
        choose.setPrefWidth(50);
        columnDecorator.setupCellValueFactory(choose, s -> s.selectedProperty().asObject());
        choose.setCellFactory(t -> new ChooseCell<SalesReceiptListVO>(chosenItem));


        JFXTreeTableColumn<SalesReceiptListVO, String> time = new JFXTreeTableColumn("Id");
        time.setPrefWidth(180);
        columnDecorator.setupCellValueFactory(time, s -> new ReadOnlyObjectWrapper(s.getId()));
        time.setCellFactory(t->new SearchableStringCell<>(keyword));

        JFXTreeTableColumn<SalesReceiptListVO, String> member = new JFXTreeTableColumn("Member");
        member.setPrefWidth(85);
        columnDecorator.setupCellValueFactory(member, s -> new ReadOnlyObjectWrapper(s.getMemberName()));
        member.setCellFactory(t->new SearchableStringCell<>(keyword));

        JFXTreeTableColumn<SalesReceiptListVO, String> state = new JFXTreeTableColumn("State");
        state.setPrefWidth(150);
        state.setCellFactory(new Callback<TreeTableColumn<SalesReceiptListVO, String>, TreeTableCell<SalesReceiptListVO, String>>() {
            @Override
            public TreeTableCell<SalesReceiptListVO, String> call(TreeTableColumn<SalesReceiptListVO, String> param) {
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

        JFXTreeTableColumn<SalesReceiptListVO, Integer> sum = new JFXTreeTableColumn("Sum");
        sum.setPrefWidth(80);
        columnDecorator.setupCellValueFactory(sum, s -> new ReadOnlyObjectWrapper(s.getSum()));

        JFXTreeTableColumn<SalesReceiptListVO, Boolean> more = new JFXTreeTableColumn("");
        more.setPrefWidth(55);
        columnDecorator.setupCellValueFactory(more, s -> new ReadOnlyObjectWrapper(s.isMultiple()));
        more.setCellFactory(new Callback<TreeTableColumn<SalesReceiptListVO, Boolean>, TreeTableCell<SalesReceiptListVO, Boolean>>() {
            @Override
            public TreeTableCell<SalesReceiptListVO, Boolean> call(TreeTableColumn<SalesReceiptListVO, Boolean> param) {
                MultiCell multiCell = new MultiCell();
//                multiCell.setRunnable1(()->{SalesReceiptPane salesReceiptPane = new SalesReceiptPane(((SalesReceiptListVO)multiCell.getTreeTableRow().getTreeItem().getValue()).getId()); salesReceiptPane.refresh(true);});
                multiCell.setRunnable2(()->{salesblService.delete(((SalesReceiptListVO)multiCell.getTreeTableRow().getTreeItem().getValue()).getId()); BoardController.getBoardController().refresh();});
                return multiCell;
            }
        });

        this.setRowFactory(tableView -> {
            JFXTreeTableRow<SalesReceiptListVO> row = new JFXTreeTableRow();
//            RowSetter(row,()->{ SalesReceiptPane salesReceiptPane = new SalesReceiptPane(row.getTreeItem().getValue().getId()); salesReceiptPane.refresh(true);});
            return row;
        });


        this.getColumns().addAll(choose, time, member,sum, state, more);

    }



    public void delete(Pagination p) {
        System.out.println(chosenItem);
        System.out.println(observableList);

        chosenItem.getSet().forEach(s -> {observableList.remove(s); salesblService.delete(s.getId());});
        p.setPageCount(observableList.size() / getRowsPerPage() + 1);
        createPage(p.getCurrentPageIndex());
        p.setCurrentPageIndex(p.getCurrentPageIndex());
        chosenItem.getSet().clear();
    }

}
