package vo.inventoryVO;

public class InventroyGiftGoodsItemVO extends InventoryReceiptGoodsItemVO {
    /** 商品赠送数量*/
    private int sendNumber;

    public InventroyGiftGoodsItemVO() {}


    public InventroyGiftGoodsItemVO(String id, String goodName, String goodType, int inventoryNum, int sendNumber) {
        super(id, goodName, goodType, inventoryNum);
        this.sendNumber = sendNumber;
    }

    public int getSendNumber() {
        return sendNumber;
    }

    public void setSendNumber(int sendNumber) {
        this.sendNumber = sendNumber;
    }
}
