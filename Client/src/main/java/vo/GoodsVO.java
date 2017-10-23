package vo;

public class GoodsVO {
	/**��� */
	String ID;
	/** ��Ʒ���� */
	public String name;
	/** ��Ʒ�ͺ� */
	public String type;
	/** ��Ʒ����ID */
	public String sortID;
	/** ��Ʒ������� */
	public int inventoryNum;
	/** ��Ʒ���� */
	public double purPrice;
	/** ��Ʒ�ۼ� */
	public double salePrice;
	/** ��Ʒ������� */
	public double recentPurPrice;
	/** ��Ʒ����ۼ� */
	public double recentSalePrice;
	/** ��Ʒ�������� */
	public int alarmNumber;
	
	public GoodsVO(String ID, String name, String type, String sortID, int inventoryNum, double purPrice, double salePrice, double recentPurpPrice, double recentSalePrice, int alarmNumber) {
		this.ID = ID;
		this.name = name;
		this.type = type;
		this.sortID = sortID;
		this.inventoryNum = inventoryNum;
		this.purPrice = purPrice;
		this.salePrice = salePrice;
		this.recentPurPrice = recentPurpPrice;
		this.recentSalePrice = recentSalePrice;
		this.alarmNumber = alarmNumber;
	}
}
