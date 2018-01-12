package ui.accountantui;

import blService.accountblService.AccountblService;
import businesslogic.accountbl.Accountbl;
import com.jfoenix.controls.*;
import com.jfoenix.controls.cells.editors.base.JFXTreeTableCell;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleStringProperty;
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
import java.util.Set;

public class AccountTreeTable extends  JFXTreeTableView<AccountListVO>{

    ObservableList<AccountListVO> observableList = FXCollections.observableArrayList();

    int rowsPerPage;
    BoardController boardController;
    StackPane mainpane;
    ChosenItem<AccountListVO> chosenItem = new ChosenItem<>();
    ColumnDecorator columnDecorator = new ColumnDecorator();
    SimpleStringProperty keyword = new SimpleStringProperty("");

    private AccountblService accountblService;

    public ObservableList<AccountListVO> getObservableList() {
        return observableList;
    }

    public int getRowsPerPage() {
        return rowsPerPage;
    }


    public AccountTreeTable() {
        super();
        this.boardController = BoardController.getBoardController();
        this.mainpane = PaneFactory.getMainPane();
        setCurrentItemsCount(rowsPerPage);
        this.setShowRoot(false);

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

        JFXTreeTableColumn<AccountListVO,Double> balance = new JFXTreeTableColumn<>("余额");
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
                    DoubleButtonDialog doubleButtonDialog = new DoubleButtonDialog(mainpane,"","请确认是否删除","是","否");
                    doubleButtonDialog.setButtonOne(()->{
                        try{
                            accountblService.delete(((AccountListVO)multiCell.getTreeTableRow().getTreeItem().getValue()).getID());
                            BoardController.getBoardController().refresh();
                        }catch (RemoteException e){
                            e.printStackTrace();
                        }
                    });
                    doubleButtonDialog.setButtonTwo(()->{});
                    doubleButtonDialog.show();
                });
                return multiCell;
            }
        });


        this.setRowFactory(tableView-> {
            JFXTreeTableRow<AccountListVO> row = new JFXTreeTableRow();
            RowSetter(row,()->{
                AccountDetailPane accountDetailPane = new AccountDetailPane( row.getItem());
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


    public void setReceipts(Set<AccountListVO> receipts) {
        observableList.setAll(receipts);
    }

    public void removeReceipt(AccountListVO t) {
        observableList.remove(t);
    }

    public void createPage(int pageIndex) {
        int fromIndex = pageIndex * rowsPerPage;
        int toIndex = Math.min(fromIndex + rowsPerPage, observableList.size());
        final TreeItem<AccountListVO> root = new RecursiveTreeItem<>(FXCollections.observableList(observableList.subList(fromIndex, toIndex)), RecursiveTreeObject::getChildren);
        this.setRoot(root);
        NodeHolder nodeHolder = new NodeHolder(this, Duration.millis(1000), NodeAnimation.FADE);
        nodeHolder.apply();

    }

    public void RowSetter(JFXTreeTableRow<AccountListVO> row,Runnable click){
        row.setPrefHeight(55);
        row.setStyle("-fx-border-color: rgb(233,237,239); -fx-border-width: 0.3;");
        row.setOnMouseClicked((MouseEvent event) -> {
            if (event.getClickCount() == 2) {
                Platform.runLater(click);
            }

        });
        row.selectedProperty().addListener(e -> {
            if (row.isSelected()) {
                row.toFront();
            } else {
                row.setEffect(null);
            }
        });
    }

    public String getKeyword() {
        return keyword.get();
    }

    public SimpleStringProperty keywordProperty() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword.set(keyword);
    }

}
