package inventory;

import businesslogic.inventorybl.InventoryCheck;

public class MockInventoryCheck extends InventoryCheck{
    public MockInventoryCheck(MockGoodsList goodsList, String today, String lot) {
        this.goodsList = goodsList;
        this.today = today;
        this.lot = lot;
    }

    MockGoodsList goodsList;
    //批号
    private String today;
    //批次
    private String lot;

    public String getToday() {
        return today;
    }

    public String getLot() {
        return lot;
    }
}
