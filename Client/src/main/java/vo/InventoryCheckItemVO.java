package vo;

public class InventoryCheckItemVO {
	/** ��Ʒ�� */
	public String name;
	/** ���� */
	public String type;
	/** ������� */
	public int number;
	/** ÿ����Ʒ�۸� */
	public double avePrice;
	/**��������*/
	public String date;

	
	public InventoryCheckItemVO(String name, String type, int number, double avePrice,String date) {
		this.name = name;
		this.type = type;
		this.number = number;
		this.avePrice = avePrice;
		this.date = date;
	}
}
