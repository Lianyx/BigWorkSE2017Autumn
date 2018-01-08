package data.billreceiptdata;

import annotations.RMIRemote;
import data.checkdata.ReceiptData;
import mapper.ChargeBillReceiptPOMapper;
import mapper.generic.ReceiptPOMapper;
import po.receiptPO.ChargeBillReceiptPO;

import java.rmi.RemoteException;

@RMIRemote
public class ChargeBillReceiptData extends ReceiptData<ChargeBillReceiptPO> {
    public ChargeBillReceiptData() throws RemoteException {
        super(ChargeBillReceiptPOMapper.class, ChargeBillReceiptPO.class);
    }
}
