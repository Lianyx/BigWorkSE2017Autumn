package ui.inventoryui.inventoryReceiptui;

import com.jfoenix.controls.*;
import com.jfoenix.controls.cells.editors.IntegerTextFieldEditorBuilder;
import com.jfoenix.controls.cells.editors.TextFieldEditorBuilder;
import com.jfoenix.controls.cells.editors.base.GenericEditableTreeTableCell;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import ui.util.BoardController;
import ui.util.ColumnDecorator;
import ui.util.ListPopup;
import ui.util.PaneFactory;
import vo.inventoryVO.inventoryReceiptVO.ReceiptGoodsItemVO;


import java.util.ArrayList;
import java.util.List;

public class InventoryListItemTreeTable extends JFXTreeTableView<ReceiptGoodsItemVO> {
    private ObservableList<ReceiptGoodsItemVO> observableList = FXCollections.observableArrayList();

    private BoardController boardController;
    private StackPane mainpane;

    public InventoryListItemTreeTable() {
        super();

        mainpane = PaneFactory.getMainPane();

        ColumnDecorator columnDecorator = new ColumnDecorator();

        this.setEditable(true);

        JFXTreeTableColumn<ReceiptGoodsItemVO, String> goodsID = new JFXTreeTableColumn<>("GoodsID");
        goodsID.setPrefWidth(115.5);
        goodsID.setEditable(true);
        columnDecorator.setupCellValueFactory(goodsID, ReceiptGoodsItemVO::goodsIdProperty);
          goodsID.setCellFactory((TreeTableColumn<ReceiptGoodsItemVO, String> param) -> {
            return new GenericEditableTreeTableCell<>(new TextFieldEditorBuilder());
        });
        goodsID.setOnEditCommit((TreeTableColumn.CellEditEvent<ReceiptGoodsItemVO, String> t) -> {
            t.getTreeTableView().getTreeItem(t.getTreeTablePosition().getRow()).getValue().goodsIdProperty().set(t.getNewValue());
        });



        JFXTreeTableColumn<ReceiptGoodsItemVO, String> goodsName = new JFXTreeTableColumn<>("GoodsName");
        goodsName.setPrefWidth(115.5);
        columnDecorator.setupCellValueFactory(goodsName, ReceiptGoodsItemVO::goodsNameProperty);
        goodsName.setCellFactory((TreeTableColumn<ReceiptGoodsItemVO, String> param) -> {
            return new GenericEditableTreeTableCell<>(new TextFieldEditorBuilder());
        });
        goodsName.setOnEditCommit((TreeTableColumn.CellEditEvent<ReceiptGoodsItemVO, String> t) -> {
            t.getTreeTableView().getTreeItem(t.getTreeTablePosition().getRow()).getValue().goodsNameProperty().set(t.getNewValue());
        });


        JFXTreeTableColumn<ReceiptGoodsItemVO, Integer> inventoryNum = new JFXTreeTableColumn<>("InventoryNum");
        inventoryNum.setPrefWidth(115.5);
        columnDecorator.setupCellValueFactory(inventoryNum, l -> l.inventoryNumProperty().asObject());
        inventoryNum.setCellFactory((TreeTableColumn<ReceiptGoodsItemVO, Integer> param) -> {
            return new GenericEditableTreeTableCell<>(new IntegerTextFieldEditorBuilder());
        });
        inventoryNum.setOnEditCommit((TreeTableColumn.CellEditEvent<ReceiptGoodsItemVO, Integer> t) -> {
            t.getTreeTableView().getTreeItem(t.getTreeTablePosition().getRow()).getValue().inventoryNumProperty().set(t.getNewValue());
        });


        JFXTreeTableColumn<ReceiptGoodsItemVO, Integer> sendNum = new JFXTreeTableColumn<>("SendNum");
        sendNum.setPrefWidth(115.5);
        columnDecorator.setupCellValueFactory(sendNum, l -> l.sendNumProperty().asObject());
        sendNum.setCellFactory((TreeTableColumn<ReceiptGoodsItemVO, Integer> param) -> {
            return new GenericEditableTreeTableCell<>(new IntegerTextFieldEditorBuilder());
        });
        sendNum.setOnEditCommit((TreeTableColumn.CellEditEvent<ReceiptGoodsItemVO, Integer> t) -> {
            t.getTreeTableView().getTreeItem(t.getTreeTablePosition().getRow()).getValue().sendNumProperty().set(t.getNewValue());
        });
        //System.out.println(observableList.toString());

        TreeItem<ReceiptGoodsItemVO> root = new RecursiveTreeItem<>(observableList, RecursiveTreeObject::getChildren);
        this.setRoot(root);
        this.setEditable(true);
        this.setShowRoot(false);
        this.getColumns().setAll(goodsID, goodsName, inventoryNum, sendNum);
    }


    public void setList(List<ReceiptGoodsItemVO> goods) {
        observableList.setAll(goods);
        System.out.println("tree 双击"+observableList.toString());
    }

 //   public void setList(ObservableList<ReceiptGoodsItemVO> list){observableList.setAll(list);}

    public void removeGood(ReceiptGoodsItemVO good) {
        observableList.remove(good);
    }

    public void addGood(ReceiptGoodsItemVO good) {
        observableList.add(good);
    }

    public void clear(){
        observableList.clear();
    }

    public ArrayList<ReceiptGoodsItemVO> getList(){
        ArrayList<ReceiptGoodsItemVO> arrayList = new ArrayList<>();
        observableList.forEach(i->arrayList.add(i));
        return arrayList;
    }

}
