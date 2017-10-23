package vo;

import java.util.ArrayList;

public class GoodsClassifyVO {
	public String ID;
	
	public String name;

	public String fatherID;

	public ArrayList<String> childrenID;

	public ArrayList<String> commoditiesID;

	public GoodsClassifyVO(String ID, String name, String fatherID, ArrayList<String> childrenID, ArrayList<String> commoditiesID) {
		this.name = name;
		this.ID = ID;
		this.fatherID = fatherID;
		this.childrenID = childrenID;
		this.commoditiesID = commoditiesID;
	}
}
