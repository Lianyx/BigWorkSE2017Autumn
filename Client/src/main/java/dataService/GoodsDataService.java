package dataService;

import java.util.ArrayList;

import PO.GoodsPO;
import utility.ResultMessage;

public interface GoodsDataService {
	/**
	 * �����л��ļ������һ��po
	 * @param po
	 * @return ������
	 */
	public ResultMessage insert(GoodsPO po);

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
	public ResultMessage update(GoodsPO po);

	/**
	 * ��ʾȫ��po
	 * @return ��������po�ļ���
	 */
	public ArrayList<GoodsPO> show();
	

	/**
	 * ����keywords�鿴��Ʒ��
	 * @param keywords
	 * @return ��Ʒ�־û�����
	 */
	public ArrayList<GoodsPO> find(String keywords);
	
	/**
	 * @param fatherID ��Ʒ�����ID
	 * @return �½���Ʒ��ID
	 */
	public String getID(String fatherID);
}
