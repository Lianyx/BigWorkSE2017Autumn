package ui.inventoryManagerui;

import blService.inventoryblService.InventoryShowblService;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.cells.editors.base.JFXTreeTableCell;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableCell;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;
import javafx.util.Callback;
import javafx.util.Duration;
import ui.util.BoardController;
import ui.util.CircleImageView;
import ui.util.TokenLabel;
import vo.inventoryVO.InventoryViewItemVO;
import vo.inventoryVO.InventoryViewVO;

import javax.xml.crypto.dom.DOMCryptoContext;
import java.util.Set;

public class InventoryViewTreeTable extends JFXTreeTableView<InventoryViewItemVO> {
    private ObservableList<InventoryViewItemVO> observableList = FXCollections.observableArrayList();
    private ObservableList<InventoryViewItemVO> observableListtemp;
    private int rowsPerPage = 7;
    private BoardController boardController;
    private InventoryShowblService showblService;
    private StackPane mainpane;

    public ObservableList<InventoryViewItemVO> getObservableList() {
        return observableList;
    }

    public int getRowsPerPage() {
        return rowsPerPage;
    }

    public InventoryViewTreeTable(InventoryShowblService showblService,BoardController boardController,StackPane mainpane){
        super();
        this.mainpane = mainpane;
        this.showblService = showblService;
        this.boardController = boardController;


        JFXTreeTableColumn image = new JFXTreeTableColumn("  ");
        image.setPrefWidth(50);
        Callback<TreeTableColumn, TreeTableCell> imageCellFactory = (TreeTableColumn p) -> new ImageCell();
        image.setCellValueFactory(new TreeItemPropertyValueFactory<>("image"));
        image.setCellFactory(imageCellFactory);

        JFXTreeTableColumn<InventoryViewItemVO, String> goodName = new JFXTreeTableColumn("商品名称");
        goodName.setPrefWidth(80);
        goodName.setCellValueFactory((TreeTableColumn.CellDataFeatures<InventoryViewItemVO, String> param) -> {
            if (goodName.validateValue(param)) {
                return new ReadOnlyObjectWrapper(param.getValue().getValue().getGoodName());
            } else {
                return goodName.getComputedValue(param);
            }
        });

        JFXTreeTableColumn<InventoryViewItemVO, String> goodsId = new JFXTreeTableColumn("商品编号");
        goodsId.setPrefWidth(120);
        goodsId.setCellValueFactory((TreeTableColumn.CellDataFeatures<InventoryViewItemVO, String > param) -> {
            if (goodsId.validateValue(param)) {
                return new ReadOnlyObjectWrapper(param.getValue().getValue().getGoodId());
            } else {
                return goodsId.getComputedValue(param);
            }
        });

        JFXTreeTableColumn<InventoryViewItemVO, Integer> stockInNum = new JFXTreeTableColumn("入库数量");
        stockInNum.setPrefWidth(120);
        stockInNum.setCellValueFactory((TreeTableColumn.CellDataFeatures<InventoryViewItemVO, Integer> param) -> {
            if (stockInNum.validateValue(param)) {
                return new ReadOnlyObjectWrapper(param.getValue().getValue().getStockInNum());
            } else {
                return stockInNum.getComputedValue(param);
            }
        });
        Callback<TreeTableColumn<InventoryViewItemVO, Integer>, TreeTableCell<InventoryViewItemVO, Integer>> tokenCellFactory_stockIn = (TreeTableColumn<InventoryViewItemVO, Integer> p) -> new TokenCell();
        stockInNum.setCellFactory(tokenCellFactory_stockIn);

        JFXTreeTableColumn<InventoryViewItemVO, Double> stockInMoney = new JFXTreeTableColumn("入库金额");
        stockInMoney.setPrefWidth(120);
        stockInMoney.setCellValueFactory((TreeTableColumn.CellDataFeatures<InventoryViewItemVO, Double> param) -> {
            if (stockInMoney.validateValue(param)) {
                return new ReadOnlyObjectWrapper(param.getValue().getValue().getStockInMoney());
            } else {
                return stockInMoney.getComputedValue(param);
            }
        });

        JFXTreeTableColumn<InventoryViewItemVO, Integer> stockOutNum = new JFXTreeTableColumn("出库数量");
        stockOutNum.setPrefWidth(120);
        stockOutNum.setCellValueFactory((TreeTableColumn.CellDataFeatures<InventoryViewItemVO, Integer> param) -> {
            if (stockOutNum.validateValue(param)) {
                return new ReadOnlyObjectWrapper(param.getValue().getValue().getStockOutNum());
            } else {
                return stockOutNum.getComputedValue(param);
            }
        });
        Callback<TreeTableColumn<InventoryViewItemVO, Integer>, TreeTableCell<InventoryViewItemVO, Integer>> tokenCellFactory_stockOut = (TreeTableColumn<InventoryViewItemVO, Integer> p) -> new TokenCell();
        stockOutNum.setCellFactory(tokenCellFactory_stockOut);

        JFXTreeTableColumn<InventoryViewItemVO, Double> stockOutMoney = new JFXTreeTableColumn("出库金额");
        stockOutMoney.setPrefWidth(120);
        stockOutMoney.setCellValueFactory((TreeTableColumn.CellDataFeatures<InventoryViewItemVO, Double> param) -> {
            if (stockOutMoney.validateValue(param)) {
                return new ReadOnlyObjectWrapper(param.getValue().getValue().getStockOutMoney());
            } else {
                return stockOutMoney.getComputedValue(param);
            }
        });

        JFXTreeTableColumn<InventoryViewItemVO, Integer> saleNum = new JFXTreeTableColumn("销售数量");
        saleNum.setPrefWidth(120);
        saleNum.setCellValueFactory((TreeTableColumn.CellDataFeatures<InventoryViewItemVO, Integer> param) -> {
            if (saleNum.validateValue(param)) {
                return new ReadOnlyObjectWrapper(param.getValue().getValue().getSaleNum());
            } else {
                return saleNum.getComputedValue(param);
            }
        });
        Callback<TreeTableColumn<InventoryViewItemVO, Integer>, TreeTableCell<InventoryViewItemVO, Integer>> tokenCellFactory_sale = (TreeTableColumn<InventoryViewItemVO, Integer> p) -> new TokenCell();
        saleNum.setCellFactory(tokenCellFactory_sale);

        JFXTreeTableColumn<InventoryViewItemVO, Double> saleMoney = new JFXTreeTableColumn("销售金额");
        saleMoney.setPrefWidth(120);
        saleMoney.setCellValueFactory((TreeTableColumn.CellDataFeatures<InventoryViewItemVO, Double> param) -> {
            if (saleMoney.validateValue(param)) {
                return new ReadOnlyObjectWrapper(param.getValue().getValue().getSaleMoney());
            } else {
                return saleMoney.getComputedValue(param);
            }
        });

        JFXTreeTableColumn<InventoryViewItemVO, Integer> stockPurNum = new JFXTreeTableColumn("进货数量");
        stockPurNum.setPrefWidth(120);
        stockPurNum.setCellValueFactory((TreeTableColumn.CellDataFeatures<InventoryViewItemVO, Integer> param) -> {
            if (stockPurNum.validateValue(param)) {
                return new ReadOnlyObjectWrapper(param.getValue().getValue().getStockPurNum());
            } else {
                return stockPurNum.getComputedValue(param);
            }
        });
        Callback<TreeTableColumn<InventoryViewItemVO, Integer>, TreeTableCell<InventoryViewItemVO, Integer>> tokenCellFactory_stockPur = (TreeTableColumn<InventoryViewItemVO, Integer> p) -> new TokenCell();
        stockPurNum.setCellFactory(tokenCellFactory_stockPur);

        JFXTreeTableColumn<InventoryViewItemVO, Double> stockPurMoney = new JFXTreeTableColumn("进货金额");
        stockPurMoney.setPrefWidth(120);
        stockPurMoney.setCellValueFactory((TreeTableColumn.CellDataFeatures<InventoryViewItemVO, Double> param) -> {
            if (stockPurMoney.validateValue(param)) {
                return new ReadOnlyObjectWrapper(param.getValue().getValue().getStockPurMoney());
            } else {
                return stockPurMoney.getComputedValue(param);
            }
        });


        setCurrentItemsCount(rowsPerPage);
        this.setShowRoot(false);

        this.getColumns().addAll(image,goodName,goodsId,stockInNum,stockInMoney,stockOutNum,stockOutMoney,saleNum,saleMoney,stockPurNum,stockPurMoney);




    }

