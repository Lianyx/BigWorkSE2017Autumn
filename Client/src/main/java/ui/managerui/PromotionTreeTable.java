package ui.managerui;

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
        idColumn.setPrefWidth(40);
        idColumn.setCellValueFactory((TreeTableColumn.CellDataFeatures<PromotionVO, String> param) -> new ReadOnlyStringWrapper(param.getValue().getValue().getId()));

        JFXTreeTableColumn<PromotionVO, String> beginTimeColumn = new JFXTreeTableColumn<>("开始时间");
        beginTimeColumn.setPrefWidth(40);
        beginTimeColumn.setCellValueFactory((p) -> new ReadOnlyStringWrapper(p.getValue().getValue().getBeginTime().toString()));

        JFXTreeTableColumn<PromotionVO, String> endTimeColumn = new JFXTreeTableColumn<>("结束时间");
        endTimeColumn.setPrefWidth(40);
        endTimeColumn.setCellValueFactory((p) -> new ReadOnlyStringWrapper(p.getValue().getValue().getBeginTime().toString()));

        JFXTreeTableColumn<PromotionVO, String> commentColumn = new JFXTreeTableColumn<>("备注");
        commentColumn.setPrefWidth(40);
//        commentColumn.setCellValueFactory(p -> new Readon);


        setRowFactory(tableView -> {
            JFXTreeTableRow<PromotionVO> row = new JFXTreeTableRow<>();
            row.setPrefHeight(55);
            row.setStyle("-fx-border-color: rgb(233,237,239); -fx-border-width: 0.3;");
            row.setOnMouseClicked(null);

            return row;
        });
    }

    // TODO 需要这个吗？
    ObservableList<PromotionVO> getObservableList() {
        return observableList;
    }

    Node createPage(int pageIndex) {
        return null;
    }

    public void setList(Collection<PromotionVO> list) {
        observableList.setAll(list);
    }

    public int getRowsPerPage() {
        return rowsPerPage;
    }
}
