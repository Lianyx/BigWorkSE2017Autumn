package blService.inventoryblService;

import vo.inventoryVO.InventoryViewVO;

public interface InventoryViewblService {
    /**
     * 查看此时间段内的出/入库数量/金额，销售/进货的数量/金额
     */
    public InventoryViewVO inventoryView(String beginDate, String endDate);
}
