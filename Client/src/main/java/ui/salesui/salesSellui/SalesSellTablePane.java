package ui.salesui.salesSellui;

import com.jfoenix.controls.JFXTreeTableRow;
import javafx.beans.property.StringProperty;
import ui.salesui.SalesTreeTable;
import ui.util.Refreshable;
import vo.receiptVO.SalesRetListVO;
import vo.receiptVO.SalesSellListVO;

import java.util.Set;

public class SalesSellTablePane  extends SalesTreeTable<SalesSellListVO> {
    public SalesSellTablePane(Set<SalesSellListVO> chosenItems, StringProperty keywordProperty) {
        super(chosenItems, keywordProperty);
    }

    @Override
    protected void clickTwiceAftermath(JFXTreeTableRow<SalesSellListVO> row) {
        ((Refreshable)row.getTreeItem().getValue().toVO().getDetailPane()).refresh(true);

    }
}