package ui.accountantui;

import ui.managerui.navigation.ChangePaneLabel;
import ui.util.RefreshablePane;

public class PaymentListPaneLabel extends ChangePaneLabel {
    @Override
    public RefreshablePane getPane() {
        return new MyPaymentReceiptListPane();
    }
}
