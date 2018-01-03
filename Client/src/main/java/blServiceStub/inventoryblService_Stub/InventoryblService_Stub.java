package blServiceStub.inventoryblService_Stub;

import util.ResultMessage;
import vo.inventoryVO.InventoryCheckItemVO;
import vo.inventoryVO.InventoryCheckVO;
import vo.inventoryVO.InventoryViewVO;

import java.util.HashSet;

public class InventoryblService_Stub {
    /**
     * 查看此时间段内的出/入库数量/金额，销售/进货的数量/金额
     */
    public InventoryViewVO viewInventory(String beginDate, String endDate){
        return new InventoryViewVO();
    }

    /**
     * 盘点的是当天的库存快照
     */


    /**
     * 建立库存报警单、报损单、报溢单、赠送单之前需要调用,得到ID

     */
    public String getGiftID(){
        return "11";
    }
    public String getOverFlowID(){
        return "12";
    }
    public String getLossID(){
        return "13";
    }
    public String getAlarmID(){
        return "14";
    }

    /**
     * 添加商品
     */
    public ResultMessage addGoods(String ID, int number){
        return ResultMessage.SUCCESS;
    }

    public ResultMessage submit(){
        return ResultMessage.SUCCESS;
    }
}
