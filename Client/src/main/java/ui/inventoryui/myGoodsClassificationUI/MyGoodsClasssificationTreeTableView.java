package ui.inventoryui.myGoodsClassificationUI;

import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import vo.inventoryVO.goodsTreeTableView.AbstractGoodsTreeTableViewVO;

public class MyGoodsClasssificationTreeTableView extends TreeTableView<AbstractGoodsTreeTableViewVO> {
    public MyGoodsClasssificationTreeTableView() {
        TreeTableColumn<AbstractGoodsTreeTableViewVO, String> nameColumn = new TreeTableColumn<>();
    }
}
