package businesslogic.billbl;

import blService.billblservice.ChargeBillReceiptblService;
import businesslogic.accountbl.Accountbl;
import businesslogic.checkbl.Receiptbl;
import businesslogic.memberbl.MemberInfo_Impl;
import po.receiptPO.ChargeBillReceiptPO;
import util.ResultMessage;
import vo.billReceiptVO.ChargeReceiptVO;
import vo.billReceiptVO.TransferItemVO;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;

public class ChargeBillReceiptbl extends Receiptbl<ChargeReceiptVO,ChargeBillReceiptPO> implements ChargeBillReceiptblService {

    public ChargeBillReceiptbl() throws RemoteException, NotBoundException, MalformedURLException {
        super(ChargeReceiptVO.class, ChargeBillReceiptPO.class);
    }

    @Override
    public ResultMessage approve(ChargeReceiptVO receiptVO) throws RemoteException {
        new MemberInfo_Impl().updateDebt(receiptVO.getClientID(),receiptVO.getSum());
        List<TransferItemVO> temp = receiptVO.getTransferList();
        try{
            Accountbl accountbl = new Accountbl();
            for(int i=0;i<temp.size();i++){
                accountbl.incBalance(temp.get(i).getAccountID(),temp.get(i).getSum());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return ResultMessage.SUCCESS;
    }


}
