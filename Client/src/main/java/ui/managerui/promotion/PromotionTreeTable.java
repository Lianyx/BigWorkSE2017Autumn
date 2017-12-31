package ui.managerui.promotion;

import com.jfoenix.controls.*;
import com.jfoenix.controls.cells.editors.base.JFXTreeTableCell;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.util.Callback;
import javafx.util.Duration;
import ui.userui.usermanagerui.BoardController;
import ui.userui.usermanagerui.ChosenItem;
import vo.UserListVO;
import vo.promotionVO.PromotionVO;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class PromotionTreeTable extends JFXTreeTableView<PromotionVO> {
    private ObservableList<PromotionVO> observableList = FXCollections.observableArrayList();
    private ObservableList<PromotionVO> tempList;
    private BoardController boardController;
    private int rowsPerPage = 7;
    private Set<PromotionVO> chosenItems = new HashSet<>();

    public PromotionTreeTable(Collection<PromotionVO> list, BoardController boardController) {
        observableList.setAll(list);
        this.boardController = boardController;

        JFXTreeTableColumn<PromotionVO, Boolean> choose = new JFXTreeTableColumn<>("  ");
        choose.setPrefWidth(40);
        choose.setCellValueFactory(param -> param.getValue().getValue().selectedProperty());
        choose.setCellFactory(p -> new ChooseBoxCell());

        JFXTreeTableColumn<PromotionVO, String> idColumn = new JFXTreeTableColumn<>("编号");
        idColumn.setPrefWidth(200);
        idColumn.setCellValueFactory((TreeTableColumn.CellDataFeatures<PromotionVO, String> param) -> new ReadOnlyStringWrapper(param.getValue().getValue().getId()));

        JFXTreeTableColumn<PromotionVO, String> beginTimeColumn = new JFXTreeTableColumn<>("开始时间");
        beginTimeColumn.setPrefWidth(100);
        beginTimeColumn.setCellValueFactory((p) -> new ReadOnlyStringWrapper(p.getValue().getValue().getBeginTime().toLocalDate().toString()));

        JFXTreeTableColumn<PromotionVO, String> endTimeColumn = new JFXTreeTableColumn<>("结束时间");
        endTimeColumn.setPrefWidth(100);
        endTimeColumn.setCellValueFactory((p) -> new ReadOnlyStringWrapper(p.getValue().getValue().getBeginTime().toLocalDate().toString()));

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

        int pageIndex = 0;
        int fromIndex = pageIndex * rowsPerPage;
        int toIndex = Math.min(fromIndex + rowsPerPage, observableList.size());
        tempList = FXCollections.observableList(observableList.subList(fromIndex, toIndex));
        TreeItem<PromotionVO> root = new RecursiveTreeItem<>(tempList, RecursiveTreeObject::getChildren);
        this.setRoot(root);
    }

    Node createPage(int pageIndex) {
        int fromIndex = pageIndex * rowsPerPage;
        int toIndex = Math.min(fromIndex + rowsPerPage, observableList.size());
        tempList = FXCollections.observableList(observableList.subList(fromIndex, toIndex));
        TreeItem<PromotionVO> root = new RecursiveTreeItem<>(tempList, RecursiveTreeObject::getChildren);
        this.setRoot(root);
//        this.setStyle("-fx-border-color: transparent; -fx-padding: 0; -fx-background-color: transparent");

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
//        return new BorderPane(this);
        return null;
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

    public void testDelete(Pagination p) {
        chosenItems.forEach(i -> {
            observableList.remove(i);
            createPage(p.getCurrentPageIndex());
            p.setPageCount(getListSize() / getRowsPerPage() + 1);
//            (observableList.indexOf(promotionVO) + 1);
        });

//        int currentPageIndex = p.getCurrentPageIndex();
//        System.out.println(currentPageIndex);
//        p.setCurrentPageIndex(1);
//        Platform.runLater(() -> p.setCurrentPageIndex(currentPageIndex));
    }

    private class ChooseBoxCell extends JFXTreeTableCell<PromotionVO, Boolean> {
        private JFXCheckBox cb = new JFXCheckBox("");

        public ChooseBoxCell() {
            cb.setOnMouseClicked(e -> {
//                if (getTreeTableRow() != null && getTreeTableRow().getItem() != null) {
//                TreeTableView<>
                PromotionVO promotionVO = getTreeTableRow().getItem();
                System.out.println(promotionVO);
                promotionVO.setSelected(!promotionVO.isSelected());

                if (promotionVO.isSelected()) {
                    chosenItems.add(promotionVO);
                } else {
                    chosenItems.remove(promotionVO);
                }
//                }
            });
        }


        @Override
        public void updateItem(Boolean item, boolean empty) {
            super.updateItem(item, empty);

            if (empty) {
                setGraphic(null);
            } else {
                setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                cb.setSelected(item);
                setGraphic(cb);
            }

        }
    }
}
