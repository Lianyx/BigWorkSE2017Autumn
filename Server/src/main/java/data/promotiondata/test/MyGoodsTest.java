package data.promotiondata.test;

import data.goodsdata.GoodsClassificationData;
import data.goodsdata.GoodsData;
import po.GoodsClassificationPO;

import java.rmi.RemoteException;

public class MyGoodsTest {
    public static void main(String[] args) throws RemoteException {
        GoodsData goodsData = new GoodsData();
        System.out.println(goodsData.show().toString());
      /*  GoodsPO goodsPO = new GoodsPO("121","花灯1","美国灯","12212",0,1,1,1,1,23);

        goodsData.update(goodsPO);*/
        // goodsData.insert(new GoodsPO("121","花灯1","中国灯","12212",12,1,1,1,1,23));
     /*  goodsData.insert(new GoodsPO("122","花灯2","中国灯","12212",12,1,1,1,1,23));
       goodsData.insert(new GoodsPO("123","花灯3","中国灯","12212",12,1,1,1,1,23));
       goodsData.insert(new GoodsPO("124","花灯4","中国灯","12212",12,1,1,1,1,23));
       goodsData.insert(new GoodsPO("125","花灯5","中国灯","12212",12,1,1,1,1,23));
       goodsData.insert(new GoodsPO("126","花灯6","中国灯","12212",12,1,1,1,1,23));
       goodsData.insert(new GoodsPO("127","花灯7","中国灯","12212",12,1,1,1,1,23));
       goodsData.insert(new GoodsPO("128","花灯8","中国灯","12212",12,1,1,1,1,23));
       goodsData.insert(new GoodsPO("129","花灯9","中国灯","12212",12,1,1,1,1,23));
       goodsData.insert(new GoodsPO("130","花灯10","中国灯","12212",12,1,1,1,1,23));
        //goodsData.delete("12433");*/
        // goodsData.update(new GoodsPO("12433","彩灯","中国灯","12212",1,1,1,1,1,1));



        GoodsClassificationData goodsClassificationData = new GoodsClassificationData();
        goodsClassificationData.update(new GoodsClassificationPO("141","light","131",null,new String[]{"sss","aaa"}));
        goodsClassificationData.insert(new GoodsClassificationPO("root", null, null, new String[]{"123", "124", "125", "126", "127"}, null));
        goodsClassificationData.insert(new GoodsClassificationPO("123", "345", "root", new String[]{"131", "132", "133"}, null));
        goodsClassificationData.insert(new GoodsClassificationPO("124", "346", "root", new String[]{"134"}, null));
        goodsClassificationData.insert(new GoodsClassificationPO("125", "347", "root", null, null));
        goodsClassificationData.insert(new GoodsClassificationPO("126", "348", "root", null, null));
        goodsClassificationData.insert(new GoodsClassificationPO("127", "349", "root", null, null));
        goodsClassificationData.insert(new GoodsClassificationPO("131", "light", "123", new String[]{"141"}, null));
        goodsClassificationData.insert(new GoodsClassificationPO("132", "light", "123", null, null));
        goodsClassificationData.insert(new GoodsClassificationPO("133", "light", "123", null, null));
        goodsClassificationData.insert(new GoodsClassificationPO("134", "light", "124", null, null));
        goodsClassificationData.insert(new GoodsClassificationPO("141", "light", "131", null, null));

        System.out.println("finish");
    }
}
