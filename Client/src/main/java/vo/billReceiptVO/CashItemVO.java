package vo.billReceiptVO;

public class CashItemVO {

    private String name;
    private double sum;
    private String comment;

    public CashItemVO(String name, double sum, String comment) {
        this.name = name;
        this.sum = sum;
        this.comment = comment;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
