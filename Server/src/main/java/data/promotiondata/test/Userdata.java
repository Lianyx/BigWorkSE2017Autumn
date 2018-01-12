package data.promotiondata.test;

import data.stockdata.StockPurReceiptData;
import data.userdata.UserData;
import dataService.userdataService.UserDataService;
import po.UserPO;
import po.receiptPO.StockPurReceiptPO;
import util.RespectiveReceiptSearchCondition;
import util.UserSearchCondition;

import java.util.ArrayList;

public class Userdata {

    public static void main(String args[]){

        try{
            StockPurReceiptData userdata = new StockPurReceiptData();
            ArrayList<StockPurReceiptPO> arrayList = userdata.search(new RespectiveReceiptSearchCondition());
            userdata.getNew();
            System.out.println(arrayList.get(0).getDayId());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}