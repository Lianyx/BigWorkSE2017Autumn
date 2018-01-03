package vo.inventoryVO;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.image.Image;

import java.io.Serializable;

public class InventoryCheckItemVO extends RecursiveTreeObject<InventoryCheckItemVO> implements Serializable,Comparable<InventoryCheckItemVO>{
    /** 商品名 */
    private String goodName;
    /** 类型 */
    private String goodType;
    /** 库存数量 */
    private int inventoryNum;
    /** 每个商品价格 */
    private double avePrice;
    /**出场日期*/
    private String StockOutDate;
    /* 批次*/
    private String batch;
    /* 批号*/
    private String batchNum;

    private SimpleBooleanProperty selected=new SimpleBooleanProperty(false);

    private Image image = new Image("/default/light.jpg");

    public InventoryCheckItemVO(String goodName, String goodType, int inventoryNum, double avePrice, String date, String batch, String batchNum) {
        this.goodName = goodName;
        this.goodType = goodType;
        this.inventoryNum = inventoryNum;
        this.avePrice = avePrice;
        this.StockOutDate = date;
        this.batch = batch;
        this.batchNum = batchNum;
    }

    public String getGoodName() {
        return goodName;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }

    public String getGoodType() {
        return goodType;
    }

    public void setGoodType(String goodType) {
        this.goodType = goodType;
    }

    public int getInventoryNum() {
        return inventoryNum;
    }

    public void setInventoryNum(int inventoryNum) {
        this.inventoryNum = inventoryNum;
    }

    public double getAvePrice() {
        return avePrice;
    }

    public void setAvePrice(double avePrice) {
        this.avePrice = avePrice;
    }

    public String getStockOutDate() {
        return StockOutDate;
    }

    public void setStockOutDate(String date) {
        this.StockOutDate = date;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public String getBatchNum() {
        return batchNum;
    }

    public void setBatchinventoryNum(String batchNum) {
        this.batchNum = batchNum;
    }

    public boolean isSelected() {
        return selected.get();
    }

    public SimpleBooleanProperty selectedProperty() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected.set(selected);
    }

    public void setBatchNum(String batchNum) {
        this.batchNum = batchNum;
    }

    /**
     * 报了image null 的异常是因为VO里面没有相应的set/get
     * @return
     */
    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    @Override
    public int compareTo(InventoryCheckItemVO o) {
        return this.goodName.compareTo(o.goodName);
    }
}
