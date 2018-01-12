package businesslogic.billbl;

import blService.billblservice.CashBillReceiptblService;
import businesslogic.accountbl.Accountbl;
import businesslogic.checkbl.Receiptbl;
import po.receiptPO.CashBillReceiptPO;
import util.ResultMessage;
import vo.billReceiptVO.CashItemVO;
import vo.billReceiptVO.CashReceiptVO;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class CashBillReceiptbl extends Receiptbl<CashReceiptVO,CashBillReceiptPO> implements CashBillReceiptblService {

    public CashBillReceiptbl() throws RemoteException, NotBoundException, MalformedURLException {
        super(CashReceiptVO.class, CashBillReceiptPO.class);
    }

    @Override
    public ResultMessage approve(CashReceiptVO receiptVO) throws RemoteException{
        List<CashItemVO> list = receiptVO.getCashList();
        double sum =0;
        int id = receiptVO.getAccountID();
        for(CashItemVO vo:list){
            sum+=vo.getPrice();
        }
        try{
            new Accountbl().decBalance(id,sum);
        }catch (Exception e){
            e.printStackTrace();
        }

        return ResultMessage.SUCCESS;
    }

}
