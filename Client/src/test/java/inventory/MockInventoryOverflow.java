package inventory;

import businesslogic.Inventory.InventoryOverflow;

public class MockInventoryOverflow extends InventoryOverflow{
    String id;
    MockGoodsList goodsList;
    String userName;

    public MockInventoryOverflow(String id, MockGoodsList goodsList, String userName) {
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
