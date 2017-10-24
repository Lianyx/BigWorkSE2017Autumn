package po;

public class ReceiptPO {
     String date;
     boolean check;

    public ReceiptPO(String date, boolean check) {
        this.date = date;
        this.check = check;
    }

    @Override
    public String toString() {
        return "ReceiptPO{" +
                "date='" + date + '\'' +
                ", check=" + check +
                '}';
    }
}
