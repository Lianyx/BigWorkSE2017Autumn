package data.goodsdata;

import po.GoodsClassificationPO;

import java.util.ArrayList;


public interface GoodsClassficationPOMapper {
    public void insert(GoodsClassificationPO po);

    public void delete(String ID);

    public void update(GoodsClassificationPO po);

    public ArrayList<GoodsClassificationPO> show();

}
