package vo.inventoryVO.inventoryReceiptVO;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.Serializable;

public class ReceiptGoodsItemVO extends RecursiveTreeObject<ReceiptGoodsItemVO> implements Serializable {
    private StringProperty goodsName;
    private  StringProperty goodsId;
    private  StringProperty goodsType;
    private IntegerProperty inventoryNum;
    private IntegerProperty factNum;
    private IntegerProperty sendNum;
    private IntegerProperty warningNum;

    public ReceiptGoodsItemVO() {
        this.goodsName = new SimpleStringProperty();
        this.goodsId = new SimpleStringProperty();
        this.goodsType = new SimpleStringProperty();
        this.inventoryNum = new SimpleIntegerProperty();
        this.factNum = new SimpleIntegerProperty();
        this.sendNum = new SimpleIntegerProperty();
        this.warningNum = new SimpleIntegerProperty();
    }

    public ReceiptGoodsItemVO(String goodsName, String goodsId, String goodsType,
                              Integer inventoryNum, Integer factNum, Integer sendNum, Integer warningNum) {
        this.goodsName = new SimpleStringProperty(goodsName);
        this.goodsId = new SimpleStringProperty(goodsId);
        this.goodsType = new SimpleStringProperty(goodsType);
        this.inventoryNum = new SimpleIntegerProperty(inventoryNum);
        this.factNum = new SimpleIntegerProperty(factNum);
        this.sendNum = new SimpleIntegerProperty(sendNum);
        this.warningNum = new SimpleIntegerProperty(warningNum);
    }

    public ReceiptGoodsItemVO(String goodsName, String goodsId, Integer inventoryNum,Integer sendNum) {
        this.goodsName = new SimpleStringProperty(goodsName);
        this.goodsId = new SimpleStringProperty(goodsId);
        this.inventoryNum = new SimpleIntegerProperty(inventoryNum);
        this.sendNum = new SimpleIntegerProperty(sendNum);
    }

    public String getGoodsType() {
        return goodsType.get();
    }

    public StringProperty goodsTypeProperty() {
        return goodsType;
    }

    public void setGoodsType(String goodsType) {
        this.goodsType.set(goodsType);
    }

    public String getGoodsName() {
        return goodsName.get();
    }

    public StringProperty goodsNameProperty() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName.set(goodsName);
    }

    public String getGoodsId() {
        return goodsId.get();
    }

    public StringProperty goodsIdProperty() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId.set(goodsId);
    }

    public int getInventoryNum() {
        return inventoryNum.get();
    }

    public IntegerProperty inventoryNumProperty() {
        return inventoryNum;
    }

    public void setInventoryNum(int inventoryNum) {
        this.inventoryNum.set(inventoryNum);
    }

    public int getFactNum() {
        return factNum.get();
    }

    public IntegerProperty factNumProperty() {
        return factNum;
    }

    public void setFactNum(int factNum) {
        this.factNum.set(factNum);
    }

    public int getSendNum() {
        return sendNum.get();
    }

    public IntegerProperty sendNumProperty() {
        return sendNum;
    }

    public void setSendNum(int sendNum) {
        this.sendNum.set(sendNum);
    }

    public int getWarningNum() {
        return warningNum.get();
    }

    public IntegerProperty warningNumProperty() {
        return warningNum;
    }

    public void setWarningNum(int warningNum) {
        this.warningNum.set(warningNum);
    }
}
