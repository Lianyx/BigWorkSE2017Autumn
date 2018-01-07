package ui.inventoryui;

import blService.inventoryblService.InventoryShowblService;
import com.jfoenix.controls.JFXButton;
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
import javafx.scene.Node;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableCell;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;
import javafx.util.Callback;
import javafx.util.Duration;
import ui.util.BoardController;
import ui.util.CircleImageView;
import ui.util.TokenLabel;
import vo.inventoryVO.InventoryCheckItemVO;

import java.util.Set;

public class InventoryCheckTreeTable extends JFXTreeTableView<InventoryCheckItemVO> {
    private ObservableList<InventoryCheckItemVO> observableList = FXCollections.observableArrayList();
    private ObservableList<InventoryCheckItemVO> observableListfilter = observableList;
    private ObservableList<InventoryCheckItemVO> observableListtemp;
    private int rowsPerPage = 7;
    private BoardController boardController;
    private InventoryShowblService showblService;
    private StackPane mainpane;
    /*
    private UserSearchVO userSearchVO;
    */

    public ObservableList<InventoryCheckItemVO> getObservableList() {
        return observableListfilter;
    }

    public int getRowsPerPage() {
        return rowsPerPage;
    }
    
    public InventoryCheckTreeTable(InventoryShowblService showblService,BoardController boardController,StackPane mainpane){
        super();
        this.showblService = showblService;
        this.boardController = boardController;
        this.mainpane = mainpane;

        JFXTreeTableColumn image = new JFXTreeTableColumn("  ");
        image.setPrefWidth(50);
        Callback<TreeTableColumn, TreeTableCell> imageCellFactory = (TreeTableColumn p) -> new ImageCell();
        image.setCellValueFactory(new TreeItemPropertyValueFactory<>("image"));
        image.setCellFactory(imageCellFactory);

        JFXTreeTableColumn<InventoryCheckItemVO, String> goodName = new JFXTreeTableColumn("商品名称");
        goodName.setPrefWidth(80);
        goodName.setCellValueFactory((TreeTableColumn.CellDataFeatures<InventoryCheckItemVO, String> param) -> {
            if (goodName.validateValue(param)) {
                return new ReadOnlyObjectWrapper(param.getValue().getValue().getGoodName());
            } else {
                return goodName.getComputedValue(param);
            }
        });

        JFXTreeTableColumn<InventoryCheckItemVO, String> goodType = new JFXTreeTableColumn("商品类型");
        goodType.setPrefWidth(125);
        goodType.setCellValueFactory((TreeTableColumn.CellDataFeatures<InventoryCheckItemVO, String> param) -> {
            if (goodType.validateValue(param)) {
                return new ReadOnlyObjectWrapper(param.getValue().getValue().getGoodType());
            } else {
                return goodType.getComputedValue(param);
            }
        });
        Callback<TreeTableColumn<InventoryCheckItemVO, String>, TreeTableCell<InventoryCheckItemVO, String>> typeCellFactory = (TreeTableColumn<InventoryCheckItemVO, String> p) -> new ButtonCell();
        goodType.setCellFactory(typeCellFactory);

        JFXTreeTableColumn<InventoryCheckItemVO, Integer> inventoryNum = new JFXTreeTableColumn("库存数量");
        inventoryNum.setPrefWidth(150);
        inventoryNum.setCellValueFactory((TreeTableColumn.CellDataFeatures<InventoryCheckItemVO, Integer> param) -> {
            if (inventoryNum.validateValue(param)) {
                return new ReadOnlyObjectWrapper(param.getValue().getValue().getInventoryNum());
            } else {
                return inventoryNum.getComputedValue(param);
            }
        });
        Callback<TreeTableColumn<InventoryCheckItemVO, Integer>, TreeTableCell<InventoryCheckItemVO, Integer>> tokenCellFactory = (TreeTableColumn<InventoryCheckItemVO, Integer> p) -> new TokenCell();
        inventoryNum.setCellFactory(tokenCellFactory);

        JFXTreeTableColumn<InventoryCheckItemVO, Double> avePrice = new JFXTreeTableColumn("库存均价");
        avePrice.setPrefWidth(150);
        avePrice.setCellValueFactory((TreeTableColumn.CellDataFeatures<InventoryCheckItemVO, Double> param) -> {
            if (avePrice.validateValue(param)) {
                return new ReadOnlyObjectWrapper(param.getValue().getValue().getAvePrice());
            } else {
                return avePrice.getComputedValue(param);
            }
        });

        JFXTreeTableColumn<InventoryCheckItemVO, String> stockOutDate = new JFXTreeTableColumn("出厂日期");
        stockOutDate.setPrefWidth(125);
        stockOutDate.setCellValueFactory((TreeTableColumn.CellDataFeatures<InventoryCheckItemVO, String> param) -> {
            if (stockOutDate.validateValue(param)) {
                return new ReadOnlyObjectWrapper(param.getValue().getValue().getStockOutDate());
            } else {
                return stockOutDate.getComputedValue(param);
            }
        });


        JFXTreeTableColumn<InventoryCheckItemVO, String> batch = new JFXTreeTableColumn("批次");
        batch.setPrefWidth(125);
        batch.setCellValueFactory((TreeTableColumn.CellDataFeatures<InventoryCheckItemVO, String> param) -> {
            if (batch.validateValue(param)) {
                return new ReadOnlyObjectWrapper(param.getValue().getValue().getBatch());
            } else {
                return batch.getComputedValue(param);
            }
        });

        JFXTreeTableColumn<InventoryCheckItemVO, String> batchNum = new JFXTreeTableColumn("批号");
        batchNum.setPrefWidth(125);
        batchNum.setCellValueFactory((TreeTableColumn.CellDataFeatures<InventoryCheckItemVO, String> param) -> {
            if (batchNum.validateValue(param)) {
                return new ReadOnlyObjectWrapper(param.getValue().getValue().getBatchNum());
            } else {
                return batchNum.getComputedValue(param);
            }
        });


        setCurrentItemsCount(rowsPerPage);
        this.setShowRoot(false);
        
        this.getColumns().addAll(image,goodName,goodType,inventoryNum,avePrice,stockOutDate,batch,batchNum);
        
    }
    
