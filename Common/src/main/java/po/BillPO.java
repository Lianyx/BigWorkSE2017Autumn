package po;

import util.BillCategory;

import java.util.ArrayList;

public class BillPO {
	private int ID;
	private int number;
    private BillCategory billCategory;
    private int retailerID;
    private int supplierID;
    private String oprator;
    private ArrayList<TransferItemPO> transferList;
    private double sum;


    public BillPO(int ID, int number, BillCategory billCategory, int retailerID, int supplierID, String oprator, ArrayList<TransferItemPO> transferList, double sum) {
        this.ID = ID;
        this.number = number;
        this.billCategory = billCategory;
        this.retailerID = retailerID;
        this.supplierID = supplierID;
        this.oprator = oprator;
        this.transferList = transferList;
        this.sum = sum;
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

    public BillCategory getBillCategory() {
        return billCategory;
    }

    public void setBillCategory(BillCategory billCategory) {
        this.billCategory = billCategory;
    }

    public int getRetailerID() {
        return retailerID;
    }

    public void setRetailerID(int retailerID) {
        this.retailerID = retailerID;
    }

    public int getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(int supplierID) {
        this.supplierID = supplierID;
    }

    public String getOprator() {
        return oprator;
    }

    public void setOprator(String oprator) {
        this.oprator = oprator;
    }

    public ArrayList<TransferItemPO> getTransferList() {
        return transferList;
    }

    public void setTransferList(ArrayList<TransferItemPO> transferList) {
        this.transferList = transferList;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }



}
