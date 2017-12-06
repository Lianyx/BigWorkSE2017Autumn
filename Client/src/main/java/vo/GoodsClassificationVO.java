package vo;

import java.util.Set;

public class GoodsClassificationVO {
    public String ID;

    public String name;

    public String fatherID;

    public Set<String> childrenID;

    public Set<String> commoditiesID;

    public GoodsClassificationVO(String ID, String name, String fatherID, Set<String> childrenID, Set<String> commoditiesID) {
        this.name = name;
        this.ID = ID;
        this.fatherID = fatherID;
        this.childrenID = childrenID;
        this.commoditiesID = commoditiesID;
    }
}
