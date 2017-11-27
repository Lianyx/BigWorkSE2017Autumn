package po;

import util.BillState;
import util.BillType;

import java.util.ArrayList;

public class InventoryPO {
    private static final long serialVersionUID = 1L;
    private String ID;
    /** 商品集合（赠送单） */
    private ArrayList<GoodsPO> goods;
    /** 单子类型，报损／报溢／报警/赠送 */
    private BillType billType;
    /** 单据状态 */
    private BillState state;

    public InventoryPO(String ID, BillType billType, ArrayList<GoodsPO> goods, String remark) {
        this.ID = ID;
        this.billType = billType;
        this.goods = goods;
        this.state = BillState.APPROVALING;
    }

    public BillState getState() {
        return this.state;
    }

    public void setState(BillState state) {
        this.state = state;
    }

    public BillType getBillType() {
        return billType;
    }

    public ArrayList<GoodsPO> getgoods() {
        return goods;
    }
}
