package ui.inventoryui.myGoodsClassificationUI.label;

import ui.common.dialog.MyOneButtonDialog;
import ui.common.mixer.FXMLLoadableMixer;
import vo.inventoryVO.MyGoodsClassificationVO;

public class AddGoodsClassificationLabel extends GoodsPopUpListLabel implements FXMLLoadableMixer {
    private MyGoodsClassificationVO goodsClassificationVO;

    public AddGoodsClassificationLabel(MyGoodsClassificationVO goodsClassificationVO) {
        load();
        this.goodsClassificationVO = goodsClassificationVO;
    }

    @Override
    public String publicGetURL() {
        return "/inventoryui/goodui/myAddGoodsClassificationLabel.fxml";
    }

    @Override
    public void clickAction() {
//        System.out.println("addGoodsClassification");
        if (goodsClassificationVO.getGoods() != null && !goodsClassificationVO.getGoods().isEmpty()) {
            new MyOneButtonDialog("当前商品分类下有商品，不可添加商品分类").show();
        } else {
            new MyOneButtonDialog("增加商品分类的还没有做😅").show();
        }
    }
}
