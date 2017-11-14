package blService.checkblService;

import util.ResultMessage;
import vo.CheckSearchVO;
import vo.ReceiptVO;

import java.util.ArrayList;

public interface CheckblService {
    public ArrayList<ReceiptVO> initCheck();
    public ReceiptVO showDetail(int id);
    public ResultMessage approve(int id);
    public ResultMessage reject(int id);
    public ResultMessage approveBatch(ArrayList<Integer> ids);
    public ResultMessage rejectBatch(ArrayList<Integer> ids);
    public ResultMessage update(ReceiptVO receiptVO);
    public ArrayList<ReceiptVO> search(CheckSearchVO checkSearchVO);
}
