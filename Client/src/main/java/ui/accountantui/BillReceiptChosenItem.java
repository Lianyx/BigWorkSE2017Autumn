package ui.accountantui;

import vo.AccountListVO;
import vo.receiptVO.ReceiptVO;

import java.util.ArrayList;
import java.util.List;

public class BillReceiptChosenItem {

    private static ArrayList<ReceiptVO> chosenitems = new ArrayList<>();
    static public void addItem(ReceiptVO receiptVO){
        chosenitems.add(receiptVO);
    }
    static public void removeItem(ReceiptVO receiptVO){
        chosenitems.remove(receiptVO);
    }
    static public List<ReceiptVO> getList(){return chosenitems;}
}
