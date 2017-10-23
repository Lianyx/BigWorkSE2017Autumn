package vo;

import java.util.ArrayList;

public class InventoryCheckVO {
	/** 商品列表 */
	public ArrayList<InventoryCheckItemVO> commodities;
	/** 批号 */
	public String today;
	/** 批次 */
	public String lot;
	

	public InventoryCheckVO(ArrayList<InventoryCheckItemVO> commodities, String today, String lot) {
		this.commodities = commodities;
		this.today = today;
		this.lot = lot;
	}
}
