package ui.myAccountantui.common;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.ListChangeListener;
import vo.ListGoodsItemVO;

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
