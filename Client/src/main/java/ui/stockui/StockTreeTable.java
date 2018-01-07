package ui.stockui;

import blService.blServiceFactory.ServiceFactory_Stub;
import blService.stockblService.StockblService;
import com.jfoenix.controls.*;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.scene.control.*;
import javafx.util.Callback;

import ui.util.*;
import util.ReceiptState;
import vo.*;
import vo.receiptVO.StockReceiptListVO;

public class StockTreeTable extends ReceiptTreeTable<StockReceiptListVO> {

    //   private ObservableList<StockReceiptListVO> observableListfilter = observableList;
    //  private ObservableList<StockReceiptListVO> observableListtemp;
    private StockblService stockblService;
    private static StockSearchVO stockSearchVO;



    public StockTreeTable() {
        super();
        rowsPerPage = 7;
        stockblService = ServiceFactory_Stub.getService(StockblService.class.getName());
        JFXTreeTableColumn<StockReceiptListVO, Boolean> choose = new JFXTreeTableColumn("  ");
        choose.setPrefWidth(50);
        columnDecorator.setupCellValueFactory(choose, s -> s.selectedProperty().asObject());
        choose.setCellFactory(t -> new ChooseCell<StockReceiptListVO>(chosenItem));


        JFXTreeTableColumn<StockReceiptListVO, String> time = new JFXTreeTableColumn("Id");
        time.setPrefWidth(180);
        columnDecorator.setupCellValueFactory(time, s -> new ReadOnlyObjectWrapper(s.getId()));
        time.setCellFactory(t->new SearchableStringCell<>(keyword));

        JFXTreeTableColumn<StockReceiptListVO, String> member = new JFXTreeTableColumn("Member");
        member.setPrefWidth(85);
        columnDecorator.setupCellValueFactory(member, s -> new ReadOnlyObjectWrapper(s.getMemberName()));
        member.setCellFactory(t->new SearchableStringCell<>(keyword));

        JFXTreeTableColumn<StockReceiptListVO, String> state = new JFXTreeTableColumn("State");
        state.setPrefWidth(150);
        state.setCellFactory(new Callback<TreeTableColumn<StockReceiptListVO, String>, TreeTableCell<StockReceiptListVO, String>>() {
            @Override
            public TreeTableCell<StockReceiptListVO, String> call(TreeTableColumn<StockReceiptListVO, String> param) {
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

        JFXTreeTableColumn<StockReceiptListVO, Integer> sum = new JFXTreeTableColumn("Sum");
        sum.setPrefWidth(80);
        columnDecorator.setupCellValueFactory(sum, s -> new ReadOnlyObjectWrapper(s.getSum()));




        /**
        * more就是一列后面有三个点那个button
        **/
        JFXTreeTableColumn<StockReceiptListVO, Boolean> more = new JFXTreeTableColumn("");
        more.setPrefWidth(55);
        columnDecorator.setupCellValueFactory(more, s -> new ReadOnlyObjectWrapper(s.isMultiple()));
        more.setCellFactory(new Callback<TreeTableColumn<StockReceiptListVO, Boolean>, TreeTableCell<StockReceiptListVO, Boolean>>() {
            @Override
            public TreeTableCell<StockReceiptListVO, Boolean> call(TreeTableColumn<StockReceiptListVO, Boolean> param) {
                MultiCell multiCell = new MultiCell();
                multiCell.setRunnable1(()->{StockReceiptPane stockReceiptPane = new StockReceiptPane(((StockReceiptListVO)multiCell.getTreeTableRow().getTreeItem().getValue()).getId()); stockReceiptPane.refresh(true);});
                multiCell.setRunnable2(()->{stockblService.delete(((StockReceiptListVO)multiCell.getTreeTableRow().getTreeItem().getValue()).getId()); BoardController.getBoardController().refresh();});
                return multiCell;
            }
        });


        /**
         * 这里吧双击什么都做了，只要写跳转就行了
         **/

        this.setRowFactory(tableView -> {
            JFXTreeTableRow<StockReceiptListVO> row = new JFXTreeTableRow();
            RowSetter(row,()->{ StockReceiptPane stockReceiptPane = new StockReceiptPane(row.getTreeItem().getValue().getId()); stockReceiptPane.refresh(true);});
            return row;
        });
        this.getColumns().addAll(choose, time, member,sum, state, more);

    }


    public void delete(Pagination p) {
        System.out.println(chosenItem);
        System.out.println(observableList);

        chosenItem.getSet().forEach(s -> {observableList.remove(s); stockblService.delete(s.getId());});
        p.setPageCount(observableList.size() / getRowsPerPage() + 1);
        createPage(p.getCurrentPageIndex());
        p.setCurrentPageIndex(p.getCurrentPageIndex());
        chosenItem.getSet().clear();
    }

}
