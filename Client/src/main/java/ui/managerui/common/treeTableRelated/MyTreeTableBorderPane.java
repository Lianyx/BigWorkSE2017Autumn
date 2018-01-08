package ui.managerui.common.treeTableRelated;

import com.jfoenix.controls.JFXTreeTableRow;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Pagination;
import javafx.scene.control.TreeItem;
import javafx.scene.layout.BorderPane;
import javafx.util.Duration;
import ui.util.NodeAnimation;
import ui.util.NodeHolder;
import vo.receiptVO.ReceiptVO;

import java.util.List;

public abstract class MyTreeTableBorderPane<T extends RecursiveTreeObject<T>> extends BorderPane {
    protected ObservableList<T> observableList;
    protected int rowsPerPage = 7;

    protected JFXTreeTableView<T> myTreeTable = new JFXTreeTableView<>();
    protected Pagination pagination = new Pagination();

    public MyTreeTableBorderPane() {
        myTreeTable.setRowFactory(tableView -> {
            JFXTreeTableRow<T> row = new JFXTreeTableRow<>();
            row.setPrefHeight(55);
            row.setStyle("-fx-border-color: rgb(233,237,239); -fx-border-width: 0.3;");
            row.setOnMouseClicked(e -> {
                if (e.getClickCount() == 2) {
                    clickTwiceAftermath(row);
                }
            });
            row.selectedProperty().addListener(e -> {
                if (row.isSelected()) {
                    row.toFront();
                } else {
                    row.setEffect(null);
                }
            });

            return row;
        });

        pagination.currentPageIndexProperty().addListener(((observable, oldValue, newValue) -> {
            createPage(newValue.intValue());
        }));

        setPrefSize(600, 480);
        setBottom(pagination);
        setCenter(myTreeTable);

        myTreeTable.setPrefSize(600, 450);
        myTreeTable.setShowRoot(false);
    }

    protected abstract void clickTwiceAftermath(JFXTreeTableRow<T> row);

    protected void createPage(int pageIndex) {
        int fromIndex = pageIndex * rowsPerPage;
        int toIndex = Math.min(fromIndex + rowsPerPage, observableList.size());
        ObservableList<T> tempList = (FXCollections.observableArrayList(observableList.subList(fromIndex, toIndex)));
        TreeItem<T> root = new RecursiveTreeItem<>(tempList, RecursiveTreeObject::getChildren);
        myTreeTable.setRoot(root);

        new NodeHolder(this, Duration.millis(1000), NodeAnimation.FADE).apply();
    }

    public void refresh(List<T> list) {
        this.observableList = FXCollections.observableArrayList(list);

        int maxPageIndex = (observableList.size() - 1) / rowsPerPage + 1;
        pagination.setPageCount(maxPageIndex);

        int currentPageIndex = pagination.getCurrentPageIndex();
        int newPageIndex = 0;
        if (currentPageIndex - 1 >= 0 && currentPageIndex < maxPageIndex) {
            newPageIndex = currentPageIndex;
        }
        if (newPageIndex == currentPageIndex) {
            createPage(newPageIndex);
        } else {
            pagination.setCurrentPageIndex(newPageIndex);
        }
    }
}