package blService.inventoryblService;

import vo.inventoryVO.InventoryCheckVO;

public interface InventoryCheckblService {
    /**
     * 盘点的是当天的库存快照
     */
    public InventoryCheckVO inventoryCheck();
}
