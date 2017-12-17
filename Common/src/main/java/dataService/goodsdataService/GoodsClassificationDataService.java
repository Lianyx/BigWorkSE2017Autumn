package dataService.goodsdataService;

import po.GoodsClassificationPO;
import util.ResultMessage;


import java.util.ArrayList;


public interface GoodsClassificationDataService {
	/**
	 * 向序列化文件中添加一个po
	 * @param po
	 * @return 处理结果
	 */
	public ResultMessage insert(GoodsClassificationPO po);

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
	public ResultMessage update(GoodsClassificationPO po);

	/**
	 * 显示全部po
	 * @return 返回所有po的集合
	 */
	public ArrayList<GoodsClassificationPO> show();

	/**
	 * @param fatherId 分类父类的ID
	 * @return 父类
	 */
	public GoodsClassificationPO getFather(String fatherId);

}
