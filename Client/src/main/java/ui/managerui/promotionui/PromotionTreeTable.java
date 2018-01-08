package ui.managerui.promotionui;

import com.jfoenix.controls.*;
import com.jfoenix.controls.cells.editors.base.JFXTreeTableCell;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.util.Duration;
import ui.managerui.common.treeTableRelated.ChooseColumn;
import ui.managerui.common.treeTableRelated.SearchableStringColumn;
import ui.managerui.promotionui.promotionDetailPane.PromotionDetailPane;
import ui.util.NodeAnimation;
import ui.util.NodeHolder;
import util.PromotionState;
import vo.promotionVO.PromotionVO;
import ui.managerui.common.treeTableRelated.StateCell;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.*;
import java.util.function.Function;

public class PromotionTreeTable extends JFXTreeTableView<PromotionVO> {
    private ObservableList<PromotionVO> observableList;
    private ObservableList<PromotionVO> tempList;
    private Pagination pagination;
    private int rowsPerPage = 7;
    private Set<PromotionVO> chosenItems;

    // 传boarderPane进来总是感觉不好的，但是唉权宜之计…改少一点吧，应该promotionTreeTable就是一个borderPane
    public PromotionTreeTable(Set<PromotionVO> chosenItems, List<PromotionVO> keywordFilterList, StringProperty keywordProperty, BorderPane borderPane) {
//        this.observableList = FXCollections.observableArrayList(list);
        this.chosenItems = chosenItems;

        JFXTreeTableColumn<PromotionVO, Boolean> choose = new ChooseColumn<>(chosenItems);
        JFXTreeTableColumn<PromotionVO, String> idColumn = new SearchableStringColumn<>("编号", 200, keywordProperty, PromotionVO::getId);
        JFXTreeTableColumn<PromotionVO, String> beginTimeColumn = new SearchableStringColumn<>("开始时间", 100, keywordProperty, p -> p.getBeginTime().toLocalDate().toString());
        JFXTreeTableColumn<PromotionVO, String> endTimeColumn = new SearchableStringColumn<>("结束时间", 100, keywordProperty, p -> p.getEndTime().toLocalDate().toString());
        JFXTreeTableColumn<PromotionVO, String> commentColumn = new SearchableStringColumn<>("备注", 100, keywordProperty, PromotionVO::getComment);

        JFXTreeTableColumn<PromotionVO, PromotionState> stateColumn = new JFXTreeTableColumn<>("状态");
        stateColumn.setPrefWidth(100);
        stateColumn.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue().getValue().getPromotionState()));
        stateColumn.setCellFactory(param -> new StateCell<>(ps -> ps.chineseName, ps -> ps.buttonStyle));


        setRowFactory(tableView -> {
            JFXTreeTableRow<PromotionVO> row = new JFXTreeTableRow<>();
            row.setPrefHeight(55);
            row.setStyle("-fx-border-color: rgb(233,237,239); -fx-border-width: 0.3;");
            row.setOnMouseClicked(e -> {
                if (e.getClickCount() == 2) {
                    PromotionDetailPane promotionDetailPane = row.getTreeItem().getValue().getDetailPane();
                    promotionDetailPane.refresh(true);
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

        setShowRoot(false);
        getColumns().addAll(choose, idColumn, beginTimeColumn, /*endTimeColumn,*/ commentColumn, stateColumn);

        setPrefSize(600, 450);

//        observableList.addListener((ListChangeListener.Change<? extends PromotionVO> change) -> { // 这里有个坑，必须写明类型
//            createPage(pagination.getCurrentPageIndex());
//            pagination.setPageCount(getListSize() / getRowsPerPage() + 1);
//        });

        pagination = new Pagination();
        pagination.currentPageIndexProperty().addListener(((observable, oldValue, newValue) -> {
            createPage(newValue.intValue());
        }));
        borderPane.setBottom(pagination);
        borderPane.setCenter(this);

    }

    private void createPage(int pageIndex) {
        int fromIndex = pageIndex * rowsPerPage;
        int toIndex = Math.min(fromIndex + rowsPerPage, observableList.size());
        tempList = (FXCollections.observableArrayList(observableList.subList(fromIndex, toIndex)));
        TreeItem<PromotionVO> root = new RecursiveTreeItem<>(tempList, RecursiveTreeObject::getChildren);
        this.setRoot(root);

        new NodeHolder(this, Duration.millis(1000), NodeAnimation.FADE).apply();
    }

    public void refresh(List<PromotionVO> list) {
        this.observableList = FXCollections.observableArrayList(list);

        int maxPageIndex = (observableList.size() - 1) / rowsPerPage + 1;
        pagination.setPageCount(maxPageIndex); // 就算是0好像也不报错的

        int currentPageIndex = pagination.getCurrentPageIndex();
        int newPageIndex = 0;
        if (currentPageIndex - 1 >= 0 && currentPageIndex < maxPageIndex) { // 这里pageIndex在userListPane逻辑有点没看懂…就自己瞎改了
            newPageIndex = currentPageIndex;
        }
        if (newPageIndex == currentPageIndex) {
            // 这里写的很丑。
            // 因为：如果newPageIndex和原来一样的，只改pagination就不会触发createPage；如果不一样（只有0的情况才不一样吧），还要改pagination
            // 要么就是继createPage又改pagination，感觉有可能会两次create。
            createPage(newPageIndex);
        } else {
            pagination.setCurrentPageIndex(newPageIndex);
        }
    }
}