package businesslogic.inventorybl;

import vo.inventoryVO.InventoryCheckVO;
import vo.inventoryVO.InventoryViewVO;

public class SaleInfo {

    public InventoryViewVO inventoryView(String beginDate, String endDate){
        return new InventoryViewVO();
    }

    public InventoryCheckVO inventoryCheck(String now){
        return new InventoryCheckVO();
    }
}
