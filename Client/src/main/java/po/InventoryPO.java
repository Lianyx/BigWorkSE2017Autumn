package po;

import java.util.ArrayList;

import utility.BillState;
import utility.BillType;

public class InventoryPO {
	private static final long serialVersionUID = 1L;
	private String ID;
	/** ��Ʒ���ϣ����͵��� */
	private ArrayList<GoodsPO> goods;
	/** �������ͣ����𣯱��磯����/���� */
	private BillType billType;
	/** ����״̬ */
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
