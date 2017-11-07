package blService.inventoryblService;

import util.ResultMessage;
import vo.InventoryCheckVO;
import vo.InventoryViewVO;

public interface InventoryblService {
    /**
     * 查看此时间段内的出/入库数量/金额，销售/进货的数量/金额
     */
    public InventoryViewVO viewInventory(String beginDate, String endDate);

    /**
     * 盘点的是当天的库存快照
     */
    public InventoryCheckVO checkInventory();

    /**
     * 建立库存报警单、报损单、报溢单、赠送单之前需要调用,得到ID

     */
    public String getGiftID();
    public String getOverFlowID();
    public String getLossID();
    public String getAlarmID();

    /**
     * 添加商品
     */
    public ResultMessage addGoods(String ID, int number);

    public ResultMessage submit();
}
