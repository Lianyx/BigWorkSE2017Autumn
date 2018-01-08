package ui.managerui.promotionui;

import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.cells.editors.IntegerTextFieldEditorBuilder;
import com.jfoenix.controls.cells.editors.base.GenericEditableTreeTableCell;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import vo.promotionVO.PromotionGoodsItemVO;

import java.util.Collection;

public class GoodsTreeTable extends JFXTreeTableView<PromotionGoodsItemVO> {

    private ObservableList<PromotionGoodsItemVO> gifts = FXCollections.observableArrayList();;

    public GoodsTreeTable() {
        TreeItem<PromotionGoodsItemVO> root = new RecursiveTreeItem<>(gifts, RecursiveTreeObject::getChildren);

        JFXTreeTableColumn<PromotionGoodsItemVO, String> idColumn = new JFXTreeTableColumn<>("商品编号");
        idColumn.setPrefWidth(94);
        idColumn.setCellValueFactory((TreeTableColumn.CellDataFeatures<PromotionGoodsItemVO, String> param) -> new ReadOnlyStringWrapper(param.getValue().getValue().getId()));

        JFXTreeTableColumn<PromotionGoodsItemVO, String> nameColumn = new JFXTreeTableColumn<>("商品名称");
        nameColumn.setPrefWidth(94);
        nameColumn.setCellValueFactory((p) -> new ReadOnlyStringWrapper(p.getValue().getValue().getName()));

        JFXTreeTableColumn<PromotionGoodsItemVO, Double> unitPriceColumn = new JFXTreeTableColumn<>("单价");
        unitPriceColumn.setPrefWidth(93);
        unitPriceColumn.setCellValueFactory((p) -> new ReadOnlyObjectWrapper<>(p.getValue().getValue().getUnitPrice()));

        JFXTreeTableColumn<PromotionGoodsItemVO, Integer> numColumn = new JFXTreeTableColumn<>("数量");
        numColumn.setPrefWidth(93);
        numColumn.setCellValueFactory(p -> p.getValue().getValue().numProperty().asObject());
        numColumn.setCellFactory((TreeTableColumn<PromotionGoodsItemVO, Integer> param) -> new GenericEditableTreeTableCell<>(
                new IntegerTextFieldEditorBuilder()));

        setShowRoot(false);
        setEditable(true);
        getColumns().setAll(idColumn, nameColumn, unitPriceColumn, numColumn);
        setRoot(root);
    }

    public void add(PromotionGoodsItemVO promotionGoodsItemVO) {
        gifts.add(promotionGoodsItemVO);
    }

    public void setAll(Collection<PromotionGoodsItemVO> newGifts) {
        gifts.clear();
        gifts.setAll(newGifts);
    }
    public ObservableList<PromotionGoodsItemVO> getGifts() {
        return gifts;
    }
}
