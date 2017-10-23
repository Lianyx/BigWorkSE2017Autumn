package blService;

import java.util.ArrayList;

import main.VO.GoodsClassifyVO;
import utility.ResultMessage;

public interface GoodsClassifyBLService {
	/**
	 * ��ʾ������Ʒ����
	 * @return
	 */
	public ArrayList<GoodsClassifyVO> show();
	
	/**
	 * ������Ʒ����ʱ���ã������ϲ���������ɱ��
	 * @param upID
	 * @return
	 */
	public String getID(String upID);
	
	/**
	 * ���ӷ���
	 * @param name
	 * @param upID
	 * @return
	 */
	public ResultMessage insertGoodsClassify(String name, String upID);
	
	/**
	 * ɾ����Ʒ����
	 * @param ID
	 * @return
	 */
	public ResultMessage deleteGoodsClassify(String ID);
	
	/**
	 * �޸ķ���
	 * @param ID
	 * @param name
	 * @return
	 */
	public ResultMessage updateGoodsClassify(String ID, String name);
}
