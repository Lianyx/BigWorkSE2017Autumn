package vo;

import util.BillState;
import util.InventoryBillCategory;

import java.util.Set;

public class InventoryBillVO {
    /**编号*/
    public String ID;
    /** 单子类型，报损／报溢／报警/赠送 */
    public InventoryBillCategory billType;
    /** 商品集合（赠送单、报损单、报溢单、报警单） */
    public Set<GoodsVO> goods;
    /** 添加备注 */
    public String remark;
    /** 单据状态 */
    public BillState state;

    public InventoryBillVO(String ID, InventoryBillCategory billType, Set<GoodsVO> goods, String remark, BillState state) {
        this.ID = ID;
        this.billType = billType;
        this.goods = goods;
        this.remark = remark;
        this.state = state;
    }
}
