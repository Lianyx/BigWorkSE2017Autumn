package dataService.goodsdataService;

import java.util.ArrayList;

import PO.GoodsClassifyPO;
import PO.GoodsPO;
import utility.ResultMessage;

public interface GoodsClassifyDataService {
	/**
	 * 向序列化文件中添加一个po
	 * @param po
	 * @return 处理结果
	 */
	public ResultMessage insert(GoodsClassifyPO po);

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
	public ResultMessage update(GoodsClassifyPO po);

	/**
	 * 显示全部po
	 * @return 返回所有po的集合
	 */
	public ArrayList<GoodsClassifyPO> show();
	
	/**
	 * @param fatherID 分类父类的ID
	 * @return 新建分类的ID
	 */
	public String getID(String fatherID);
}
