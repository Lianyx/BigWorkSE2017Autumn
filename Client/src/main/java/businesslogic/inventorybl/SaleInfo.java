package businesslogic.inventorybl;

import vo.InventoryCheckVO;
import vo.InventoryViewVO;

public class SaleInfo {

    public InventoryViewVO inventoryView(String beginDate, String endDate){
        return new InventoryViewVO();
    }

    public InventoryCheckVO inventoryCheck(String now){
        return new InventoryCheckVO();
    }
}
