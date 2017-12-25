package vo.inventoryVO;

import po.receiptPO.ReceiptPO;
import util.ReceiptState;

import java.time.LocalDateTime;
import java.util.List;

public class InventoryGiftReceiptVO extends InventoryReceiptVO {
    private List<InventroyGiftGoodsItemVO> list;


    public InventoryGiftReceiptVO(String id, int operatorId, LocalDateTime createTime, LocalDateTime lastModifiedTime,
                                  ReceiptState receiptState, List<InventroyGiftGoodsItemVO> list) {
        super(id, operatorId, createTime, lastModifiedTime, receiptState);
        this.list = list;
    }

    @Override
    public <T extends ReceiptPO> T toPO() {
       // return new InventoryGiftReceiptPO(Integer.parseInt(getId()),getOperatorId(),getCreateTime(),getLastModifiedTime(),getReceiptState()
       // ,getClerkName(), list.toArray(new InventoryReceiptGoodsItemPO[list.size()]),getComment());
        return null;
    }
}
