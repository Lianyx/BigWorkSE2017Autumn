package blServiceStub.billblservice_Stub;

import blService.billblService.ChargeBillReceiptblService;
import util.ReceiptSearchCondition;
import util.ReceiptState;
import util.RespectiveReceiptSearchCondition;
import util.ResultMessage;
import vo.billReceiptVO.CashBillReceiptVO;
import vo.billReceiptVO.ChargeBillReceiptVO;
import vo.billReceiptVO.PaymentBillReceiptVO;
import vo.billReceiptVO.TransferItemVO;

import java.rmi.RemoteException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ChargeblService_Stub implements ChargeBillReceiptblService{

    public int getDayId() throws RemoteException {
        return 0;
    }

    public ResultMessage insert(ChargeBillReceiptVO receiptVO) throws RemoteException{
        return ResultMessage.SUCCESS;
    }

    public ResultMessage update(ChargeBillReceiptVO receiptVO) throws RemoteException{
        return ResultMessage.SUCCESS;
    }

    public ResultMessage delete(ChargeBillReceiptVO receiptVO) throws RemoteException{
        return ResultMessage.SUCCESS;
    }

    public ArrayList<ChargeBillReceiptVO> search(ReceiptSearchCondition receiptSearchCondition) throws RemoteException{
        ArrayList<ChargeBillReceiptVO> list = new ArrayList<>();
        LocalDateTime time = LocalDateTime.now();
        List<TransferItemVO> list1 = new ArrayList<>();
        list1.add(new TransferItemVO(1,1,"1"));
        list1.add(new TransferItemVO(1,1,"1"));
        list1.add(new TransferItemVO(1,1,"1"));
        list1.add(new TransferItemVO(1,1,"1"));
        list1.add(new TransferItemVO(1,1,"1"));
        list1.add(new TransferItemVO(1,1,"1"));
        //String dayId, int operatorId, LocalDateTime createTime, LocalDateTime lastModifiedTime, ReceiptState receiptState,int clientID, TransferItemVO[] transferList, double sum
        list.add(new ChargeBillReceiptVO("2111",111,time,time, ReceiptState.APPROVED,111,list1,1111));
        list.add(new ChargeBillReceiptVO("2112",111,time,time, ReceiptState.APPROVED,111,list1,1111));
        list.add(new ChargeBillReceiptVO("2113",111,time,time, ReceiptState.APPROVED,111,list1,1111));
        list.add(new ChargeBillReceiptVO("2114",111,time,time, ReceiptState.APPROVED,111,list1,1111));
        list.add(new ChargeBillReceiptVO("2115",111,time,time, ReceiptState.APPROVED,111,list1,1111));
        list.add(new ChargeBillReceiptVO("2116",111,time,time, ReceiptState.APPROVED,111,list1,1111));
        list.add(new ChargeBillReceiptVO("2117",111,time,time, ReceiptState.APPROVED,111,list1,1111));
        list.add(new ChargeBillReceiptVO("2118",111,time,time, ReceiptState.APPROVED,111,list1,1111));
        list.add(new ChargeBillReceiptVO("2119",111,time,time, ReceiptState.APPROVED,111,list1,1111));
        list.add(new ChargeBillReceiptVO("2120",111,time,time, ReceiptState.APPROVED,111,list1,1111));
        list.add(new ChargeBillReceiptVO("2121",111,time,time, ReceiptState.APPROVED,111,list1,1111));
        return list;
    }

    public ArrayList<ChargeBillReceiptVO> search(RespectiveReceiptSearchCondition respectiveReceiptSearchCondition) throws RemoteException{
        return  null;
    }
}
