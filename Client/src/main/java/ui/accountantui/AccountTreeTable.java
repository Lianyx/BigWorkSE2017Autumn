package ui.accountantui;

import blService.accountblService.AccountblService;
import businesslogic.accountbl.Accountbl;
import com.jfoenix.controls.*;
import com.jfoenix.controls.cells.editors.base.JFXTreeTableCell;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.util.Callback;
import javafx.util.Duration;
import ui.memberui.MemberDetailPane;
import ui.util.*;
import vo.AccountListVO;
import vo.MemberListVO;

import java.rmi.RemoteException;
import java.util.List;

public class AccountTreeTable extends ReceiptTreeTable<AccountListVO>{

    private AccountblService accountblService;

    public ObservableList<AccountListVO> getObservableList() {
        return observableList;
    }

    public int getRowsPerPage() {
        return rowsPerPage;
    }


    public AccountTreeTable() {
        super();

        rowsPerPage = 7;
        try {
            this.accountblService = new Accountbl();

        }catch (Exception e){
            e.printStackTrace();
        }

        JFXTreeTableColumn<AccountListVO,Boolean> choose = new JFXTreeTableColumn("  ");
        choose.setPrefWidth(40);
        columnDecorator.setupCellValueFactory(choose, s -> s.selectedProperty().asObject());
        choose.setCellFactory(t->new ChooseCell<AccountListVO>(chosenItem));

        JFXTreeTableColumn<AccountListVO,Integer> id = new JFXTreeTableColumn("编号");
        id.setPrefWidth(150);
        columnDecorator.setupCellValueFactory(id,s->new ReadOnlyObjectWrapper<>(s.getID()));

        JFXTreeTableColumn<AccountListVO,String> name = new JFXTreeTableColumn<>("姓名");
        name.setPrefWidth(150);
        columnDecorator.setupCellValueFactory(name,s->new ReadOnlyObjectWrapper<>(s.getName()));
        name.setCellFactory(t->new SearchableStringCell<>(keyword));

        JFXTreeTableColumn<AccountListVO,Double> balance = new JFXTreeTableColumn<>("Balance");
        balance.setPrefWidth(150);
        columnDecorator.setupCellValueFactory(balance,s->new ReadOnlyObjectWrapper<>(s.getBalance()));

        JFXTreeTableColumn<AccountListVO, Boolean> more = new JFXTreeTableColumn("");
        more.setPrefWidth(100);
        columnDecorator.setupCellValueFactory(more, s -> new ReadOnlyObjectWrapper(s.isMultiple()));
        more.setCellFactory(new Callback<TreeTableColumn<AccountListVO, Boolean>, TreeTableCell<AccountListVO, Boolean>>() {
            @Override
            public TreeTableCell<AccountListVO, Boolean> call(TreeTableColumn<AccountListVO, Boolean> param) {
                MultiCell multiCell = new MultiCell();
                multiCell.setRunnable1(()->{
                    AccountDetailPane accountDetailPane = new AccountDetailPane(((AccountListVO)multiCell.getTreeTableRow().getTreeItem().getValue()));
                    accountDetailPane.refresh(true);
                });
                multiCell.setRunnable2(()->{
                    try{
                        accountblService.delete(((AccountListVO)multiCell.getTreeTableRow().getTreeItem().getValue()).getID());
                        BoardController.getBoardController().refresh();
                    }catch (RemoteException e){
                        e.printStackTrace();
                    }
                });
                return multiCell;
            }
        });


        this.setRowFactory(tableView-> {
            JFXTreeTableRow<AccountListVO> row = new JFXTreeTableRow();
            RowSetter(row,()->{
                AccountDetailPane accountDetailPane = new AccountDetailPane((AccountListVO) row.getItem());
                accountDetailPane.refresh(true);
            });
            return row;
        });

        this.getColumns().addAll(choose,id,name,balance,more);
    }

    public void setAccount(List<AccountListVO> list){
        observableList.setAll(list);
    }

    public void removeAccount(AccountListVO accountListVO){
        observableList.remove(accountListVO);
    }

    @Override
    public void delete(Pagination p) {
        try{
            chosenItem.getSet().forEach(s -> {
                observableList.remove(s);
                try {
                    accountblService.delete(s.getID());
                }catch (RemoteException e){
                    e.printStackTrace();
                }
            });
            p.setPageCount(observableList.size() / getRowsPerPage() + 1);
            createPage(p.getCurrentPageIndex());
            p.setCurrentPageIndex(p.getCurrentPageIndex());
            chosenItem.getSet().clear();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
