package blService;

import java.util.ArrayList;

import main.VO.GoodsClassifyVO;
import utility.ResultMessage;

public interface GoodsClassifyBLService {
	/**
	 * 显示所有商品分类
	 * @return
	 */
	public ArrayList<GoodsClassifyVO> show();
	
	/**
	 * 创建商品分类时调用，根据上层分类编号生成编号
	 * @param upID
	 * @return
	 */
	public String getID(String upID);
	
	/**
	 * 增加分类
	 * @param name
	 * @param upID
	 * @return
	 */
	public ResultMessage insertGoodsClassify(String name, String upID);
	
	/**
	 * 删除商品分类
	 * @param ID
	 * @return
	 */
	public ResultMessage deleteGoodsClassify(String ID);
	
	/**
	 * 修改分类
	 * @param ID
	 * @param name
	 * @return
	 */
	public ResultMessage updateGoodsClassify(String ID, String name);
}
