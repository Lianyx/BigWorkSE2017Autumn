package po.InventoryManagerPO;

import po.GoodsPO;
import util.BillState;
import util.InventoryBillCategory;

import java.util.ArrayList;

public class InventoryBillPO {
    private static final long serialVersionUID = 1L;
    private String ID;
    /** 商品集合（赠送单） */
    private ArrayList<GoodsPO> goods;
    /** 单子类型，报损／报溢／报警/赠送 */
    private InventoryBillCategory billType;
    /** 单据状态 */
    private BillState state;
    /** 操作员 */
    private String operater;
    /** 商品的实际数量 */
    private ArrayList<Integer> goodsAcutalNum;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setBillType(InventoryBillCategory billType) {
        this.billType = billType;
    }

    public String getOperater() {
        return operater;
    }

    public void setOperater(String operater) {
        this.operater = operater;
    }

    /** 提供给报损报溢单的构造器*/
    public InventoryBillPO(String ID, ArrayList<GoodsPO> goods, InventoryBillCategory billType, BillState state, String operater, ArrayList<Integer> goodsAcutalNum) {
        this.ID = ID;
        this.goods = goods;
        this.billType = billType;
        this.state = state;
        this.operater = operater;
        this.goodsAcutalNum = goodsAcutalNum;
    }

    /** 提供给赠送报警单的构造器*/
    public InventoryBillPO(String ID, ArrayList<GoodsPO> goods, InventoryBillCategory billType, BillState state, String operater) {
        this.ID = ID;
        this.goods = goods;
        this.billType = billType;
        this.state = state;
        this.operater = operater;
    }

    public BillState getState() {
        return this.state;
    }

    public void setState(BillState state) {
        this.state = state;
    }

    public InventoryBillCategory getBillType() {
        return billType;
    }

    public ArrayList<GoodsPO> getgoods() {
        return goods;
    }
}
