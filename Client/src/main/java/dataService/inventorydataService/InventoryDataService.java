package dataService.inventorydataService;

import java.util.ArrayList;

import PO.InventoryPO;
import utility.BillType;
import utility.ResultMessage;

public interface InventoryDataService {
	/**
	 * �����л��ļ������һ��po
	 * @param po
	 * @return ������
	 */
	public ResultMessage insert(InventoryPO po);

	/**
	 * ����IDɾ��
	 * @param ID
	 * @return ������
	 */
	public ResultMessage delete(String ID);

	/**
	 * ����po
	 * @param po
	 * @return ������
	 */
	public ResultMessage update(InventoryPO po);

	/**
	 * ��ʾȫ��po
	 * @return ��������po�ļ���
	 */
	public ArrayList<InventoryPO> show();
	
	/**
	 * @return �µı��絥��ID
	 */
	public String getOverflowID();

	/**
	 * @return �µı��𵥵�ID
	 */
	public String getLossID();

	/**
	 * @return �µı�������ID
	 */
	public String getAlarmID();

	/**
	 * @return �µ����͵���ID
	 */
	public String getGiftID();
	
	/**
	 * @param type ��������
	 * @return ���յ������ͷ��ص���
	 */
	public ArrayList<InventoryPO> show(BillType type);


}
