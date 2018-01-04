package ui.inventoryManagerui.inventoryGiftReceiptui;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import vo.inventoryVO.uiReceipt.GiftuiGoodsItemVO;

import java.io.IOException;

public class GiftListItemPane extends AnchorPane {

    GiftuiGoodsItemVO itemVO;

    public GiftListItemPane(GiftuiGoodsItemVO itemVO) {
        super();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/inventoryui/giftItemPane.fxml"));
            fxmlLoader.setRoot(this);
            fxmlLoader.setController(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }


        this.itemVO = itemVO;
    }
}
