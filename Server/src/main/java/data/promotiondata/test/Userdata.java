package data.promotiondata.test;

import data.userdata.UserData;
import dataService.userdataService.UserDataService;
import po.UserPO;
import util.UserSearchCondition;

public class Userdata {

    public static void main(String args[]){

        try{
            UserDataService userdata = new UserData();


           UserPO userPO = userdata.getNew();
            userPO.setComment("comment userPO");
            userPO.setDate("fdsa");
            userdata.update(userPO);

            System.out.println(userdata.search(new UserSearchCondition()));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}