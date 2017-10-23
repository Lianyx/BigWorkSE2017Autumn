package vo;

import java.util.ArrayList;

import utility.BillState;
import utility.BillType;

public class InventoryBillVO {
	/**���*/
	public String ID;
	/** �������ͣ����𣯱��磯����/���� */
	public BillType billType;
	/** ��Ʒ���ϣ����͵������𵥡����絥���������� */
	public ArrayList<GoodsVO> goods;
	/** ��ӱ�ע */
	public String remark;
	/** ����״̬ */
	public BillState state;

	public InventoryBillVO(String ID, BillType billType, ArrayList<GoodsVO> goods, String remark, BillState state) {
		this.ID = ID;
		this.billType = billType;
		this.goods = goods;
		this.remark = remark;
		this.state = state;
	}
}
