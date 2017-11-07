package po.InventoryManagerPO;

import java.util.ArrayList;

public class GoodsClassificationPO {
    private static final long serialVersionUID = 1L;

    private String ID;

    private String name;

    private String fatherID;

    private ArrayList<String> childrenID;

    private ArrayList<String> commoditiesID;

    public GoodsClassificationPO(String ID, String name, String fatherID, ArrayList<String> childrenID, ArrayList<String> commoditiesID) {
        this.ID=ID;
        this.name = name;
        this.fatherID = fatherID;
        this.childrenID = childrenID;
        this.commoditiesID = commoditiesID;
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

    public ArrayList<String> getCommoditiesID() {
        return commoditiesID;
    }
}
