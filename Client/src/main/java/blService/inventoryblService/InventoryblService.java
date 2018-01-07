package blService.inventoryblService;

import blService.checkblService.ReceiptblService;

import vo.inventoryVO.inventoryReceiptVO.InventoryReceiptListVO;
import vo.inventoryVO.inventoryReceiptVO.InventoryReceiptType;
import vo.inventoryVO.inventoryReceiptVO.InventoryReceiptVO;
import vo.inventoryVO.inventoryReceiptVO.InventorySearchVO;
import vo.inventoryVO.uiReceipt.InventoryGiftuiVO;

import java.util.Set;

public interface InventoryblService extends ReceiptblService<InventoryReceiptVO>{
    public int getDayId();
    public Set<InventoryReceiptListVO> search(InventorySearchVO stockSearchVO, InventoryReceiptType receiptType);
    public Set<InventoryReceiptListVO> search(String keyword, InventoryReceiptType receiptType);
    public InventoryReceiptVO showDetail(String id);
    public Set<InventoryReceiptListVO> getALL(InventoryReceiptType receiptType);
}
