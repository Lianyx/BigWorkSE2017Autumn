package ui.inventoryManagerui.inventoryGiftReceiptui;

import blService.inventoryblService.InventoryblService;
import com.jfoenix.controls.*;
import com.jfoenix.controls.cells.editors.IntegerTextFieldEditorBuilder;
import com.jfoenix.controls.cells.editors.base.GenericEditableTreeTableCell;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.layout.StackPane;
import ui.util.BoardController;
import vo.inventoryVO.uiReceipt.GiftuiGoodsItemVO;

import java.util.Set;
import java.util.function.Function;

public class GiftListItemTreeTable extends JFXTreeTableView<GiftuiGoodsItemVO>{
    private ObservableList<GiftuiGoodsItemVO> observableList = FXCollections.observableArrayList();

    InventoryblService inventoryblService;
    BoardController boardController;
    StackPane mainpane;

    public GiftListItemTreeTable() {
        super();



        JFXTreeTableColumn<GiftuiGoodsItemVO, String> goodId = new JFXTreeTableColumn<>("商品编号");
        goodId.setPrefWidth(97);
        setupCellValueFactory(goodId,GiftuiGoodsItemVO::goodIdProperty);


        JFXTreeTableColumn<GiftuiGoodsItemVO, String> goodName = new JFXTreeTableColumn<>("商品名");
        goodName.setPrefWidth(97);
        setupCellValueFactory(goodName, GiftuiGoodsItemVO::goodNameProperty);

        JFXTreeTableColumn<GiftuiGoodsItemVO, Integer> inventoryNum = new JFXTreeTableColumn<>("库存数量");
        inventoryNum.setPrefWidth(97);
        setupCellValueFactory(inventoryNum, l->l.inventoryNumProperty().asObject());
        inventoryNum.setCellFactory((TreeTableColumn<GiftuiGoodsItemVO, Integer> param) -> {
            return new GenericEditableTreeTableCell<>(
                    new IntegerTextFieldEditorBuilder());
        });
        inventoryNum.setOnEditCommit((TreeTableColumn.CellEditEvent<GiftuiGoodsItemVO, Integer> t) -> {
            t.getTreeTableView()
                    .getTreeItem(t.getTreeTablePosition()
                            .getRow())
                    .getValue().inventoryNumProperty().set(t.getNewValue());
        });


        JFXTreeTableColumn<GiftuiGoodsItemVO, Integer> sendNum = new JFXTreeTableColumn<>("赠送数量");
        sendNum.setPrefWidth(97);
        setupCellValueFactory(sendNum, l -> l.sendNumProperty().asObject());

        sendNum.setCellFactory((TreeTableColumn<GiftuiGoodsItemVO, Integer> param) -> {
            return new GenericEditableTreeTableCell<>(
                    new IntegerTextFieldEditorBuilder());
        });
        sendNum.setOnEditCommit((TreeTableColumn.CellEditEvent<GiftuiGoodsItemVO, Integer> t) -> {
            t.getTreeTableView()
                    .getTreeItem(t.getTreeTablePosition()
                            .getRow())
                    .getValue().inventoryNumProperty().set(t.getNewValue());
        });

        JFXTreeTableColumn<GiftuiGoodsItemVO, Double> price = new JFXTreeTableColumn<>("价格");
        price.setPrefWidth(96);
        setupCellValueFactory(price, l -> l.goodPriceProperty().asObject());


        /*this.setRowFactory(tableView -> {
            JFXTreeTableRow row = new JFXTreeTableRow();
            row.setStyle("-fx-border-color: rgb(233,237,239); -fx-border-width: 0.3;");
            row.setOnMouseClicked((MouseEvent event) -> {
                if (event.getButton() == MouseButton.SECONDARY) {
                    ListPopup listPopup = new ListPopup();
                    JFXPopup popup = new JFXPopup(listPopup);
                    listPopup.getListview().setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            GiftListItemPane giftListItemPane = new GiftListItemPane((GiftuiGoodsItemVO) row.getTreeItem().getValue());
                            JFXDialog dialog = new JFXDialog(mainpane, giftListItemPane, JFXDialog.DialogTransition.CENTER);
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
*/

        TreeItem<GiftuiGoodsItemVO> root = new RecursiveTreeItem<>(observableList, RecursiveTreeObject::getChildren);
        this.setRoot(root);
        this.setEditable(true);
        this.setShowRoot(false);
        this.getColumns().setAll(goodId, goodName, inventoryNum, sendNum, price);
       // this.getColumns().setAll(goodName);

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

    public void setList(Set<GiftuiGoodsItemVO> goods){
        observableList.setAll(goods);
    }

    public void removeGood(GiftuiGoodsItemVO good){
        observableList.remove(good);
    }

    public void addGood(GiftuiGoodsItemVO good){
        observableList.add(good);
    }

    private <T> void setupCellValueFactory(JFXTreeTableColumn<GiftuiGoodsItemVO, T> column, Function<GiftuiGoodsItemVO, ObservableValue<T>> mapper) {
        column.setCellValueFactory((TreeTableColumn.CellDataFeatures<GiftuiGoodsItemVO, T> param) -> {
            if (column.validateValue(param)) {
                return mapper.apply(param.getValue().getValue());
            } else {
                return column.getComputedValue(param);
            }
        });
    }
}

