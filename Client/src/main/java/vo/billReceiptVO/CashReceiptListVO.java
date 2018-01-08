package vo.billReceiptVO;

import util.ReceiptState;
import vo.abstractVO.SelectableVO;

public class CashReceiptListVO extends SelectableVO<CashReceiptListVO>{

    private String id;
    private ReceiptState receiptState;
    private int operator;
    private double sum;
    private boolean multiple = true;



    public CashReceiptListVO(String id, ReceiptState receiptState, int operator, double sum) {
        this.id = id;
        this.receiptState = receiptState;
        this.operator = operator;
        this.sum = sum;
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

    public int getOperator() {
        return operator;
    }

    public void setOperator(int operator) {
        this.operator = operator;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public boolean isMultiple() {
        return multiple;
    }

    public void setMultiple(boolean multiple) {
        this.multiple = multiple;
    }


}
