package ui.inventoryui;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import vo.inventoryVO.uiReceipt.DamageuiGoodsItemVO;

import java.io.IOException;

public class DamageListItemPane extends AnchorPane {

    DamageuiGoodsItemVO itemVO;

    public DamageListItemPane(DamageuiGoodsItemVO itemVO) {
        super();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/inventoryui/damageItemPane.fxml"));
            fxmlLoader.setRoot(this);
            fxmlLoader.setController(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }


        this.itemVO = itemVO;
    }
}
