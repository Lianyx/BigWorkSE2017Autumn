package vo.inventoryVO.uiReceipt;

import java.util.Set;
import java.util.TreeSet;

public class InventoryGiftChosenItem {
    private static TreeSet<InventoryGiftuiVO> chosenitems=new TreeSet();
    static public void addItem(InventoryGiftuiVO InventoryGiftuiVO){
        chosenitems.add(InventoryGiftuiVO);
    }
    static public void removeItem(InventoryGiftuiVO InventoryGiftuiVO){
        chosenitems.remove(InventoryGiftuiVO);
    }
    static public Set<InventoryGiftuiVO> getSet(){return chosenitems;}
}
