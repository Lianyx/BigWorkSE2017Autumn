package ui.inventoryManagerui;

import blService.goodsClassificationblService.GoodsClassificationblService;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.CheckBoxTreeItem;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.CheckBoxTreeCell;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import org.controlsfx.control.CheckTreeView;
import ui.util.BoardController;
import vo.inventoryVO.GoodsClassificationVO;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class GoodsClassificationTreeView extends CheckTreeView<GoodsClassificationVO>{
  //  private CheckTreeView<GoodsClassificationVO> treeView = new CheckTreeView<>();
    private BoardController boardController;
    private StackPane mainpane;
    private GoodsClassificationblService goodsClassificationblService;
    private Set<GoodsClassificationVO> set;
    private CheckBoxTreeItem<GoodsClassificationVO> root = new CheckBoxTreeItem<>(new GoodsClassificationVO());

    GoodsClassificationVO vo1 = new GoodsClassificationVO();
    GoodsClassificationVO vo2 = new GoodsClassificationVO();
    GoodsClassificationVO vo3 = new GoodsClassificationVO();
    GoodsClassificationVO vo4 = new GoodsClassificationVO();
    private CheckBoxTreeItem<GoodsClassificationVO> treeItem_Light1 = new CheckBoxTreeItem<>(vo1);
    private CheckBoxTreeItem<GoodsClassificationVO> treeItem_Light2 = new CheckBoxTreeItem<>(vo2);
    private CheckBoxTreeItem<GoodsClassificationVO> treeItem_Light3 = new CheckBoxTreeItem<>(vo3);
    private CheckBoxTreeItem<GoodsClassificationVO> treeItem_Light4 = new CheckBoxTreeItem<>(vo4);



    public GoodsClassificationTreeView() {
        super();
        root.setExpanded(true);
        root.getChildren().addAll(treeItem_Light1,treeItem_Light2,treeItem_Light3,treeItem_Light4);
        this.setRoot(root);


    }


    public void setBoardController(BoardController boardController) {
        this.boardController = boardController;
    }

    public void setMainpane(StackPane mainpane) {
        this.mainpane = mainpane;
    }

    public void setGoodsClassificationblService(GoodsClassificationblService goodsClassificationblService) {
        this.goodsClassificationblService = goodsClassificationblService;
    }

    public void setGoodsClassificationVO(Set<GoodsClassificationVO> set) {
        this.set = set;
    }
}
