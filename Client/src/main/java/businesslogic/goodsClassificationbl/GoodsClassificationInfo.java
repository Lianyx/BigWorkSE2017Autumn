package businesslogic.goodsClassificationbl;

import blService.goodsblService.GoodsClassification_Goods;
import dataService.goodsdataService.GoodsClassificationDataService;
import po.GoodsClassificationPO;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Arrays;

public class GoodsClassificationInfo implements GoodsClassification_Goods {
    private static final String DELETE = "-1";
    private GoodsClassificationDataService dataService;

    public GoodsClassificationInfo() throws RemoteException, NotBoundException, MalformedURLException {
        dataService = (GoodsClassificationDataService) Naming.lookup("rmi://localhost:1099/GoodsClassificationData");
    }

    @Override
    public void addGoods(String classifyId ,String goodId) throws RemoteException {
        GoodsClassificationPO po = dataService.getById(classifyId);
        String[] oldGoodsId = po.getGoodsId();

        String[] newGoodsId = Arrays.copyOf(oldGoodsId,oldGoodsId.length+1);
        newGoodsId[oldGoodsId.length] = goodId;

        dataService.update(po);
    }

    @Override
    public void deleteGoods(String classifyId ,String goodsId) throws RemoteException{
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
