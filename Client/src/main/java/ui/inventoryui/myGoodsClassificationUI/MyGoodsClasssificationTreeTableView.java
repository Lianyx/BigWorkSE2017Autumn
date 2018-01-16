package ui.inventoryui.myGoodsClassificationUI;

import com.jfoenix.controls.JFXPopup;
import javafx.scene.control.*;
//import ui.common.treeTableRelated.OrdinaryStringColumn;
import javafx.scene.input.MouseButton;
import ui.common.treeTableRelated.GoodsAndGoodsClassificationNameCell;
import ui.common.treeTableRelated.OrdinaryStringColumn;
import ui.util.ListPopup;
import vo.inventoryVO.MyGoodsClassificationVO;
import vo.inventoryVO.goodsTreeTableView.AbstractGoodsTreeTableViewVO;
import vo.inventoryVO.goodsTreeTableView.GoodsClassificationTreeTableViewVO;
import vo.inventoryVO.goodsTreeTableView.GoodsTreeTableViewVO;

public class MyGoodsClasssificationTreeTableView extends TreeTableView<AbstractGoodsTreeTableViewVO> {
    public MyGoodsClasssificationTreeTableView() {
        TreeTableColumn<AbstractGoodsTreeTableViewVO, String> nameColumn = new OrdinaryStringColumn<>("名称", 200, "name");
        nameColumn.setCellFactory(param -> {
            TreeTableCell<AbstractGoodsTreeTableViewVO, String> cell = new GoodsAndGoodsClassificationNameCell();
            cell.setOnMouseClicked(e -> {
//                cell.getTreeTableRow().getTreeItem().getValue()
                if (e.getButton().equals(MouseButton.SECONDARY)) {
                    JFXPopup popup = new JFXPopup(cell.getTreeTableRow().getTreeItem().getValue().clickSecondaryPopUpList());
//                    JFXPopup popup = new JFXPopup(new GoodsPopUpListView());
                    popup.show(cell, JFXPopup.PopupVPosition.TOP, JFXPopup.PopupHPosition.RIGHT);
                }
            });
            return cell;
        });

        TreeTableColumn<AbstractGoodsTreeTableViewVO, String> typeColumn = new OrdinaryStringColumn<>("型号", 70, "type");
        TreeTableColumn<AbstractGoodsTreeTableViewVO, String> numColumn = new OrdinaryStringColumn<>("数量", 70, "num");
        TreeTableColumn<AbstractGoodsTreeTableViewVO, String> purPriceColumn = new OrdinaryStringColumn<>("进价", 70, "purPrice");
        TreeTableColumn<AbstractGoodsTreeTableViewVO, String> salePriceColumn = new OrdinaryStringColumn<>("售价", 70, "salePrice");
        TreeTableColumn<AbstractGoodsTreeTableViewVO, String> alarmNumColumn = new OrdinaryStringColumn<>("报警数量", 70, "alarmNumber");

        this.setRowFactory(tableView -> {
            TreeTableRow<AbstractGoodsTreeTableViewVO> row = new TreeTableRow<>();

            row.selectedProperty().addListener(e -> {
                if (row.isSelected()) {
                    row.toFront();
                } else {
                    row.setEffect(null);
                }
            });
            row.setOnMouseClicked(e -> {
                if (row.getTreeItem() != null) {
                    if (e.getButton().equals(MouseButton.PRIMARY) && e.getClickCount() == 2) {
                        row.getTreeItem().getValue().clickTwiceAftermath();
                    }
                }
            });
            return row;
        });


        this.getColumns().setAll(nameColumn, typeColumn, numColumn, purPriceColumn, salePriceColumn, alarmNumColumn);
        this.setShowRoot(true); // 因为这样在这里添加商品分类什么的比较容易
    }

    public void refresh(MyGoodsClassificationVO root) { // 外部给的这个就是只能是root。不然就有问题。
        TreeItem<AbstractGoodsTreeTableViewVO> treeItemRoot = makeTreeItemRoot(root);
        treeItemRoot.setExpanded(true);
        this.setRoot(treeItemRoot);
    }

    private TreeItem<AbstractGoodsTreeTableViewVO> makeTreeItemRoot(MyGoodsClassificationVO root) {
        AbstractGoodsTreeTableViewVO treeRootItem = new GoodsClassificationTreeTableViewVO(root);
        TreeItem<AbstractGoodsTreeTableViewVO> result = new TreeItem<>(treeRootItem);

        if (root.getGoods() != null && !root.getGoods().isEmpty()) {
            root.getGoods().forEach(g -> result.getChildren().add(new TreeItem<>(new GoodsTreeTableViewVO(g))));
            return result;
        }

        if (root.getChildren() != null) {
            root.getChildren().forEach(gc -> result.getChildren().add(makeTreeItemRoot(gc)));
        }
        return result;
    }
}
