package vo.inventoryVO;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleBooleanProperty;

import javafx.scene.image.Image;
import vo.abstractVO.SelectableVO;

import java.io.Serializable;

public class GoodsVO extends SelectableVO<GoodsVO> {
    /**编号*/
    private String id;
    /** 商品名称 */
    private String goodName;
    /** 商品型号 */
    private String goodType;
    /** 商品分类ID */
    private String classifyId;
    /** 商品库存数量 */
    private int inventoryNum;
    /** 商品进价 */
    private double purPrice;
    /** 商品零售价 */
    private double salePrice;
    /** 商品最近进价 */
    private double recentPurPrice;
    /** 商品最近零售价 */
    private double recentSalePrice;
    /** 商品警戒数量 */
    private int alarmNumber;
    /** 来表示是否被选中*/
    private boolean multiple = true;

    public GoodsVO() {
    }

    public GoodsVO(String id) {
        this.id = id;
    }

    public GoodsVO(String ID, String name, String type, String sortID, int inventoryNum, double purPrice, double salePrice, double recentPurpPrice, double recentSalePrice, int alarmNumber) {
        this.id = ID;
        this.goodName = name;
        this.goodType = type;
        this.classifyId = sortID;
        this.inventoryNum = inventoryNum;
        this.purPrice = purPrice;
        this.salePrice = salePrice;
        this.recentPurPrice = recentPurpPrice;
        this.recentSalePrice = recentSalePrice;
        this.alarmNumber = alarmNumber;
    }

    public boolean isMultiple() {
        return multiple;
    }

    public void setMultiple(boolean multiple) {
        this.multiple = multiple;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getClassifyId() {
        return classifyId;
    }

    public void setClassifyId(String classifyId) {
        this.classifyId = classifyId;
    }

    public int getInventoryNum() {
        return inventoryNum;
    }

    public void setInventoryNum(int inventoryNum) {
        this.inventoryNum = inventoryNum;
    }

    public double getPurPrice() {
        return purPrice;
    }

    public void setPurPrice(double purPrice) {
        this.purPrice = purPrice;
    }

    public double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(double salePrice) {
        this.salePrice = salePrice;
    }

    public double getRecentPurPrice() {
        return recentPurPrice;
    }

    public void setRecentPurPrice(double recentPurPrice) {
        this.recentPurPrice = recentPurPrice;
    }

    public double getRecentSalePrice() {
        return recentSalePrice;
    }

    public void setRecentSalePrice(double recentSalePrice) {
        this.recentSalePrice = recentSalePrice;
    }

    public int getAlarmNumber() {
        return alarmNumber;
    }

    public void setAlarmNumber(int alarmNumber) {
        this.alarmNumber = alarmNumber;
    }

    @Override
    public String toString() {
        return "GoodsVO{" +
                "id='" + id + '\'' +
                ", goodName='" + goodName + '\'' +
                ", goodType='" + goodType + '\'' +
                ", classifyId='" + classifyId + '\'' +
                ", inventoryNum=" + inventoryNum +
                ", purPrice=" + purPrice +
                ", salePrice=" + salePrice +
                ", recentPurPrice=" + recentPurPrice +
                ", recentSalePrice=" + recentSalePrice +
                ", alarmNumber=" + alarmNumber +
                ", multiple=" + multiple +
                '}';
    }
}
