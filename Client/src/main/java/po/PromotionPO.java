package po;

import util.PromotionType;

import java.io.Serializable;
import java.util.ArrayList;

public class PromotionPO implements Serializable{
    private int ID;
    private int VIPGrade;
    private int cashSum;
    ArrayList<PromotionType> promotionTypes;
    private String startTime;
    private String endTime;

    public PromotionPO(int ID, int VIPGrade, int cashSum, ArrayList<PromotionType> promotionTypes, String startTime, String endTime) {
        this.ID = ID;
        this.VIPGrade = VIPGrade;
        this.cashSum = cashSum;
        this.promotionTypes = promotionTypes;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getVIPGrade() {
        return VIPGrade;
    }

    public void setVIPGrade(int VIPGrade) {
        this.VIPGrade = VIPGrade;
    }

    public int getCashSum() {
        return cashSum;
    }

    public void setCashSum(int cashSum) {
        this.cashSum = cashSum;
    }

    public ArrayList<PromotionType> getPromotionTypes() {
        return promotionTypes;
    }

    public void setPromotionTypes(ArrayList<PromotionType> promotionTypes) {
        this.promotionTypes = promotionTypes;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "PromotionPO{" +
                ", VIPGrade=" + VIPGrade +
                ", cashSum=" + cashSum +
                ", promotionTypes=" + promotionTypes +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                '}';
    }
}
