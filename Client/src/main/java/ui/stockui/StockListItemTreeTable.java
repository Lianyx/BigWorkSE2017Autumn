package ui.stockui;

import blService.stockblService.StockblService;
import com.jfoenix.controls.*;
import com.jfoenix.controls.cells.editors.IntegerTextFieldEditorBuilder;
import com.jfoenix.controls.cells.editors.TextFieldEditorBuilder;
import com.jfoenix.controls.cells.editors.base.GenericEditableTreeTableCell;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import ui.userui.usermanagerui.BoardController;
import ui.util.ListPopup;
import vo.ListGoodsItemVO;
import vo.UserListVO;

import java.util.List;
import java.util.Set;
import java.util.function.Function;

public class StockListItemTreeTable extends JFXTreeTableView<ListGoodsItemVO> {
    private ObservableList<ListGoodsItemVO> observableList = FXCollections.observableArrayList();

    StockblService stockblService;
    BoardController boardController;
    StackPane mainpane;

    public StockListItemTreeTable(){
        super();



        JFXTreeTableColumn<ListGoodsItemVO,Integer> goodsID = new JFXTreeTableColumn<>("GoodsID");
        goodsID.setPrefWidth(97);
        setupCellValueFactory(goodsID,l->l.goodsIdProperty().asObject());



        JFXTreeTableColumn<ListGoodsItemVO, String> goodsName = new JFXTreeTableColumn<>("GoodsName");
        goodsName.setPrefWidth(97);
        setupCellValueFactory(goodsName,ListGoodsItemVO::goodsNameProperty);



        JFXTreeTableColumn<ListGoodsItemVO, Integer> goodsNum = new JFXTreeTableColumn<>("Num");
        goodsNum.setPrefWidth(97);
        setupCellValueFactory(goodsNum,l->l.goodsNumProperty().asObject());
        goodsNum.setCellFactory((TreeTableColumn<ListGoodsItemVO, Integer> param) -> {
            return new GenericEditableTreeTableCell<>(
                    new IntegerTextFieldEditorBuilder());
        });
        goodsNum.setOnEditCommit((TreeTableColumn.CellEditEvent<ListGoodsItemVO, Integer> t) -> {
            t.getTreeTableView()
                    .getTreeItem(t.getTreeTablePosition()
                            .getRow())
                    .getValue().goodsNumProperty().set(t.getNewValue());
        });





        JFXTreeTableColumn<ListGoodsItemVO, Integer> price = new JFXTreeTableColumn<>("Price");
        price.setPrefWidth(96);
        setupCellValueFactory(price,l->l.priceProperty().asObject());


        this.setRowFactory(tableView->{
            JFXTreeTableRow row=new JFXTreeTableRow();
            row.setStyle("-fx-border-color: rgb(233,237,239); -fx-border-width: 0.3;");
            row.setOnMouseClicked((MouseEvent event) -> {
                if(event.getButton() == MouseButton.SECONDARY){
                    ListPopup listPopup = new ListPopup();
                    JFXPopup popup = new JFXPopup(listPopup);
                    listPopup.getListview().setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            StockListItemPane stockListItemPane = new StockListItemPane((ListGoodsItemVO)row.getTreeItem().getValue());
                            JFXDialog dialog = new JFXDialog(mainpane, stockListItemPane, JFXDialog.DialogTransition.CENTER);
                            dialog.show();
                            popup.hide();
                        }
                    });
                    popup.show(row, JFXPopup.PopupVPosition.TOP,JFXPopup.PopupHPosition.RIGHT);
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

        TreeItem<ListGoodsItemVO> root = new RecursiveTreeItem<>(observableList, RecursiveTreeObject::getChildren);
        this.setRoot(root);
        this.setEditable(true);
        this.setShowRoot(false);
        this.getColumns().setAll(goodsID,goodsName, goodsNum,price);

      //  observableList.addListener(setupChanger(this));

    }

    public void setStockblService(StockblService stockblService) {
        this.stockblService = stockblService;
    }

    public void setBoardController(BoardController boardController) {
        this.boardController = boardController;
    }

    public void setMainpane(StackPane mainpane) {
        this.mainpane = mainpane;
    }

    public void setList(Set<ListGoodsItemVO> goods){
        observableList.setAll(goods);
    }

    public void removeGood(ListGoodsItemVO good){
        observableList.remove(good);
    }

    public void addGood(ListGoodsItemVO good){
        observableList.add(good);
    }
    private <T> void setupCellValueFactory(JFXTreeTableColumn<ListGoodsItemVO, T> column, Function<ListGoodsItemVO, ObservableValue<T>> mapper) {
        column.setCellValueFactory((TreeTableColumn.CellDataFeatures<ListGoodsItemVO, T> param) -> {
            if (column.validateValue(param)) {
                return mapper.apply(param.getValue().getValue());
            } else {
                return column.getComputedValue(param);
            }
        });
    }
}
