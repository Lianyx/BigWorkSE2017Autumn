package ui.inventoryui.myGoodsClassificationUI.label;

import ui.common.dialog.MyOneButtonDialog;
import ui.common.mixer.FXMLLoadableMixer;
import ui.inventoryui.myGoodsClassificationUI.MyGoodsDetailPane;
import vo.inventoryVO.MyGoodsClassificationVO;

public class AddGoodsLabel extends GoodsPopUpListLabel implements FXMLLoadableMixer {
    private MyGoodsClassificationVO goodsClassificationVO;

    public AddGoodsLabel(MyGoodsClassificationVO goodsClassificationVO) {
        load();
        this.goodsClassificationVO = goodsClassificationVO;
    }

    @Override
    public String publicGetURL() {
        return "/inventoryui/goodui/myAddGoodsLabel.fxml";
    }

    @Override
    public void clickAction() {
        System.out.println("addGoods");
        if (goodsClassificationVO.getChildren() != null && !goodsClassificationVO.getChildren().isEmpty()) {
            new MyOneButtonDialog("当前商品分类下存在商品子分类，不可添加商品").show();
        } else {
            new MyGoodsDetailPane(goodsClassificationVO.getId()).refresh(true);
        }
    }
}
