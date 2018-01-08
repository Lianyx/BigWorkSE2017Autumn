package businesslogic.inventorybl;

import blService.inventoryblService.InventoryShowblService;
import po.receiptPO.InventoryDamageReceiptPO;
import po.receiptPO.InventoryGiftReceiptPO;
import po.receiptPO.InventoryOverflowReceiptPO;
import po.receiptPO.InventoryWarningReceiptPO;
import vo.inventoryVO.InventoryCheckVO;
import vo.inventoryVO.InventoryViewVO;

import java.util.List;

public class InventoryShowblController implements InventoryShowblService{
    @Override
    public InventoryViewVO inventoryView(String beginDate, String endDate) {
        return null;
    }

    @Override
    public InventoryCheckVO inventoryCheck() {
        return null;
    }


}
