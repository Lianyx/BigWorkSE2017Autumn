package dataService;

import java.util.ArrayList;

import PO.GoodsPO;
import utility.ResultMessage;

public interface GoodsDataService {
	/**
	 * 向序列化文件中添加一个po
	 * @param po
	 * @return 处理结果
	 */
	public ResultMessage insert(GoodsPO po);

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
	public ResultMessage update(GoodsPO po);

	/**
	 * 显示全部po
	 * @return 返回所有po的集合
	 */
	public ArrayList<GoodsPO> show();
	

	/**
	 * 根据keywords查看商品，
	 * @param keywords
	 * @return 商品持久化数据
	 */
	public ArrayList<GoodsPO> find(String keywords);
	
	/**
	 * @param fatherID 商品父类的ID
	 * @return 新建商品的ID
	 */
	public String getID(String fatherID);
}
