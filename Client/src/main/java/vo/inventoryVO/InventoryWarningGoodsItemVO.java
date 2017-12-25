package vo.inventoryVO;

public class InventoryWarningGoodsItemVO extends InventoryReceiptGoodsItemVO{
    /** 商品警戒数量*/
    private int alarmNumber;

    public InventoryWarningGoodsItemVO() {}

    public InventoryWarningGoodsItemVO(String id, String goodName, String goodType, int inventoryNum,int alarmNumber) {
        super(id, goodName, goodType, inventoryNum);
        this.alarmNumber = alarmNumber;
    }

    public int getAlarmNumber() {
        return alarmNumber;
    }

    public void setAlarmNumber(int alarmNumber) {
        this.alarmNumber = alarmNumber;
    }
}
