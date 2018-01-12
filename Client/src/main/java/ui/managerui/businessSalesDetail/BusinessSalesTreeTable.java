package ui.managerui.businessSalesDetail;

import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableRow;
import javafx.beans.property.StringProperty;
import ui.managerui.common.treeTableRelated.MyTreeTableBorderPane;
import ui.managerui.common.treeTableRelated.SearchableStringColumn;
import vo.ListGoodsItemVO;

import java.util.Set;

public class BusinessSalesTreeTable extends MyTreeTableBorderPane<ListGoodsItemVO>{
    public BusinessSalesTreeTable(StringProperty keywordProperty) {
        // TODO 加上时间信息
        JFXTreeTableColumn<ListGoodsItemVO, String> dateColumn = new SearchableStringColumn<>("时间", 100, keywordProperty, l -> l.getTime().toString());
        JFXTreeTableColumn<ListGoodsItemVO, String> nameColumn = new SearchableStringColumn<>("商品名", 50, keywordProperty, ListGoodsItemVO::getGoodsName);
        JFXTreeTableColumn<ListGoodsItemVO, String> typeColumn = new SearchableStringColumn<>("型号", 50, keywordProperty, ListGoodsItemVO::getType);
        JFXTreeTableColumn<ListGoodsItemVO, String> numColumn = new SearchableStringColumn<>("数量", 50, keywordProperty, l -> String.valueOf(l.getGoodsNum()));
        JFXTreeTableColumn<ListGoodsItemVO, String> priceColumn = new SearchableStringColumn<>("单价", 50, keywordProperty, l -> String.valueOf(l.getPrice()));
        JFXTreeTableColumn<ListGoodsItemVO, String> totalColumn = new SearchableStringColumn<>("总价", 50, keywordProperty, l -> String.valueOf(l.getSum()));

        myTreeTable.getColumns().addAll(dateColumn, nameColumn, typeColumn, numColumn, priceColumn, totalColumn);
    }

    @Override
    protected void clickTwiceAftermath(JFXTreeTableRow<ListGoodsItemVO> row) {
    }
}
