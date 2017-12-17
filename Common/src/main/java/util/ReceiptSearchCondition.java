package util;

import util.BillType;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class ReceiptSearchCondition {
    private LocalDateTime begin, end;
    private ArrayList<BillType> billTypes;
    private String clerkName;
    private String stockName;
    private Integer clientId;

    public ReceiptSearchCondition() {
    }

    public ReceiptSearchCondition(LocalDateTime begin, LocalDateTime end, ArrayList<BillType> billTypes, String clerkName, String stockName, Integer clientId) {
        this.begin = begin;
        this.end = end;
        this.billTypes = billTypes;
        this.clerkName = clerkName;
        this.stockName = stockName;
        this.clientId = clientId;
    }

    public LocalDateTime getBegin() {
        return begin;
    }

    public void setBegin(LocalDateTime begin) {
        this.begin = begin;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    public ArrayList<BillType> getBillTypes() {
        return billTypes;
    }

    public void setBillTypes(ArrayList<BillType> billTypes) {
        this.billTypes = billTypes;
    }

    public String getClerkName() {
        return clerkName;
    }

    public void setClerkName(String clerkName) {
        this.clerkName = clerkName;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }
}
