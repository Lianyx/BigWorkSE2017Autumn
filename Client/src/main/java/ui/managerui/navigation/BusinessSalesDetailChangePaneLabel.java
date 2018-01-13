package ui.managerui.navigation;

import ui.managerui.businessSalesDetail.BusinessSalesListPane;
import ui.util.RefreshablePane;

public class BusinessSalesDetailChangePaneLabel extends ChangePaneLabel {
    @Override
    public RefreshablePane getPane() {
        return new BusinessSalesListPane();
    }
}
