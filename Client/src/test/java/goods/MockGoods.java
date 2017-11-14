package goods;

import businesslogic.goods.Goods;

public class MockGoods extends Goods {
    String id;
    String name;
    int count;

    public MockGoods(String id, String name, int count) {
        this.id = id;
        this.name = name;
        this.count = count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCount() {
        return count;
    }
}
