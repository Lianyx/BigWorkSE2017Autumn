package vo;

public class BusinessConditionVO {
    private double income;
    private double expense;

    public BusinessConditionVO(double income, double expense) {
        this.income = income;
        this.expense = expense;
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
