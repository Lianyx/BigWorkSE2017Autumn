package businesslogic.goodsClassificationbl;

import businesslogic.goodsbl.GoodsClassification_Goods;
import dataService.goodsdataService.GoodsClassificationDataService;
import po.GoodsClassificationPO;

import java.util.Arrays;

public class GoodsClassificationInfo implements GoodsClassification_Goods {
    private static final String DELETE = "-1";
    private GoodsClassificationDataService dataService;

    public GoodsClassificationInfo(){

    }

    @Override
    public void addGoods(String classifyId ,String goodId) {
        GoodsClassificationPO po = dataService.getById(classifyId);
        String[] oldGoodsId = po.getGoodsId();

        String[] newGoodsId = Arrays.copyOf(oldGoodsId,oldGoodsId.length+1);
        newGoodsId[oldGoodsId.length] = goodId;

        dataService.update(po);
    }

    @Override
    public void deleteGoods(String classifyId ,String goodsId) {
        GoodsClassificationPO po = dataService.getById(classifyId);
        String[] oldGoodsId = po.getGoodsId();

        String[] newGoodsId = new String[oldGoodsId.length-1];
        for (int i = 0, j = 0; i < newGoodsId.length; i++,j++) {
            if(oldGoodsId[j].equals(goodsId)){
                j++;
            }
            newGoodsId[i] = oldGoodsId[j];
        }

        po.setGoodsId(newGoodsId);
        dataService.update(po);
    }
}
