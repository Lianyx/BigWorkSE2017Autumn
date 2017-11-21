package po;

import java.util.ArrayList;

public class CashBillPO {
    private int ID;
    private int number;
    private String oprator;
    private int accountID;
    private double total;
    private ArrayList<CashItemPO> itemList;

    public CashBillPO(int ID, int number, String oprator, int accountID, ArrayList<CashItemPO> itemList, double total) {
        this.ID = ID;
        this.number = number;
        this.oprator = oprator;
        this.accountID = accountID;
        this.itemList = itemList;
        this.total = total;
    }



    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getOprator() {
        return oprator;
    }

    public void setOprator(String oprator) {
        this.oprator = oprator;
    }

    public int getAccountID() {
        return accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    public ArrayList<CashItemPO> getItemList() {
        return itemList;
    }

    public void setItemList(ArrayList<CashItemPO> itemList) {
        this.itemList = itemList;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }


}
