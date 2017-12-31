package util;

import java.util.HashMap;

public enum UserCategory {
     InventoryManager,Salesman,SalesManager,Accountant,GeneralManager,UserManager;
     private final int value;

     public static HashMap<String,UserCategory> map = new HashMap<>();
     static{
          for(UserCategory userCategory:values()){
               map.put(userCategory.name(),userCategory);
          }
     }
     private UserCategory() {
          this.value = ordinal();
     }

}
