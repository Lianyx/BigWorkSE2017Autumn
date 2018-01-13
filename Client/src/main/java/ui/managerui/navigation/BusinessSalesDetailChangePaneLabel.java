package ui.managerui.navigation;

import ui.managerui.businessSalesDetail.BusinessSalesListPane;
import ui.util.Refreshable;

public class BusinessSalesDetailChangePaneLabel extends ChangePaneLabel {
    @Override
    public Refreshable getPane() {
        return new BusinessSalesListPane();
    }
}
