package po.InventoryManagerPO;

import java.util.ArrayList;

public class GoodsClassificationPO {
    private static final long serialVersionUID = 1L;

    private String ID;

    private String name;

    private String fatherID;

    private ArrayList<String> childrenID;

    private ArrayList<String> goodsID;

    public GoodsClassificationPO(String ID, String name, String fatherID, ArrayList<String> childrenID, ArrayList<String> goodsID) {
        this.ID=ID;
        this.name = name;
        this.fatherID = fatherID;
        this.childrenID = childrenID;
        this.goodsID = goodsID;
    }

    public String getName() {
        return name;
    }

    public String getFatherID() {
        return fatherID;
    }

    public ArrayList<String> getChildrenID() {
        return childrenID;
    }

    public ArrayList<String> getGoodsID() {
        return goodsID;
    }
}
