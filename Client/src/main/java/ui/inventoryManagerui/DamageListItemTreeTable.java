package ui.inventoryManagerui;

import blService.inventoryblService.InventoryblService;
import com.jfoenix.controls.*;
import com.jfoenix.controls.cells.editors.IntegerTextFieldEditorBuilder;
import com.jfoenix.controls.cells.editors.base.GenericEditableTreeTableCell;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import ui.userui.usermanagerui.BoardController;
import ui.util.ListPopup;
import vo.inventoryVO.uiReceipt.DamageuiGoodsItemVO;

import java.util.Set;
import java.util.function.Function;

public class DamageListItemTreeTable extends JFXTreeTableView<DamageuiGoodsItemVO> {
    private ObservableList<DamageuiGoodsItemVO> observableList = FXCollections.observableArrayList();

    InventoryblService inventoryblService;
    BoardController boardController;
    StackPane mainpane;

    public DamageListItemTreeTable() {
        super();


        JFXTreeTableColumn<DamageuiGoodsItemVO, String> goodsId = new JFXTreeTableColumn<>("商品编号");
        goodsId.setPrefWidth(97);
        setupCellValueFactory(goodsId,DamageuiGoodsItemVO::goodIdProperty);

        JFXTreeTableColumn<DamageuiGoodsItemVO, String> goodsName = new JFXTreeTableColumn<>("商品名");
        goodsName.setPrefWidth(97);
        setupCellValueFactory(goodsName, DamageuiGoodsItemVO::goodNameProperty);

        JFXTreeTableColumn<DamageuiGoodsItemVO, Integer> inventoryNum = new JFXTreeTableColumn<>("库存数量");
        inventoryNum.setPrefWidth(97);
        setupCellValueFactory(inventoryNum, l -> l.inventoryNumProperty().asObject());
        inventoryNum.setCellFactory((TreeTableColumn<DamageuiGoodsItemVO, Integer> param) -> {
            return new GenericEditableTreeTableCell<>(
                    new IntegerTextFieldEditorBuilder());
        });
        inventoryNum.setOnEditCommit((TreeTableColumn.CellEditEvent<DamageuiGoodsItemVO, Integer> t) -> {
            t.getTreeTableView()
                    .getTreeItem(t.getTreeTablePosition()
                            .getRow())
                    .getValue().inventoryNumProperty().set(t.getNewValue());
        });


        JFXTreeTableColumn<DamageuiGoodsItemVO, Integer> factNum = new JFXTreeTableColumn<>("实际数量");
        factNum.setPrefWidth(97);
        setupCellValueFactory(factNum, l -> l.factNumProperty().asObject());
        factNum.setCellFactory((TreeTableColumn<DamageuiGoodsItemVO, Integer> param) -> {
            return new GenericEditableTreeTableCell<>(
                    new IntegerTextFieldEditorBuilder());
        });
        factNum.setOnEditCommit((TreeTableColumn.CellEditEvent<DamageuiGoodsItemVO, Integer> t) -> {
            t.getTreeTableView()
                    .getTreeItem(t.getTreeTablePosition()
                            .getRow())
                    .getValue().inventoryNumProperty().set(t.getNewValue());
        });

        JFXTreeTableColumn<DamageuiGoodsItemVO, Double> price = new JFXTreeTableColumn<>("价格");
        price.setPrefWidth(96);
        setupCellValueFactory(price, l -> l.goodPriceProperty().asObject());


        this.setRowFactory(tableView -> {
            JFXTreeTableRow row = new JFXTreeTableRow();
            row.setStyle("-fx-border-color: rgb(233,237,239); -fx-border-width: 0.3;");
            row.setOnMouseClicked((MouseEvent event) -> {
                if (event.getButton() == MouseButton.SECONDARY) {
                    ListPopup listPopup = new ListPopup();
                    JFXPopup popup = new JFXPopup(listPopup);
                    listPopup.getListview().setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            DamageListItemPane damageListItemPane = new DamageListItemPane((DamageuiGoodsItemVO) row.getTreeItem().getValue());
                            JFXDialog dialog = new JFXDialog(mainpane, damageListItemPane, JFXDialog.DialogTransition.CENTER);
                            dialog.show();
                            popup.hide();
                        }
                    });
                    popup.show(row, JFXPopup.PopupVPosition.TOP, JFXPopup.PopupHPosition.RIGHT);
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


        TreeItem<DamageuiGoodsItemVO> root = new RecursiveTreeItem<>(observableList, RecursiveTreeObject::getChildren);
        this.setRoot(root);
        this.setEditable(true);
        this.setShowRoot(false);
        this.getColumns().setAll(goodsId, goodsName, inventoryNum, factNum, price);

    }

    public void setInventoryblService(InventoryblService inventoryblService) {
        this.inventoryblService = inventoryblService;
    }

    public void setBoardController(BoardController boardController) {
        this.boardController = boardController;
    }

    public void setMainpane(StackPane mainpane) {
        this.mainpane = mainpane;
    }

    public void setList(Set<DamageuiGoodsItemVO> goods){
        observableList.setAll(goods);
    }

    public void removeGood(DamageuiGoodsItemVO good){
        observableList.remove(good);
    }

    public void addGood(DamageuiGoodsItemVO good){
        observableList.add(good);
    }


    private <T> void setupCellValueFactory(JFXTreeTableColumn<DamageuiGoodsItemVO, T> column, Function<DamageuiGoodsItemVO, ObservableValue<T>> mapper) {
        column.setCellValueFactory((TreeTableColumn.CellDataFeatures<DamageuiGoodsItemVO, T> param) -> {
            if (column.validateValue(param)) {
                return mapper.apply(param.getValue().getValue());
            } else {
                return column.getComputedValue(param);
            }
        });
    }
}
