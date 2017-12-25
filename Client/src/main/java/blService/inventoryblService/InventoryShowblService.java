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
    public InventoryViewVO InventoryView(String beginDate, String endDate);

    /**
     * 盘点的是当天的库存快照
     */
    public InventoryCheckVO InventoryCheck();

    /**
     * 得到所有审核通过的赠送单、报溢单、报损单、报警单
     */
    public List<InventoryGiftReceiptPO> showGiftReceiptOfApproved();
    public List<InventoryOverflowReceiptPO> showOverflowReceiptOfApproved();
    public List<InventoryDamageReceiptPO> showDamageReceiptOfApproved();
    public List<InventoryWarningReceiptPO> showWarningReceiptOfApproved();

    /**
     * 得到所有等待审核的赠送单、报溢单、报损单、报警单
     * @return
     */
    public List<InventoryGiftReceiptPO> showGiftReceiptOfPendding();
    public List<InventoryOverflowReceiptPO> showOverflowReceiptOfPendding();
    public List<InventoryDamageReceiptPO> showDamageReceiptOfPendding();
    public List<InventoryWarningReceiptPO> showWarningReceiptOfPendding();

    /**
     * 得到所有草稿的赠送单、报溢单、报损单、报警单
     * @return
     */
    public List<InventoryGiftReceiptPO> showGiftReceiptOfDarft();
    public List<InventoryOverflowReceiptPO> showOverflowReceiptOfDarft();
    public List<InventoryDamageReceiptPO> showDamageReceiptOfDarft();
    public List<InventoryWarningReceiptPO> showWarningReceiptOfDarft();
}