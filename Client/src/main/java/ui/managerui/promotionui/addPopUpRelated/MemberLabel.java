package ui.managerui.promotionui.addPopUpRelated;

import ui.managerui.promotionui.promotionDetailPane.MemberPromotionDetailPane;
import ui.util.Refreshable;

public class MemberLabel extends addButton {
    public MemberLabel() {
        setText("会员促销");
    }

    @Override
    public Refreshable getPane() {
        return new MemberPromotionDetailPane();
    }
}
