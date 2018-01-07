package ui.stockui;

import blService.blServiceFactory.ServiceFactory;
import blService.blServiceFactory.ServiceFactory_Stub;
import blService.stockblService.StockblService;
import com.jfoenix.controls.*;
import com.jfoenix.controls.cells.editors.IntegerTextFieldEditorBuilder;
import com.jfoenix.controls.cells.editors.base.GenericEditableTreeTableCell;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
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
import vo.ListGoodsItemVO;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

public class StockListItemTreeTable extends JFXTreeTableView<ListGoodsItemVO> {
    private ObservableList<ListGoodsItemVO> observableList = FXCollections.observableArrayList();

    private StackPane mainpane;

    private SimpleDoubleProperty sum = new SimpleDoubleProperty(0);
    public StockListItemTreeTable() {
        super();


        mainpane = PaneFactory.getMainPane();

        /**
          decorator，这个主要就是setValuefactory方便
        **/
        ColumnDecorator columnDecorator = new ColumnDecorator();

        JFXTreeTableColumn<ListGoodsItemVO, Integer> goodsID = new JFXTreeTableColumn<>("GoodsID");
        goodsID.setPrefWidth(115.5);
        columnDecorator.setupCellValueFactory(goodsID, l -> l.goodsIdProperty().asObject());


        JFXTreeTableColumn<ListGoodsItemVO, String> goodsName = new JFXTreeTableColumn<>("GoodsName");
        goodsName.setPrefWidth(115.5);
        columnDecorator.setupCellValueFactory(goodsName, ListGoodsItemVO::goodsNameProperty);


        JFXTreeTableColumn<ListGoodsItemVO, Integer> goodsNum = new JFXTreeTableColumn<>("Num");
        goodsNum.setPrefWidth(115.5);
        columnDecorator.setupCellValueFactory(goodsNum, l -> l.goodsNumProperty().asObject());
        goodsNum.setCellFactory((TreeTableColumn<ListGoodsItemVO, Integer> param) -> { return new GenericEditableTreeTableCell<>(new IntegerTextFieldEditorBuilder());
        });
        /**
        number的textfield
        **/
        goodsNum.setOnEditCommit((TreeTableColumn.CellEditEvent<ListGoodsItemVO, Integer> t) -> {
            t.getTreeTableView().getTreeItem(t.getTreeTablePosition().getRow()).getValue().goodsNumProperty().set(t.getNewValue());
        });


        JFXTreeTableColumn<ListGoodsItemVO, Double> price = new JFXTreeTableColumn<>("Price");
        price.setPrefWidth(115.5);
        columnDecorator.setupCellValueFactory(price, l -> l.priceProperty().asObject());


        this.setRowFactory(tableView -> {
            JFXTreeTableRow row = new JFXTreeTableRow();
            row.setStyle("-fx-border-color: rgb(233,237,239); -fx-border-width: 0.3;");



            row.setOnMouseClicked((MouseEvent event) -> {
                ListGoodsItemVO listGoodsItemVO = (ListGoodsItemVO) row.getTreeItem().getValue();


                /**
                 * 右键时候，弹出popup，里面有view和delete，getListview命名不好，就是弹窗中listview里面的view，getlistdelete同样
                 **/
                if (event.getButton() == MouseButton.SECONDARY) {
                    ListPopup listPopup = new ListPopup();
                    JFXPopup popup = new JFXPopup(listPopup);
                    listPopup.getListview().setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            /**
                            进入listpane
                            **/
                            StockListItemPane stockListItemPane = new StockListItemPane(listGoodsItemVO, observableList);
                            JFXDialog dialog = new JFXDialog(mainpane, stockListItemPane, JFXDialog.DialogTransition.CENTER);
                            stockListItemPane.setDialog(dialog);
                            dialog.show();
                            popup.hide();
                        }
                    });
                    listPopup.getListdelete().setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            observableList.remove(listGoodsItemVO);
                        }
                    });
                    popup.show(row, JFXPopup.PopupVPosition.TOP, JFXPopup.PopupHPosition.RIGHT);
                }



                if(event.getClickCount() == 2){
                    StockListItemPane stockListItemPane = new StockListItemPane(listGoodsItemVO, observableList);
                    JFXDialog dialog = new JFXDialog(mainpane, stockListItemPane, JFXDialog.DialogTransition.CENTER);
                    stockListItemPane.setDialog(dialog);
                    dialog.show();
                }

            });

            /**
            row阴影效果
            **/
            row.selectedProperty().addListener(e -> {
                if (row.isSelected()) {
                    row.toFront();
                } else {
                    row.setEffect(null);
                }
            });
            return row;
        });


        observableList.addListener(new ListChangeListener<ListGoodsItemVO>() {
            @Override
            public void onChanged(Change<? extends ListGoodsItemVO> c) {
                sum.set(0);
                double s = 0;
                for(ListGoodsItemVO listGoodsItemVO:observableList){
                      s+=listGoodsItemVO.getSum();
                }
                sum.set(s);
            }
        });


        TreeItem<ListGoodsItemVO> root = new RecursiveTreeItem<>(observableList, RecursiveTreeObject::getChildren);
        this.setRoot(root);
        this.setEditable(true);
        this.setShowRoot(false);
        this.getColumns().setAll(goodsID, goodsName, goodsNum, price);

    }


    public void setList(ArrayList<ListGoodsItemVO> goods) {
        observableList.setAll(goods);
    }

    public void addGood(ListGoodsItemVO good) {
        observableList.add(good);
    }

    /**
     * @Author: Lin Yuchao
     * @Attention 转成arraylist
     * @Param:
     * @Return:
    **/
    public ArrayList<ListGoodsItemVO> getList(){
        ArrayList<ListGoodsItemVO> arrayList = new ArrayList<>();
        observableList.forEach(i->arrayList.add(i));
        return arrayList;
    }

    public double getSum() {
        return sum.get();
    }

    public SimpleDoubleProperty sumProperty() {
        return sum;
    }
}
