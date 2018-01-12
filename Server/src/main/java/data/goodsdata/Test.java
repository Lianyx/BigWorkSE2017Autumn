package data.goodsdata;

import po.GoodsClassificationPO;
import po.GoodsPO;

import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.List;

public class Test {
    public static void main(String[] args) throws RemoteException {
        GoodsData goodsData = new GoodsData();
        goodsData.delete("130");
        //goodsData.insert(new GoodsPO("155","花灯1","美国灯","12212",0,1,1,1,1,23));
      /*  GoodsPO goodsPO = new GoodsPO("121","花灯1","美国灯","12212",0,1,1,1,1,23);

        goodsData.update(goodsPO);*/
      // goodsData.insert(new GoodsPO("121","花灯1","中国灯","12212",12,1,1,1,1,23));
     /*  goodsData.insert(new GoodsPO("122","花灯2","小灯1","12212",122,29,12,12,54,23));
       goodsData.insert(new GoodsPO("123","花灯3","大灯2","12212",123,29,11,14,34,23));
       goodsData.insert(new GoodsPO("124","花灯4","中灯2","12212",142,29.0,3,13,23,23));
       goodsData.insert(new GoodsPO("125","花灯5","中灯2","12212",12,40,45,15,24,231));
       goodsData.insert(new GoodsPO("126","花灯6","大灯3","12212",12,23,98,164,34,213));
       goodsData.insert(new GoodsPO("127","花灯7","小灯1","12212",12,45,43,15,12,23));
       goodsData.insert(new GoodsPO("128","花灯8","中灯2","12212",12,65,23,45,23,23));
       goodsData.insert(new GoodsPO("129","花灯9","中灯4","12212",12,89,12,65,32,23));
       goodsData.insert(new GoodsPO("130","花灯10","大灯2","12212",12,23,11,34,32,23));
        //goodsData.delete("12433");*/
       // goodsData.update(new GoodsPO("12433","彩灯","中国灯","12212",1,1,1,1,1,1));


/*
        GoodsClassificationData goodsClassificationData = new GoodsClassificationData();
        goodsClassificationData.update(new GoodsClassificationPO("141","light","131",null,new String[]{"sss","aaa"}));*/
    /*    goodsClassificationData.insert(new GoodsClassificationPO("root",null,null,new String[]{"123","124","125","126","127"},null));
        goodsClassificationData.insert(new GoodsClassificationPO("123","345","root",new String[]{"131","132","133"},null));
        goodsClassificationData.insert(new GoodsClassificationPO("124","346","root",new String[]{"134"},null));
        goodsClassificationData.insert(new GoodsClassificationPO("125","347","root",null,null));
        goodsClassificationData.insert(new GoodsClassificationPO("126","348","root",null,null));
        goodsClassificationData.insert(new GoodsClassificationPO("127","349","root",null,null));
        goodsClassificationData.insert(new GoodsClassificationPO("131","light","123",new String[]{"141"},null));
        goodsClassificationData.insert(new GoodsClassificationPO("132","light","123",null,null));
        goodsClassificationData.insert(new GoodsClassificationPO("133","light","123",null,null));
        goodsClassificationData.insert(new GoodsClassificationPO("134","light","124",null,null));
        goodsClassificationData.insert(new GoodsClassificationPO("141","light","131",null,null));*/
    }
}
