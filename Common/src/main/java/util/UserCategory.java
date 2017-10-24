package util;

public enum UserCategory {
     InventoryManager,Salesman,Accountant,GeneralManager;
     private final int value;

     private UserCategory() {
          this.value = ordinal();
     }

}
