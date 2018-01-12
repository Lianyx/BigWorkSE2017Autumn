package businesslogic.billbl;

import blService.billblservice.PaymentBillReceiptblService;
import blService.memberblService.MemberInfo;
import businesslogic.accountbl.Accountbl;
import businesslogic.checkbl.Receiptbl;
import businesslogic.memberbl.MemberInfo_Impl;
import businesslogic.memberbl.Memberbl;
import po.receiptPO.PaymentBillReceiptPO;
import util.ResultMessage;
import vo.billReceiptVO.PaymentReceiptVO;
import vo.billReceiptVO.TransferItemVO;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class PaymentBillReceiptbl extends Receiptbl<PaymentReceiptVO,PaymentBillReceiptPO> implements PaymentBillReceiptblService {

    public PaymentBillReceiptbl() throws RemoteException, NotBoundException, MalformedURLException {
        super(PaymentReceiptVO.class, PaymentBillReceiptPO.class);
    }

    @Override
    public ResultMessage approve(PaymentReceiptVO receiptVO) throws RemoteException {
        new MemberInfo_Impl().updateCredit(receiptVO.getclientID(),receiptVO.getSum());
        List<TransferItemVO> temp = receiptVO.getTransferList();
        try{
            Accountbl accountbl = new Accountbl();
            for(int i=0;i<temp.size();i++){
                accountbl.decBalance(temp.get(i).getAccountID(),temp.get(i).getSum());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return ResultMessage.SUCCESS;
    }
}
