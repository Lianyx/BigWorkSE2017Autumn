package ui.inventoryui.inventoryReceiptui;

import blService.blServiceFactory.ServiceFactory_Stub;
import blService.inventoryblService.InventoryblService;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableRow;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.scene.control.Pagination;
import javafx.scene.control.TreeTableCell;
import javafx.scene.control.TreeTableColumn;
import javafx.util.Callback;
import ui.util.*;
import util.ReceiptState;
import vo.inventoryVO.inventoryReceiptVO.InventoryReceiptListVO;
import vo.inventoryVO.inventoryReceiptVO.InventoryReceiptVO;
import vo.inventoryVO.inventoryReceiptVO.InventorySearchVO;

import java.rmi.RemoteException;

public class InventoryTreeTable extends ReceiptTreeTable<InventoryReceiptListVO> {
    private InventoryblService inventoryblService;
    private InventorySearchVO searchVO;

    public InventoryTreeTable() {
        super();
        rowsPerPage = 7;
        inventoryblService = ServiceFactory_Stub.getService(InventoryblService.class.getName());
        JFXTreeTableColumn<InventoryReceiptListVO, Boolean> choose = new JFXTreeTableColumn("  ");
        choose.setPrefWidth(50);
        columnDecorator.setupCellValueFactory(choose, s -> s.selectedProperty().asObject());
        choose.setCellFactory(t -> new ChooseCell<InventoryReceiptListVO>(chosenItem));

        JFXTreeTableColumn<InventoryReceiptListVO, String> time = new JFXTreeTableColumn("Id");
        time.setPrefWidth(180);
        columnDecorator.setupCellValueFactory(time, s -> new ReadOnlyObjectWrapper(s.getId()));
        time.setCellFactory(t->new SearchableStringCell<>(keyword));

        JFXTreeTableColumn<InventoryReceiptListVO, String> member = new JFXTreeTableColumn("Member");
        member.setPrefWidth(85);
        columnDecorator.setupCellValueFactory(member, s -> new ReadOnlyObjectWrapper(s.getMemberName()));
        member.setCellFactory(t->new SearchableStringCell<>(keyword));

        JFXTreeTableColumn<InventoryReceiptListVO, String> state = new JFXTreeTableColumn("State");
        state.setPrefWidth(150);
        state.setCellFactory(new Callback<TreeTableColumn<InventoryReceiptListVO, String>, TreeTableCell<InventoryReceiptListVO, String>>() {
            @Override
            public TreeTableCell<InventoryReceiptListVO, String> call(TreeTableColumn<InventoryReceiptListVO, String> param) {
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

        JFXTreeTableColumn<InventoryReceiptListVO, Boolean> more = new JFXTreeTableColumn("");
        more.setPrefWidth(55);
        columnDecorator.setupCellValueFactory(more, s -> new ReadOnlyObjectWrapper(s.isMultiple()));
        more.setCellFactory(new Callback<TreeTableColumn<InventoryReceiptListVO, Boolean>, TreeTableCell<InventoryReceiptListVO, Boolean>>() {
            @Override
            public TreeTableCell<InventoryReceiptListVO, Boolean> call(TreeTableColumn<InventoryReceiptListVO, Boolean> param) {
                MultiCell multiCell = new MultiCell();
                multiCell.setRunnable1(()->{InventoryReceiptPane inventoryReceiptPane = new InventoryReceiptPane(((InventoryReceiptListVO)multiCell.getTreeTableRow().getTreeItem().getValue()).getId()); inventoryReceiptPane.refresh(true);});
                multiCell.setRunnable2(()->{
                    try {
                        inventoryblService.delete(((InventoryReceiptVO)multiCell.getTreeTableRow().getTreeItem().getValue()));
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    BoardController.getBoardController().refresh();});
                return multiCell;

            }
        });

        this.setRowFactory(tableView -> {
            JFXTreeTableRow<InventoryReceiptListVO> row = new JFXTreeTableRow();
            RowSetter(row,()->{ InventoryReceiptPane inventoryReceiptPane = new InventoryReceiptPane(row.getTreeItem().getValue().getId()); inventoryReceiptPane.refresh(true);});
            return row;
        });
        this.getColumns().addAll(choose, time, member, state, more);
        
    }

    @Override
    public void delete(Pagination p) {
            System.out.println(chosenItem);
            System.out.println(observableList);

            chosenItem.getSet().forEach(s -> {observableList.remove(s);
            //inventoryblService.delete(s);  与bl的连接
                });
            p.setPageCount(observableList.size() / getRowsPerPage() + 1);
            createPage(p.getCurrentPageIndex());
            p.setCurrentPageIndex(p.getCurrentPageIndex());
            chosenItem.getSet().clear();

    }
}
