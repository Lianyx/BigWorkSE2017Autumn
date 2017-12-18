package po;

import java.time.LocalDateTime;

public class BusinessConditionPO {

    LocalDateTime date;
    private double income;
    private double expense;

    public BusinessConditionPO(LocalDateTime date, double income, double expense) {
        this.date = date;
        this.income = income;
        this.expense = expense;
    }


    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public double getIncome() {
        return income;
    }

    public void setIncome(double income) {
        this.income = income;
    }

    public double getExpense() {
        return expense;
    }

    public void setExpense(double expense) {
        this.expense = expense;
    }



}
