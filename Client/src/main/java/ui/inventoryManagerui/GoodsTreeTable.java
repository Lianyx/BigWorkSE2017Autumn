package ui.inventoryManagerui;

import com.jfoenix.controls.*;
import com.jfoenix.controls.cells.editors.base.JFXTreeTableCell;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
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
import javafx.scene.paint.Paint;
import javafx.util.Callback;
import javafx.util.Duration;
import ui.util.BoardController;
import ui.util.IconButton;
import ui.util.TokenLabel;
import ui.util.CircleImageView;
import vo.inventoryVO.GoodsVO;

import java.util.Set;

public class GoodsTreeTable extends JFXTreeTableView<GoodsVO> {
    private ObservableList<GoodsVO> observableList = FXCollections.observableArrayList();
    private ObservableList<GoodsVO> observableListtemp;
    private int rowsPerPage = 7;
    private BoardController boardController;

    public ObservableList<GoodsVO> getObservableList() {
        return observableList;
    }

    public int getRowsPerPage() {
        return rowsPerPage;
    }

    public void setBoardController(BoardController boardController) {
        this.boardController = boardController;
    }

    public GoodsTreeTable(){
        super();
        JFXTreeTableColumn<GoodsVO,Boolean> choose = new JFXTreeTableColumn("  ");
        choose.setPrefWidth(40);
        Callback<TreeTableColumn<GoodsVO,Boolean>, TreeTableCell<GoodsVO,Boolean>> chooseCellFactory = (TreeTableColumn<GoodsVO,Boolean> p) -> new ChooseBoxCell();
        choose.setCellValueFactory((TreeTableColumn.CellDataFeatures<GoodsVO, Boolean> param) -> {
            if (choose.validateValue(param)) {
                return param.getValue().getValue().selectedProperty();
            } else {
                return choose.getComputedValue(param);
            }
        });
        choose.setCellFactory(chooseCellFactory);


        JFXTreeTableColumn image = new JFXTreeTableColumn("  ");
        image.setPrefWidth(50);
        Callback<TreeTableColumn, TreeTableCell> imageCellFactory = (TreeTableColumn p) -> new ImageCell();
        image.setCellValueFactory(new TreeItemPropertyValueFactory<>("image"));
        image.setCellFactory(imageCellFactory);


        JFXTreeTableColumn<GoodsVO, String> goodName = new JFXTreeTableColumn("商品名称");
        goodName.setPrefWidth(80);
        goodName.setCellValueFactory((TreeTableColumn.CellDataFeatures<GoodsVO, String> param) -> {
            if (goodName.validateValue(param)) {
                return new ReadOnlyObjectWrapper(param.getValue().getValue().getGoodName());
            } else {
                return goodName.getComputedValue(param);
            }
        });

        JFXTreeTableColumn<GoodsVO, String> goodId = new JFXTreeTableColumn("商品编号");
        goodId.setPrefWidth(80);
        goodId .setCellValueFactory((TreeTableColumn.CellDataFeatures<GoodsVO, String> param) -> {
            if (goodId .validateValue(param)) {
                return new ReadOnlyObjectWrapper(param.getValue().getValue().getId());
            } else {
                return goodId .getComputedValue(param);
            }
        });

        JFXTreeTableColumn<GoodsVO, String> goodType = new JFXTreeTableColumn("商品型号");
        goodType.setPrefWidth(80);
        goodType.setCellValueFactory((TreeTableColumn.CellDataFeatures<GoodsVO, String> param) -> {
            if (goodType.validateValue(param)) {
                return new ReadOnlyObjectWrapper(param.getValue().getValue().getGoodType());
            } else {
                return goodType.getComputedValue(param);
            }
        });
        Callback<TreeTableColumn<GoodsVO, String>, TreeTableCell<GoodsVO, String>> typeCellFactory = (TreeTableColumn<GoodsVO, String> p) -> new ButtonCell();
        goodType.setCellFactory(typeCellFactory);
        

        JFXTreeTableColumn<GoodsVO, Integer> inventoryNum = new JFXTreeTableColumn("库存数量");
        inventoryNum.setPrefWidth(80);
        inventoryNum .setCellValueFactory((TreeTableColumn.CellDataFeatures<GoodsVO, Integer> param) -> {
            if (inventoryNum .validateValue(param)) {
                return new ReadOnlyObjectWrapper(param.getValue().getValue().getInventoryNum());
            } else {
                return inventoryNum.getComputedValue(param);
            }
        });
        Callback<TreeTableColumn<GoodsVO, Integer>, TreeTableCell<GoodsVO, Integer>> tokenCellFactory = (TreeTableColumn<GoodsVO, Integer> p) -> new TokenCell();
        inventoryNum.setCellFactory(tokenCellFactory);

        JFXTreeTableColumn more = new JFXTreeTableColumn("");
        more.setPrefWidth(20);
        Callback<TreeTableColumn, TreeTableCell> moreCellFactory = (TreeTableColumn p) -> new MoreCell();
        more.setCellFactory(moreCellFactory);

        //双击操作
        this.setRowFactory(tableView->{
            JFXTreeTableRow row=new JFXTreeTableRow();
            row.setPrefHeight(55);
            row.setStyle("-fx-border-color: rgb(233,237,239); -fx-border-width: 0.3;");
            row.setOnMouseClicked((MouseEvent event) -> {
                if(event.getClickCount()==2){
                    try{
                        boardController.switchTo(new GoodDetailPane((GoodsVO) row.getTreeItem().getValue(),boardController));
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }

            });
            row.selectedProperty().addListener(e->{
                if(row.isSelected()){
                    row.toFront();
                }else{
                    row.setEffect(null);
                }
            });
            return row;
        });

        //并不知道这是干嘛的
        setCurrentItemsCount(rowsPerPage);
        this.setShowRoot(false);

        this.getColumns().addAll(choose,image,goodName,goodId,goodType,inventoryNum,more);


    }

    public void setGoods(Set<GoodsVO> goods){
        observableList.addAll(goods);
    }

    public void removeGood(GoodsVO goodsVO){
        observableList.remove(goodsVO);
    }

    //和分页有关的方法
    public Node createPage(int pageIndex) {

        int fromIndex = pageIndex * rowsPerPage;
        int toIndex = Math.min(fromIndex + rowsPerPage, observableList.size());
        observableListtemp = FXCollections.observableList(observableList.subList(fromIndex, toIndex));
        final TreeItem<GoodsVO> root = new RecursiveTreeItem<>(observableListtemp, RecursiveTreeObject::getChildren);
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

        timeline.play();
        return new BorderPane(this);
    }

    private class ChooseBoxCell extends JFXTreeTableCell<GoodsVO,Boolean> {
        private JFXCheckBox cb = new JFXCheckBox("");

        public ChooseBoxCell(){
            cb.setStyle("-jfx-checked-color: #DA4CEE; -jfx-unchecked-color:#78909C;");
            cb.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    if(getTreeTableRow()!=null&&getTreeTableRow().getItem()!=null){
                        GoodsVO GoodsVO=getTreeTableRow().getItem();
                        System.out.println(GoodsVO);
                        GoodsVO.setSelected(!GoodsVO.isSelected());
                        if(GoodsVO.isSelected()){
                            GoodsChosenItem.addItem(GoodsVO);
                        }else{
                            GoodsChosenItem.removeItem(GoodsVO);
                        }
                    }
                }
            });

        }

        @Override
        public void updateItem(Boolean item,boolean empty){
            super.updateItem(item,empty);

            if(empty){
                setGraphic(null);
            }else{
                setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                cb.setSelected(item);
                setGraphic(cb);
            }
        }
    }

    private class ImageCell extends JFXTreeTableCell {
       // private CircleImageView civ = new CircleImageView("/default/light.jpg");
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

    private class ButtonCell extends JFXTreeTableCell<GoodsVO,String> {
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

    private class TokenCell extends JFXTreeTableCell<GoodsVO,Integer> {
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

    private class MoreCell extends JFXTreeTableCell {
        private IconButton iconButton = new IconButton(MaterialDesignIcon.DOTS_HORIZONTAL);

        @Override
        public void updateItem(Object item,boolean empty){
            super.updateItem(item,empty);
            iconButton.setTranslateY(-8);
            setGraphic(iconButton);
        }
    }

}
