package vo;

public class InventoryViewVO {
    /** 开始日期 */
    private String beginDate;
    /** 结束日期 */
    private String endDate;
    /** 出库数量（也就是销售数量） */
    private int saleNumber;
    /** 入库数量（也就是进货数量） */
    private int purNumber;
    /** 出库金额（也就是销售金额） */
    private double saleMoney;
    /** 入库金额（也就是进货金额） */
    private double purMoney;

    public InventoryViewVO() {
    }

    public InventoryViewVO(String beginDate, String endDate, int saleNumber, int purNumber, double saleMoney, double purMoney) {
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.saleNumber = saleNumber;
        this.purNumber = purNumber;
        this.saleMoney = saleMoney;
        this.purMoney = purMoney;
    }

    public String getBeginDate() {
        return beginDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public int getSaleNumber() {
        return saleNumber;
    }

    public int getPurNumber() {
        return purNumber;
    }

    public double getSaleMoney() {
        return saleMoney;
    }

    public double getPurMoney() {
        return purMoney;
    }
}
