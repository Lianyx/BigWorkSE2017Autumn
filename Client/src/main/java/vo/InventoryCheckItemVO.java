package vo;

public class InventoryCheckItemVO {
	/** 商品名 */
	public String name;
	/** 类型 */
	public String type;
	/** 库存数量 */
	public int number;
	/** 每个商品价格 */
	public double avePrice;
	/**出场日期*/
	public String date;

	
	public InventoryCheckItemVO(String name, String type, int number, double avePrice,String date) {
		this.name = name;
		this.type = type;
		this.number = number;
		this.avePrice = avePrice;
		this.date = date;
	}
}
