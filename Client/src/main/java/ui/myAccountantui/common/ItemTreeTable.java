package ui.myAccountantui.common;

import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.ListChangeListener;
import javafx.scene.control.TreeItem;
import ui.managerui.common.treeTableRelated.ChooseColumn;
import ui.managerui.common.treeTableRelated.ImageColumn;
import ui.managerui.common.treeTableRelated.SearchableStringColumn;
import ui.util.ButtonCell;
import util.UserCategory;
import vo.ListGoodsItemVO;
import vo.UserListVO;
import vo.billReceiptVO.CashItemVO;

public class ItemTreeTable extends MyListItemTablePane<ListGoodsItemVO>{
    private SimpleDoubleProperty sum = new SimpleDoubleProperty(0);

    public ItemTreeTable() {
        super();
        observableList.addListener(new ListChangeListener<ListGoodsItemVO>() {
            @Override
            public void onChanged(Change<? extends ListGoodsItemVO> c) {
                sum.set(0);
                for(ListGoodsItemVO listGoodsItemVO:c.getList()){
                    sum.add(listGoodsItemVO.getSum());
                }

            }
        });
        JFXTreeTableColumn<ListGoodsItemVO, String> goodsid = new JFXTreeTableColumn<>("ID");
        goodsid.setCellValueFactory(p->new ReadOnlyObjectWrapper<>(p.getValue().getValue().getGoodsId()));
        goodsid.setPrefWidth(100);
        JFXTreeTableColumn<ListGoodsItemVO, String> name = new JFXTreeTableColumn<>("商品名");
        name.setCellValueFactory(p->new ReadOnlyObjectWrapper<>(p.getValue().getValue().getGoodsId()));
        name.setPrefWidth(100);
        JFXTreeTableColumn<ListGoodsItemVO, Double> price = new JFXTreeTableColumn<>("价格");
        price.setCellValueFactory(p->new ReadOnlyObjectWrapper<>(p.getValue().getValue().getPrice()));
        price.setPrefWidth(100);

        this.getColumns().setAll(goodsid,name,price);
        TreeItem<ListGoodsItemVO> root = new RecursiveTreeItem<>(observableList, RecursiveTreeObject::getChildren);
        this.setRoot(root);


    }

    @Override
    public void initial(ListGoodsItemVO vo) {
        myListItemPane = new ListItemPane(vo,this);
    }

    public double getSum() {
        return sum.get();
    }

    public SimpleDoubleProperty sumProperty() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum.set(sum);
    }


}
