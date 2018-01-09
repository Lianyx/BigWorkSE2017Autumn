package userdata;

import data.userdata.UserData;
import dataService.userdataService.UserDataService;
import util.UserSearchCondition;

public class Userdata {

    public static void main(String args[]){

        try{
            UserDataService userdata = new UserData();
            userdata.search(new UserSearchCondition());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
