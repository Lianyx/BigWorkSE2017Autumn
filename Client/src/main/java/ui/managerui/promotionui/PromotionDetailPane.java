package ui.managerui.promotionui;

import javafx.scene.layout.StackPane;
import ui.managerui.common.Save;
import ui.util.BoardController;
import ui.util.Refreshable;

public abstract class PromotionDetailPane extends Refreshable {
//    public abstract void setPromotionVO(PromotionVO promotionVO);
    protected StackPane mainpane;
    protected BoardController boardController;

    public void setMainpane(StackPane mainpane) {
        this.mainpane = mainpane;
        Save.mainpane = mainpane;
    }

    public void setBoardController(BoardController boardController) {
        this.boardController = boardController;
    }
}
