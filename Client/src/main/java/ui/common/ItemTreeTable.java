package ui.common;

import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.control.TreeItem;
import vo.ListGoodsItemVO;

public class ItemTreeTable extends MyListItemTablePane<ListGoodsItemVO>{
    private SimpleDoubleProperty sum = new SimpleDoubleProperty(0);




    public ItemTreeTable() {
        super();




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

    @Override
    public void refresh() {
        for(ListGoodsItemVO listGoodsItemVO : observableList){
            sum.set(listGoodsItemVO.getSum()+sum.get());
        }
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
