package blService;

import main.VO.InventoryBillVO;
import main.VO.InventoryCheckVO;
import main.VO.InventoryViewVO;
import utility.ResultMessage;

public interface InventoryBLService {
	/**
	 * �鿴��ʱ����ڵĳ�/�������/������/����������/���
	 */
	public InventoryViewVO viewInventory(String beginDate, String endDate);
	
	/**
	 * �̵���ǵ���Ŀ�����
	 */
	public InventoryCheckVO checkInventory();
	
	/**
	 * ������汨���������𵥡����絥�����͵�֮ǰ��Ҫ����,�õ�ID

	 */
	public String getGiftID();
	public String getOverFlowID();
	public String getLossID();
	public String getAlarmID();
	
	/**
	 * �����Ʒ
	 */
	public ResultMessage insertGoods(String ID, int number);
	
	public ResultMessage submit();
}
