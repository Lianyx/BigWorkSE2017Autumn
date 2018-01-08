package data.billreceiptdata;

import annotations.RMIRemote;
import data.checkdata.ReceiptData;
import mapper.PaymentBillReceiptPOMapper;
import mapper.generic.ReceipishPOMapper;
import mapper.generic.ReceiptPOMapper;
import mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import po.receiptPO.PaymentBillReceiptPO;
import util.ResultMessage;

import java.rmi.RemoteException;

@RMIRemote
public class PaymentBillReceiptData extends ReceiptData<PaymentBillReceiptPO> {
    public PaymentBillReceiptData() throws RemoteException {
        super(PaymentBillReceiptPOMapper.class, PaymentBillReceiptPO.class);
    }


}
