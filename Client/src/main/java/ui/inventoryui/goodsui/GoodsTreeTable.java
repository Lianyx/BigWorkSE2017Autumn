package ui.inventoryui.goodsui;

import blService.blServiceFactory.ServiceFactory_Stub;
import blService.goodsblService.GoodsblService;
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
import javafx.scene.control.*;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Paint;
import javafx.util.Callback;
import javafx.util.Duration;
import ui.util.*;
import vo.inventoryVO.GoodSearchVO;
import vo.inventoryVO.GoodsVO;

import java.util.Set;

public class GoodsTreeTable extends ReceiptTreeTable<GoodsVO> {
    private GoodsblService goodsblService;
    private GoodSearchVO goodSearchVO;

    public GoodsTreeTable() {
        super();
        rowsPerPage = 7;
        goodsblService = ServiceFactory_Stub.getService(GoodsblService.class.getName());

        JFXTreeTableColumn<GoodsVO,Boolean> choose = new JFXTreeTableColumn("  ");
        choose.setPrefWidth(40);
        columnDecorator.setupCellValueFactory(choose, s -> s.selectedProperty().asObject());
        choose.setCellFactory(t->new ChooseCell<GoodsVO>(chosenItem));


        JFXTreeTableColumn<GoodsVO, String> goodName = new JFXTreeTableColumn("GoodName");
        goodName.setPrefWidth(60);
        columnDecorator.setupCellValueFactory(goodName,s->new ReadOnlyObjectWrapper<>(s.getGoodName()));
        goodName.setCellFactory(t->new SearchableStringCell<>(keyword));

        JFXTreeTableColumn<GoodsVO, String> goodId = new JFXTreeTableColumn("GoodId");
        goodId.setPrefWidth(120);
        columnDecorator.setupCellValueFactory(goodId,s->new ReadOnlyObjectWrapper<>(s.getId()));

     /*   JFXTreeTableColumn<GoodsVO, String> goodType = new JFXTreeTableColumn("GoodType");
        goodType.setPrefWidth(125);
        columnDecorator.setupCellValueFactory(goodType,s->new ReadOnlyObjectWrapper(s.getGoodType()));
        goodType.setCellFactory(new Callback<TreeTableColumn<GoodsVO, String>, TreeTableCell<GoodsVO, String>>() {
            @Override
            public TreeTableCell<GoodsVO, String> call(TreeTableColumn<GoodsVO, String> param) {
                return new ButtonCell() {
                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if(item!=null){
                            setButtonStyle(UserCategory.color.get(item));
                        }
                    }
                };
            }
        });*/
    }

    @Override
    public void delete(Pagination p) {

    }
}
