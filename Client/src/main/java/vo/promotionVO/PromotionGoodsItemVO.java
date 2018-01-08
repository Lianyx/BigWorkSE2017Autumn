package vo.promotionVO;

import businesslogic.checkbl.MyServiceFactory;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import po.promotionPO.PromotionGoodsItemPO;
import vo.inventoryVO.GoodsVO;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.ExportException;

public class PromotionGoodsItemVO extends RecursiveTreeObject<PromotionGoodsItemVO> {
    private String id;
    private String name;
    private double unitPrice;
    private IntegerProperty num;

    public PromotionGoodsItemVO() {
    }

    public PromotionGoodsItemVO(PromotionGoodsItemPO promotionGoodsItemPO) {
        id = promotionGoodsItemPO.getId();
        num = new SimpleIntegerProperty(promotionGoodsItemPO.getNum());
        try {
            GoodsVO goodsVO = MyServiceFactory.getGoodsSearchInfo().getGoodById(id);
            name = goodsVO.getGoodName();
            unitPrice = goodsVO.getSalePrice();
        } catch (NotBoundException | MalformedURLException | RemoteException e) {
            // 不管了，就这么写了…
            e.printStackTrace();
            name = "连接错误";
            unitPrice = -99999;
        }
    }

    public PromotionGoodsItemVO(String id, String name, int unitPrice, IntegerProperty num) {
        this.id = id;
        this.name = name;
        this.unitPrice = unitPrice;
        this.num = num;
    }

    public PromotionGoodsItemPO toPO() {
        return new PromotionGoodsItemPO(id, num.get());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getNum() {
        return num.get();
    }

    public IntegerProperty numProperty() {
        return num;
    }

    public void setNum(int num) {
        this.num.set(num);
    }
}
