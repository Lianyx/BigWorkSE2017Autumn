package dataService.goodsdataService;

import java.util.ArrayList;

import PO.GoodsClassifyPO;
import PO.GoodsPO;
import utility.ResultMessage;

public interface GoodsClassifyDataService {
	/**
	 * �����л��ļ������һ��po
	 * @param po
	 * @return ������
	 */
	public ResultMessage insert(GoodsClassifyPO po);

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
	public ResultMessage update(GoodsClassifyPO po);

	/**
	 * ��ʾȫ��po
	 * @return ��������po�ļ���
	 */
	public ArrayList<GoodsClassifyPO> show();
	
	/**
	 * @param fatherID ���ุ���ID
	 * @return �½������ID
	 */
	public String getID(String fatherID);
}
