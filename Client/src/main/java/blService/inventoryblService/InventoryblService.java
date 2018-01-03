package blService.inventoryblService;

import blService.checkblService.ReceiptblService;
import vo.inventoryVO.InventoryReceiptVO;
import vo.inventoryVO.uiReceipt.InventoryGiftuiVO;

import java.util.Set;

public interface InventoryblService extends ReceiptblService<InventoryReceiptVO>{
    public Set<InventoryGiftuiVO> getAll();

}
