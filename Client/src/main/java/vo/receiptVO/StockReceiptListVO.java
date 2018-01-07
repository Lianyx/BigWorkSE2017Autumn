package vo.receiptVO;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import util.ReceiptState;
import vo.abstractVO.SelectableVO;

import java.io.Serializable;

public class StockReceiptListVO extends SelectableVO<StockReceiptListVO>{

    private String id;
    private ReceiptState receiptState;
    private String memberName;
    private String stockName;
    private double sum;
    private boolean multiple = true;
    boolean isPur;


    public StockReceiptListVO(String id, ReceiptState receiptState, String memberName, String stockName, double sum,boolean isPur) {
        this.id = id;
        this.receiptState = receiptState;
        this.memberName = memberName;
        this.stockName = stockName;
        this.sum = sum;
        this.isPur=isPur;
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

    public ReceiptState getReceiptState() {
        return receiptState;
    }

    public void setReceiptState(ReceiptState receiptState) {
        this.receiptState = receiptState;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public boolean isPur() {
        return isPur;
    }

    public void setPur(boolean pur) {
        isPur = pur;
    }
}
