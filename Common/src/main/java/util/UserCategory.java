package util;

import java.util.HashMap;

public enum UserCategory {
     InventoryManager,Salesman,SalesManager,Accountant,GeneralManager,UserManager;
     private final int value;

     public static HashMap<String,UserCategory> map = new HashMap<>();
     public static HashMap<String,String> color = new HashMap<>();

     static{
          for(UserCategory userCategory:values()){
               map.put(userCategory.name(),userCategory);
          }
          for(UserCategory userCategory:values()){
               if(userCategory==UserCategory.SalesManager){
                    color.put(userCategory.name(),"-fx-text-fill: white;-fx-background-radius: 10; -fx-background-color:red");
               }else if(userCategory==UserCategory.GeneralManager){
                    color.put(userCategory.name(),"-fx-text-fill: white;-fx-background-radius: 10; -fx-background-color:green");
               }else if(userCategory==UserCategory.InventoryManager){
                    color.put(userCategory.name(),"-fx-text-fill: white;-fx-background-radius: 10; -fx-background-color:yellow");
               }else if(userCategory==UserCategory.Accountant){
                    color.put(userCategory.name(),"-fx-text-fill: white;-fx-background-radius: 10; -fx-background-color:blue");
               }else if(userCategory==UserCategory.Salesman){
                    color.put(userCategory.name(),"-fx-text-fill: white;-fx-background-radius: 10; -fx-background-color:orange");
               }else if(userCategory==UserCategory.UserManager){
                    color.put(userCategory.name(),"-fx-text-fill: white;-fx-background-radius: 10; -fx-background-color:purple");
               }
          }
     }
     private UserCategory() {
          this.value = ordinal();
     }

}
