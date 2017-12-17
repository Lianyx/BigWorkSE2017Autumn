package inventory;

import businesslogic.inventorybl.InventoryDamage;

public class MockInventoryDamage extends InventoryDamage {
    String id;
    MockGoodsList goodsList;
    String userName;

    public MockInventoryDamage(String id, MockGoodsList goodsList, String userName) {
        this.id = id;
        this.goodsList = goodsList;
        this.userName = userName;
    }

    public String getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

}
