package vo.billReceiptVO;

public class TransferItemVO {

    private int accountID;
    private double sum;
    private String commnet;

    public TransferItemVO(int accountID, double sum, String commnet) {
        this.accountID = accountID;
        this.sum = sum;
        this.commnet = commnet;
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

    public String getCommnet() {
        return commnet;
    }

    public void setCommnet(String commnet) {
        this.commnet = commnet;
    }
}
