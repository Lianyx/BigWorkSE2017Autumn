package vo;

public class InventoryViewVO {
	/** ��ʼ���� */
	public String beginDate;
	/** �������� */
	public String endDate;
	/** ����������Ҳ�������������� */
	public int saleNumber;
	/** ���������Ҳ���ǽ��������� */
	public int purNumber;
	/** �����Ҳ�������۽� */
	public double saleMoney;
	/** ����Ҳ���ǽ����� */
	public double purMoney;

	public InventoryViewVO(int saleNumber, int purNumber, double saleMoney, double purMoney) {
		this.saleNumber = saleNumber;
		this.purNumber = purNumber;
		this.saleMoney = saleMoney;
		this.purMoney = purMoney;
	}
}
