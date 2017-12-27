package ui.managerui.promotion;

import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableRow;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.layout.BorderPane;
import javafx.util.Duration;
import ui.userui.usermanagerui.BoardController;
import vo.UserListVO;
import vo.promotionVO.PromotionVO;

import java.util.Collection;

public class PromotionTreeTable extends JFXTreeTableView<PromotionVO> {
    private ObservableList<PromotionVO> observableList = FXCollections.observableArrayList();
    private BoardController boardController;
    private int rowsPerPage = 7;

    public PromotionTreeTable(Collection<PromotionVO> list, BoardController boardController) {
        observableList.setAll(list);
        this.boardController = boardController;

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
        getColumns().addAll(idColumn, beginTimeColumn, endTimeColumn, commentColumn);
    }

    Node createPage(int pageIndex) {
        int fromIndex = pageIndex * rowsPerPage;
        int toIndex = Math.min(fromIndex + rowsPerPage, observableList.size());
        ObservableList<PromotionVO> tempList = FXCollections.observableList(observableList.subList(fromIndex, toIndex));
        TreeItem<PromotionVO> root = new RecursiveTreeItem<>(tempList, RecursiveTreeObject::getChildren);
        setRoot(root);
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
        return new BorderPane(this);
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
}
