package ui.inventoryui.myGoodsClassificationUI;

import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableRow;
import javafx.scene.control.TreeTableView;
//import ui.common.treeTableRelated.OrdinaryStringColumn;
import ui.common.treeTableRelated.OrdinaryStringColumn;
import vo.inventoryVO.GoodsVO;
import vo.inventoryVO.MyGoodsClassificationVO;
import vo.inventoryVO.goodsTreeTableView.AbstractGoodsTreeTableViewVO;
import vo.inventoryVO.goodsTreeTableView.GoodsClassficationTreeTableViewVO;
import vo.inventoryVO.goodsTreeTableView.GoodsTreeTableViewVO;

public class MyGoodsClasssificationTreeTableView extends TreeTableView<AbstractGoodsTreeTableViewVO> {
    public MyGoodsClasssificationTreeTableView() {
        TreeTableColumn<AbstractGoodsTreeTableViewVO, String> nameColumn = new OrdinaryStringColumn<>("名称", 80, AbstractGoodsTreeTableViewVO::getName);
        TreeTableColumn<AbstractGoodsTreeTableViewVO, String> typeColumn = new OrdinaryStringColumn<>("型号", 60, AbstractGoodsTreeTableViewVO::getType);
        TreeTableColumn<AbstractGoodsTreeTableViewVO, String> numColumn = new OrdinaryStringColumn<>("数量", 50, AbstractGoodsTreeTableViewVO::getNum);
        TreeTableColumn<AbstractGoodsTreeTableViewVO, String> purPriceColumn = new OrdinaryStringColumn<>("进价", 50, AbstractGoodsTreeTableViewVO::getPurPrice);
        TreeTableColumn<AbstractGoodsTreeTableViewVO, String> salePriceColumn = new OrdinaryStringColumn<>("售价", 50, AbstractGoodsTreeTableViewVO::getSalePrice);
        TreeTableColumn<AbstractGoodsTreeTableViewVO, String> alarmNumColumn = new OrdinaryStringColumn<>("报警数量", 50, AbstractGoodsTreeTableViewVO::getAlarmNumber);

        this.getColumns().setAll(nameColumn, typeColumn, numColumn, purPriceColumn, salePriceColumn, alarmNumColumn);
        this.setShowRoot(false);

        this.setRowFactory(tableView -> {
            TreeTableRow<AbstractGoodsTreeTableViewVO> row = new TreeTableRow<>();

            // set style, maybe selectedProperty? todo

            row.setOnMouseClicked(e -> {
                if (row.getTreeItem().getValue() != null && e.getClickCount() == 2) {
                    row.getTreeItem().getValue().clickTwiceAftermath();
                }
            });

            return row;
        });
    }

    public void refresh(MyGoodsClassificationVO root) { // 外部给的这个就是只能是root。不然就有问题。
        TreeItem<AbstractGoodsTreeTableViewVO> treeItemRoot = makeTreeItemRoot(root);
        this.setRoot(treeItemRoot);
    }

    private TreeItem<AbstractGoodsTreeTableViewVO> makeTreeItemRoot(MyGoodsClassificationVO root) {
        AbstractGoodsTreeTableViewVO treeRootItem = new GoodsClassficationTreeTableViewVO(root);
        TreeItem<AbstractGoodsTreeTableViewVO> result = new TreeItem<>(treeRootItem);

        if (root.getGoods() != null && !root.getGoods().isEmpty()) {
            root.getGoods().forEach(g -> result.getChildren().add(new TreeItem<>(new GoodsTreeTableViewVO(g))));
            return result;
        }

        if (root.getChildren() != null) {
            root.getChildren().forEach(gc -> result.getChildren().add(new TreeItem<>(new GoodsClassficationTreeTableViewVO(gc))));
        }
        return result;
    }
}
