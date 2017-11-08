package dataService.salesdataService;

import po.SalesReceiptPO;
import util.ResultMessage;
import vo.SalesReceiptListVO;
import vo.SalesReceiptVO;
import vo.SalesSearchVO;

import java.util.ArrayList;
import java.util.Set;

public interface SalesdataService {
    public ResultMessage insert(SalesReceiptPO salesReceiptPO);
    public ResultMessage delete(int id);
    public ResultMessage delete(ArrayList<Integer> list);

    public ResultMessage update(SalesReceiptPO salesReceiptPO);
    public ArrayList<SalesReceiptPO> select(SalesSearchVO salesSearchVO);
    public ArrayList<SalesReceiptPO> select(String keyword);
    public SalesReceiptVO showDetail(int id);
    public ArrayList<SalesReceiptPO> getAll();

}
