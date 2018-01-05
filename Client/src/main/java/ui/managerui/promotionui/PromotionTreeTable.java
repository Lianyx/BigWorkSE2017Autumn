package ui.managerui.promotionui;

import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import ui.managerui.common.ChooseBoxTTCell;
import ui.managerui.common.SearchableStringCell;
import ui.userui.usermanagerui.BoardController;
import vo.promotionVO.PromotionVO;

import java.util.*;

public class PromotionTreeTable extends JFXTreeTableView<PromotionVO> {
    private StackPane mainpane;

    private ObservableList<PromotionVO> observableList;
    private ObservableList<PromotionVO> tempList;
    private BoardController boardController;
    private Pagination pagination;
    private int rowsPerPage = 7;
    private Set<PromotionVO> chosenItems;
    private StringProperty keyWordProperty = new SimpleStringProperty("1");

    public PromotionTreeTable(List<PromotionVO> list, BoardController boardController, StackPane mainpane) {
        this.observableList = FXCollections.observableArrayList(list);
        this.boardController = boardController;
        this.mainpane = mainpane;
        this.chosenItems = new HashSet<>();

        JFXTreeTableColumn<PromotionVO, Boolean> choose = new JFXTreeTableColumn<>("  ");
        choose.setPrefWidth(40);
        choose.setCellValueFactory(param -> param.getValue().getValue().selectedProperty());
        choose.setCellFactory(p -> new ChooseBoxTTCell<>(chosenItems));

        JFXTreeTableColumn<PromotionVO, String> idColumn = new JFXTreeTableColumn<>("编号");
        idColumn.setPrefWidth(200);
        idColumn.setCellValueFactory((TreeTableColumn.CellDataFeatures<PromotionVO, String> param) -> new ReadOnlyStringWrapper(param.getValue().getValue().getId()));
        idColumn.setCellFactory(p -> new SearchableStringCell<>(keyWordProperty));

        JFXTreeTableColumn<PromotionVO, String> beginTimeColumn = new JFXTreeTableColumn<>("开始时间");
        beginTimeColumn.setPrefWidth(100);
        beginTimeColumn.setCellValueFactory((p) -> new ReadOnlyStringWrapper(p.getValue().getValue().getBeginTime().toString()));

        JFXTreeTableColumn<PromotionVO, String> endTimeColumn = new JFXTreeTableColumn<>("结束时间");
        endTimeColumn.setPrefWidth(100);
        endTimeColumn.setCellValueFactory((p) -> new ReadOnlyStringWrapper(p.getValue().getValue().getBeginTime().toString()));

        JFXTreeTableColumn<PromotionVO, String> commentColumn = new JFXTreeTableColumn<>("备注");
        commentColumn.setPrefWidth(100);
        commentColumn.setCellValueFactory(p -> new ReadOnlyStringWrapper(p.getValue().getValue().getComment()));

        setRowFactory(tableView -> {
            JFXTreeTableRow<PromotionVO> row = new JFXTreeTableRow<>();
            row.setPrefHeight(55);
            row.setStyle("-fx-border-color: rgb(233,237,239); -fx-border-width: 0.3;");
            row.setOnMouseClicked(e -> {
                if (e.getClickCount() == 2) {
                    PromotionDetailPane promotionDetailPane = row.getTreeItem().getValue().getDetailPane();
                    promotionDetailPane.setMainpane(mainpane);
                    promotionDetailPane.setBoardController(boardController);
                    boardController.switchTo(promotionDetailPane);
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
        getColumns().addAll(choose, idColumn, beginTimeColumn, endTimeColumn, commentColumn);

//        observableList.addListener((ListChangeListener.Change<? extends PromotionVO> change) -> { // 这里有个坑，必须写明类型
//            createPage(pagination.getCurrentPageIndex());
//            pagination.setPageCount(getListSize() / getRowsPerPage() + 1);
//        });

        int pageIndex = 0;
        int fromIndex = pageIndex * rowsPerPage;
        int toIndex = Math.min(fromIndex + rowsPerPage, observableList.size());
        tempList = FXCollections.observableArrayList(observableList.subList(fromIndex, toIndex));
        TreeItem<PromotionVO> root = new RecursiveTreeItem<>(tempList, RecursiveTreeObject::getChildren);
//        this.setStyle("-fx-border-color: transparent; -fx-padding: 0; -fx-background-color: transparent");
        this.setRoot(root);
    }

    void createPage(int pageIndex) {
        int fromIndex = pageIndex * rowsPerPage;
        int toIndex = Math.min(fromIndex + rowsPerPage, observableList.size());
        tempList = FXCollections.observableArrayList(observableList.subList(fromIndex, toIndex));
        TreeItem<PromotionVO> root = new RecursiveTreeItem<>(tempList, RecursiveTreeObject::getChildren);
        this.setRoot(root);

        Timeline timeline = new Timeline();
        timeline.getKeyFrames().add(
                new KeyFrame(Duration.ZERO,
                        new KeyValue(this.opacityProperty(), 0)
                )
        );

        for (int i = 1; i <= 10; i++) {
            timeline.getKeyFrames().add(
                    new KeyFrame(new Duration(i * 80),
                            new KeyValue(this.opacityProperty(), i / 10.0)
                    )
            );
        }

        timeline.play();
    }

    public void setList(Collection<PromotionVO> list) {
        observableList.setAll(list);
    }

    public int getListSize() {
        return observableList.size();
    }

    public int getRowsPerPage() {
        return rowsPerPage;
    }

//    void delete(Set<PromotionVO> itemsToBeDeleted) { // 这个是没有和数据库交互的，不能用吧
//        if (itemsToBeDeleted.isEmpty()) {
//            return;
//        }
//
//        observableList.removeAll(itemsToBeDeleted);
//
//        int maxPageIndex = (observableList.size() - 1) / rowsPerPage + 1;
//        pagination.setPageCount(maxPageIndex); // 就算是0好像也不报错的
//
//        int currentPageIndex = pagination.getCurrentPageIndex();
//        int newPageIndex = 0;
//        if (currentPageIndex - 1 >= 0 && currentPageIndex < maxPageIndex) { // TODO 这里pageIndex在userListPane逻辑有点没看懂…就自己瞎改了
//            newPageIndex = currentPageIndex;
//        }
//        if (newPageIndex == currentPageIndex) {
//            // 这里写的很丑。
//            // 因为：如果newPageIndex和原来一样的，只改pagination就不会触发createPage；如果不一样（只有0的情况才不一样吧），还要改pagination
//            // 要么就是继createPage又改pagination，感觉有可能会两次create。
//            createPage(newPageIndex);
//        } else {
//            pagination.setCurrentPageIndex(newPageIndex);
//        }
//    }

    void delete() {
        if (chosenItems.isEmpty()) {
            return;
        }

        observableList.removeAll(chosenItems);
        chosenItems.clear();
        refresh(observableList);
    }

    void refresh(List<PromotionVO> list) {
        this.observableList = FXCollections.observableArrayList(list);

        int maxPageIndex = (observableList.size() - 1) / rowsPerPage + 1;
        pagination.setPageCount(maxPageIndex); // 就算是0好像也不报错的

        int currentPageIndex = pagination.getCurrentPageIndex();
        int newPageIndex = 0;
        if (currentPageIndex - 1 >= 0 && currentPageIndex < maxPageIndex) { // TODO 这里pageIndex在userListPane逻辑有点没看懂…就自己瞎改了
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

    public void setKeyWordProperty(StringProperty keyWordProperty) { // TODO 这样的传值很有喜欢…
        this.keyWordProperty = keyWordProperty;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }
}