package ui.accountantui;

import ui.managerui.navigation.ChangePaneLabel;
import ui.accountantui.MyPaymentReceiptListPane;
import ui.util.Refreshable;

public class PaymentListPaneLabel extends ChangePaneLabel {
    @Override
    public Refreshable getPane() {
        return new MyPaymentReceiptListPane();
    }
}
