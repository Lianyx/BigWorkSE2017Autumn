package businesslogic.inventorybl;

import vo.inventoryVO.InventoryCheckVO;
import vo.inventoryVO.InventoryViewVO;

public class InventroyShow {

    private SaleInfo saleInfo;

    public InventroyShow(){
        saleInfo = new SaleInfo();
    }

    /**
     * 库存查看
     * @param beginDate
     * @param endDate
     * @return
     */
    public InventoryViewVO inventoryView(String beginDate, String endDate){
        InventoryViewVO tmpVO = saleInfo.inventoryView(beginDate,endDate);
        InventoryViewVO vo = new InventoryViewVO(tmpVO.getBeginDate(),tmpVO.getEndDate(),tmpVO.getSaleNumber(),tmpVO.getPurNumber(),
                tmpVO.getSaleMoney(),tmpVO.getPurMoney());

        return vo;
    }

    /**
     * 库存盘点
     * @return
     */
    public InventoryCheckVO inventoryCheck(){
        String now = "`````";

        InventoryCheckVO tmpVO = saleInfo.inventoryCheck(now);
        InventoryCheckVO vo = new InventoryCheckVO(tmpVO.getCommodities(),tmpVO.getToday(),tmpVO.getLot());

        return vo;
    }
}
