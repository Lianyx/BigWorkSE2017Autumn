package po;

public class TransferItemPO {

    private int accountID;
    private double sum;
    private String comment;


    public TransferItemPO(int accountID, double sum, String coment) {
        this.accountID = accountID;
        this.sum = sum;
        this.comment = comment;
    }


    public int getAccountID() {
        return accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
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
