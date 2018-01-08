package data.goodsdata;

import po.GoodsClassificationPO;
import po.GoodsPO;

import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.List;

public class Test {
    public static void main(String[] args) throws RemoteException {
       /* GoodsData goodsData = new GoodsData();
       *//* List<GoodsPO> list = goodsData.show();
        System.out.println(list.toString());*//*
       goodsData.insert(new GoodsPO("12432","花灯1","中国灯","12212",1,1,1,1,1,1));
       goodsData.insert(new GoodsPO("12433","花灯2","中国灯","12212",1,1,1,1,1,1));
       goodsData.insert(new GoodsPO("12434","花灯3","中国灯","12212",1,1,1,1,1,1));
       goodsData.insert(new GoodsPO("12435","花灯4","中国灯","12212",1,1,1,1,1,1));
       goodsData.insert(new GoodsPO("12436","花灯5","中国灯","12212",1,1,1,1,1,1));
       goodsData.insert(new GoodsPO("12437","花灯6","中国灯","12212",1,1,1,1,1,1));
       goodsData.insert(new GoodsPO("12438","花灯7","中国灯","12212",1,1,1,1,1,1));
       goodsData.insert(new GoodsPO("12439","花灯8","中国灯","12212",1,1,1,1,1,1));
       goodsData.insert(new GoodsPO("12440","花灯9","中国灯","12212",1,1,1,1,1,1));
       goodsData.insert(new GoodsPO("12441","花灯10","中国灯","12212",1,1,1,1,1,1));

        goodsData.delete("12433");
       // goodsData.update(new GoodsPO("12433","彩灯","中国灯","12212",1,1,1,1,1,1));*/

        GoodsClassificationData goodsClassificationData = new GoodsClassificationData();
        goodsClassificationData.insert(new GoodsClassificationPO());
    }
}
