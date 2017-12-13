package po;

import java.util.ArrayList;

public class GoodsClassificationPO {
    private String id;

    private String _name;

    private String fatherId;

    private ArrayList<String> goodsID;

    public GoodsClassificationPO() {
    }

    public GoodsClassificationPO(String ID, String name, String fatherID) {
        this.id = ID;
        this._name = name;
        this.fatherId = fatherID;
    }

    public GoodsClassificationPO(String ID, String name, String fatherID, ArrayList<String> goodsID) {
        this.id=ID;
        this._name = name;
        this.fatherId = fatherID;

        this.goodsID = goodsID;
    }

    public String getName() {
        return _name;
    }

    public String getFatherID() {
        return fatherId;
    }

    public ArrayList<String> getGoodsID() {
        return goodsID;
    }

    @Override
    public String toString() {
        return "GoodsClassificationPO{" +
                "id='" + id + '\'' +
                ", _name='" + _name + '\'' +
                ", fatherId='" + fatherId + '\'' +
                ", goodsID=" + goodsID +
                '}';
    }
}
