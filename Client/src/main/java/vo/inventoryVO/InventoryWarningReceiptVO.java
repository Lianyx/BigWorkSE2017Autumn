package vo.inventoryVO;

import po.receiptPO.ReceiptPO;
import util.ReceiptState;

import java.time.LocalDateTime;
import java.util.List;

public class InventoryWarningReceiptVO extends InventoryReceiptVO {
    private List<InventoryWarningGoodsItemVO> goodsList;

    public InventoryWarningReceiptVO(String id, int operatorId, LocalDateTime createTime, LocalDateTime lastModifiedTime,
                                     ReceiptState receiptState, List<InventoryWarningGoodsItemVO> goodsList) {
        super(id, operatorId, createTime, lastModifiedTime, receiptState);
        this.goodsList = goodsList;
    }

    @Override
    public <T extends ReceiptPO> T toPO() {
        return null;
    }
}
