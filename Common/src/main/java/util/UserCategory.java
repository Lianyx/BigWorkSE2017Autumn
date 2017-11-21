package util;

public enum UserCategory {
     InventoryManager,Salesman,SalesManager,Accountant,GeneralManager;
     private final int value;

     private UserCategory() {
          this.value = ordinal();
     }

}
