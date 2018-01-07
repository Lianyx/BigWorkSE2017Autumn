package data.billreceiptdata;

import annotations.RMIRemote;
import data.checkdata.ReceiptData;
import mapper.CashBillReceiptPOMapper;
import mapper.generic.ReceiptPOMapper;
import po.receiptPO.CashBillReceiptPO;

import java.rmi.RemoteException;

@RMIRemote
public class CashBillReceiptData extends ReceiptData<CashBillReceiptPO> {
    public CashBillReceiptData() throws RemoteException {
        super(CashBillReceiptPOMapper.class, CashBillReceiptPO.class);
    }
}
