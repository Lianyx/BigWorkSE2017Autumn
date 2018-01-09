package vo.receiptVO;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import util.ReceiptState;
import vo.abstractVO.SelectableVO;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class StockReceiptListVO extends ReceiptListVO<StockReceiptListVO>{

    private String id;
    private LocalDateTime createTime;
    private ReceiptState receiptState;
    private String memberName;
    private String stockName;
    private double sum;
    private boolean multiple = true;
    boolean isPur;

    public StockReceiptListVO() {
    }

    public StockReceiptListVO(String id, LocalDateTime createTime, ReceiptState receiptState, String memberName, String stockName, double sum, boolean isPur) {
        this.id = id;
        this.createTime = createTime;
        this.receiptState = receiptState;
        this.memberName = memberName;
        this.stockName = stockName;
        this.sum = sum;
        this.isPur = isPur;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
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


    @Override
    public <TV extends ReceiptVO> TV toVO() {
        return null;
    }
}