    public void setInventoryCheck(Set<InventoryCheckItemVO> checkSet){ observableList.setAll(checkSet);}

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

    public Node createPage(int pageIndex) {

/*
        if(userSearchVO.getUserCategory()!=null)
            observableListfilter=observableList.filtered(t->{
                return userSearchVO.getUserCategory()==t.getUserCategory();
            });
        else
         observableListfilter=observableList;

        */

        int fromIndex = pageIndex * rowsPerPage;
        int toIndex = Math.min(fromIndex + rowsPerPage, observableListfilter.size());
        observableListtemp = FXCollections.observableList(observableListfilter.subList(fromIndex, toIndex));
        final TreeItem<InventoryCheckItemVO> root = new RecursiveTreeItem<>(observableListtemp, RecursiveTreeObject::getChildren);
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

    private class ButtonCell extends JFXTreeTableCell<InventoryCheckItemVO,String> {
        private JFXButton civ = new JFXButton("");

        @Override
        public void updateItem(String item,boolean empty){
            super.updateItem(item,empty);
            if(empty){
                setGraphic(null);
            }else{
                setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                civ.setText(item);
                civ.setStyle("-fx-background-color: linear-gradient(to top right , rgba(170,17,242,0.7),rgba(218,76,238,0.7)); -fx-text-fill: white;-fx-background-radius: 10;");
                setGraphic(civ);
            }
        }
    }

    private class TokenCell extends JFXTreeTableCell<InventoryCheckItemVO,Integer> {
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
