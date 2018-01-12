package ui.stockui;

import com.jfoenix.controls.*;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.StringProperty;

import ui.managerui.common.treeTableRelated.ChooseColumn;
import ui.managerui.common.treeTableRelated.MyTreeTableBorderPane;
import ui.managerui.common.treeTableRelated.SearchableStringColumn;
import ui.util.*;
import util.ReceiptState;
import vo.receiptVO.StockReceiptListVO;

import java.util.Set;

public abstract class StockTreeTable <T extends StockReceiptListVO<T>> extends MyTreeTableBorderPane<T> {



    public StockTreeTable(Set<T> chosenItems, StringProperty keywordProperty) {

        JFXTreeTableColumn<T, Boolean> choose = new ChooseColumn<>(chosenItems);
        JFXTreeTableColumn<T, String> id = new SearchableStringColumn<>("ID", 100, keywordProperty, p -> p.getId());
        JFXTreeTableColumn<T, String> member = new SearchableStringColumn<>("客户", 100, keywordProperty, p -> p.getMemberName());
        JFXTreeTableColumn<T, String> stateColumn = new JFXTreeTableColumn<>("类型");
        stateColumn.setPrefWidth(100);
        stateColumn.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue().getValue().getReceiptState().name()));
        stateColumn.setCellFactory(param -> new ButtonCell<T>() {
            @Override
            public void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (item != null) {
                    setButtonStyle(ReceiptState.color.get(item));
                }
            }
        });

        myTreeTable.getColumns().addAll(choose,id,member, stateColumn);
    }
/*
    //   private ObservableList<StockReceiptListVO> observableListfilter = observableList;
    //  private ObservableList<StockReceiptListVO> observableListtemp;
    private StockblService stockblService;



    public StockTreeTable() {
        super();
        rowsPerPage = 7;
        stockblService = ServiceFactory_Stub.getService(StockblService.class.getName());
        JFXTreeTableColumn<StockReceiptListVO, Boolean> choose = new JFXTreeTableColumn<StockReceiptListVO, Boolean>("  ");
        choose.setPrefWidth(50);
        columnDecorator.setupCellValueFactory(choose, s -> s.selectedProperty().asObject());
        choose.setCellFactory(t -> new ChooseCell<StockReceiptListVO>(chosenItem));


        JFXTreeTableColumn<StockReceiptListVO, String> time = new JFXTreeTableColumn<>("Id");
        time.setPrefWidth(180);
        columnDecorator.setupCellValueFactory(time, s -> new ReadOnlyObjectWrapper<>(s.getId()));
        time.setCellFactory(t->new SearchableStringCell<>(keyword));

        JFXTreeTableColumn<StockReceiptListVO, String> member = new JFXTreeTableColumn<>("Member");
        member.setPrefWidth(85);
        columnDecorator.setupCellValueFactory(member, s -> new ReadOnlyObjectWrapper<>(s.getMemberName()));
        member.setCellFactory(t->new SearchableStringCell<>(keyword));

        JFXTreeTableColumn<StockReceiptListVO, String> state = new JFXTreeTableColumn<>("State");
        state.setPrefWidth(150);
        state.setCellFactory(new Callback<TreeTableColumn<StockReceiptListVO, String>, TreeTableCell<StockReceiptListVO, String>>() {
            @Override
            public TreeTableCell<StockReceiptListVO, String> call(TreeTableColumn<StockReceiptListVO, String> param) {
                return new ButtonCell<StockReceiptListVO>() {
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
        columnDecorator.setupCellValueFactory(state, s -> new ReadOnlyObjectWrapper<>(s.getReceiptState().name()));

        JFXTreeTableColumn<StockReceiptListVO, Integer> sum = new JFXTreeTableColumn<>("Sum");
        sum.setPrefWidth(80);
        columnDecorator.setupCellValueFactory(sum, s -> new ReadOnlyObjectWrapper(s.getSum()));




        JFXTreeTableColumn<StockReceiptListVO, Boolean> more = new JFXTreeTableColumn<>("");
        more.setPrefWidth(55);
        columnDecorator.setupCellValueFactory(more, s -> new ReadOnlyObjectWrapper(s.isMultiple()));
        more.setCellFactory(new Callback<TreeTableColumn<StockReceiptListVO, Boolean>, TreeTableCell<StockReceiptListVO, Boolean>>() {
            @Override
            public TreeTableCell<StockReceiptListVO, Boolean> call(TreeTableColumn<StockReceiptListVO, Boolean> param) {
                MultiCell multiCell = new MultiCell();
                multiCell.setRunnable1(()->{StockReceiptPane stockReceiptPane = new StockReceiptPane(((StockReceiptListVO)multiCell.getTreeTableRow().getTreeItem().getValue()).getId()); stockReceiptPane.refresh(true);});
                multiCell.setRunnable2(()->{
                    try {
            //            stockblService.delete(((StockReceiptListVO) multiCell.getTreeTableRow().getTreeItem().getValue()).getId());
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    BoardController.getBoardController().refresh();});
                return multiCell;
            }
        });



        this.setRowFactory(tableView -> {
            JFXTreeTableRow<StockReceiptListVO> row = new JFXTreeTableRow<>();
            RowSetter(row,()->{ StockReceiptPane stockReceiptPane = new StockReceiptPane(row.getTreeItem().getValue().getId()); stockReceiptPane.refresh(true);});
            return row;
        });
        this.getColumns().addAll(choose, time, member,sum, state, more);

    }


    public void delete(Pagination p) {
        System.out.println(chosenItem);
        System.out.println(observableList);

        chosenItem.getSet().forEach(s -> {//observableList.remove();
        try {
        //    stockblService.delete(s.getId());
        }catch (Exception e){
            e.printStackTrace();
        }
        });
        p.setPageCount(observableList.size() / getRowsPerPage() + 1);
        createPage(p.getCurrentPageIndex());
        p.setCurrentPageIndex(p.getCurrentPageIndex());
        chosenItem.getSet().clear();
    }
*/
}
