package dataService.inventorydataService;

import java.util.ArrayList;

import PO.InventoryPO;
import utility.BillType;
import utility.ResultMessage;

public interface InventoryDataService {
	/**
	 * 向序列化文件中添加一个po
	 * @param po
	 * @return 处理结果
	 */
	public ResultMessage insert(InventoryPO po);

	/**
	 * 根据ID删除
	 * @param ID
	 * @return 处理结果
	 */
	public ResultMessage delete(String ID);

	/**
	 * 更新po
	 * @param po
	 * @return 处理结果
	 */
	public ResultMessage update(InventoryPO po);

	/**
	 * 显示全部po
	 * @return 返回所有po的集合
	 */
	public ArrayList<InventoryPO> show();
	
	/**
	 * @return 新的报溢单的ID
	 */
	public String getOverflowID();

	/**
	 * @return 新的报损单的ID
	 */
	public String getLossID();

	/**
	 * @return 新的报警单的ID
	 */
	public String getAlarmID();

	/**
	 * @return 新的赠送单的ID
	 */
	public String getGiftID();
	
	/**
	 * @param type 单据类型
	 * @return 按照单据类型返回单据
	 */
	public ArrayList<InventoryPO> show(BillType type);


}
