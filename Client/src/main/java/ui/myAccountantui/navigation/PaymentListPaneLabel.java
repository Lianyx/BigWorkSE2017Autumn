package ui.myAccountantui.navigation;

import ui.managerui.common.navigation.ChangePaneLabel;
import ui.myAccountantui.MyPaymentReceiptListPane;
import ui.util.Refreshable;

public class PaymentListPaneLabel extends ChangePaneLabel {
    @Override
    public Refreshable getPane() {
        return new MyPaymentReceiptListPane();
    }
}
