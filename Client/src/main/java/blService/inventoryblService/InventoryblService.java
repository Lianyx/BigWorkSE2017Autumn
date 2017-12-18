package blService.inventoryblService;

import util.ResultMessage;

public interface InventoryblService {

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
