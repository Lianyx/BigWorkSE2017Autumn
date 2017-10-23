package po;

import java.io.Serializable;

/**
 * ��Ʒ�־û�����
 * @author ����
 *
 */
public class GoodsPO implements Serializable{
	private static final long serialVersionUID = 1L;
	


	/**���*/
	private String ID;
	/** ��Ʒ���� */
	private String name;
	/** ��Ʒ�ͺ� */
	private String type;
	/** ��Ʒ����ID */
	private String classifyID;
	/** ��Ʒ������� */
	private int inventoryNum;
	/** ��Ʒ���� */
	private double purPrice;
	/** ��Ʒ���ۼ� */
	private double salePrice;
	/** ��Ʒ������� */
	private double recentPurPrice;
	/** ��Ʒ������ۼ� */
	private double recentSalePrice;
	/** ��Ʒ�������� */
	private int alarmNumber;
	
	public GoodsPO(String ID, String name, String sortID, String type, double purPrice, double salePrice, int alarmNumber) {
		this.ID = ID;
		this.name = name;
		this.type = type;
		this.classifyID = classifyID;
		this.purPrice = purPrice;
		this.salePrice = salePrice;
		this.alarmNumber = alarmNumber;

	}
	
	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getClassifyID() {
		return classifyID;
	}

	public void setClassifyID(String classifyID) {
		this.classifyID = classifyID;
	}

	public int getInventoryNum() {
		return inventoryNum;
	}

	public void setInventoryNum(int inventoryNum) {
		this.inventoryNum = inventoryNum;
	}

	public double getPurPrice() {
		return purPrice;
	}

	public void setPurPrice(double purPrice) {
		this.purPrice = purPrice;
	}

	public double getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(double salePrice) {
		this.salePrice = salePrice;
	}

	public double getRecentPurPrice() {
		return recentPurPrice;
	}

	public void setRecentPurPrice(double recentPurPrice) {
		this.recentPurPrice = recentPurPrice;
	}

	public double getRecentSalePrice() {
		return recentSalePrice;
	}

	public void setRecentSalePrice(double recentSalePrice) {
		this.recentSalePrice = recentSalePrice;
	}

	public int getAlarmNumber() {
		return alarmNumber;
	}

	public void setAlarmNumber(int alarmNumber) {
		this.alarmNumber = alarmNumber;
	}
}