    public void setInventoryView(Set<InventoryViewItemVO> viewSet){
        observableList.setAll(viewSet);
    }
    
    /*
    *   public void removeUser(InventoryViewItemVO InventoryViewItemVO){
        observableList.remove(InventoryViewItemVO);
    }*/

    public Node createPage(int pageIndex) {

        int fromIndex = pageIndex * rowsPerPage;
        int toIndex = Math.min(fromIndex + rowsPerPage, observableList.size());
        observableListtemp = FXCollections.observableList(observableList.subList(fromIndex, toIndex));
        final TreeItem<InventoryViewItemVO> root = new RecursiveTreeItem<>(observableListtemp, RecursiveTreeObject::getChildren);
        this.setRoot(root);
        this.setStyle("-fx-border-color: transparent; -fx-padding: 0; -fx-background-color: transparent");


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

        return new BorderPane(this);
    }


    private class ImageCell extends JFXTreeTableCell {
        private CircleImageView civ = new CircleImageView();

        @Override
        public void updateItem(Object item,boolean empty){
            super.updateItem(item,empty);
            if(empty){
                setGraphic(null);
            }else{
                civ.setImage((Image)item);
                civ.setRadius(17);
                civ.setTranslateY(-8);
                setGraphic(civ);

            }
        }
    }


    private class TokenCell extends JFXTreeTableCell<InventoryViewItemVO,Integer> {
        private TokenLabel tl = new TokenLabel("");

        @Override
        public void updateItem(Integer item,boolean empty){
            super.updateItem(item,empty);
            if(empty){
                setGraphic(null);
            }else{
                tl.setColor(Paint.valueOf("#AA11F2"));
                tl.setText(Integer.toString(item));
                setGraphic(tl);
            }
        }
    }
}
