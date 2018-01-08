package blService.inventoryblService;

import po.receiptPO.InventoryDamageReceiptPO;
import po.receiptPO.InventoryGiftReceiptPO;
import po.receiptPO.InventoryOverflowReceiptPO;
import po.receiptPO.InventoryWarningReceiptPO;
import vo.inventoryVO.InventoryCheckVO;
import vo.inventoryVO.InventoryViewVO;

import java.util.List;

public interface InventoryShowblService{
    /**
     * 查看此时间段内的出/入库数量/金额，销售/进货的数量/金额
     */
    public InventoryViewVO inventoryView(String beginDate, String endDate);

    /**
     * 盘点的是当天的库存快照
     */
    public InventoryCheckVO inventoryCheck();


}