package vo;

import java.util.ArrayList;

public class InventoryCheckVO {
	/** ��Ʒ�б� */
	public ArrayList<InventoryCheckItemVO> commodities;
	/** ���� */
	public String today;
	/** ���� */
	public String lot;
	

	public InventoryCheckVO(ArrayList<InventoryCheckItemVO> commodities, String today, String lot) {
		this.commodities = commodities;
		this.today = today;
		this.lot = lot;
	}
}
