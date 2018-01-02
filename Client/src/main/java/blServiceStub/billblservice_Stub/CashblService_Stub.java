package blServiceStub.billblservice_Stub;

import blService.billblService.CashBillReceiptblService;
import com.sun.org.apache.regexp.internal.RE;
import util.ReceiptSearchCondition;
import util.ReceiptState;
import util.RespectiveReceiptSearchCondition;
import util.ResultMessage;
import vo.billReceiptVO.CashBillReceiptVO;
import vo.billReceiptVO.ChargeBillReceiptVO;

import java.rmi.RemoteException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CashblService_Stub implements CashBillReceiptblService{

    public int getDayId() throws RemoteException{
        return 0;
    }

    @Override
    public CashBillReceiptVO getNew() throws RemoteException {
        return null;
    }

    public ResultMessage insert(CashBillReceiptVO receiptVO) throws RemoteException{
        return ResultMessage.SUCCESS;
    }

    public ResultMessage update(CashBillReceiptVO receiptVO) throws RemoteException{
        return ResultMessage.SUCCESS;
    }

    public ResultMessage delete(CashBillReceiptVO receiptVO) throws RemoteException{
        return ResultMessage.SUCCESS;
    }

    public ArrayList<CashBillReceiptVO> search(ReceiptSearchCondition receiptSearchCondition) throws RemoteException{
        ArrayList<CashBillReceiptVO> list = new ArrayList<>();
        LocalDateTime time = LocalDateTime.MIN;
        //String dayId, int operatorId, LocalDateTime createTime, LocalDateTime lastModifiedTime, ReceiptState receiptState,int clientID, TransferItemVO[] transferList, double sum
        //String id, int operatorId, LocalDateTime createTime, LocalDateTime lastModifiedTime, ReceiptState receiptState, int accountID, double total, CashItemVO[] itemList
        list.add(new CashBillReceiptVO("3111",111,time,time, ReceiptState.APPROVED,111,1111,null));
        list.add(new CashBillReceiptVO("3112",111,time,time, ReceiptState.APPROVED,111,1111,null));
        list.add(new CashBillReceiptVO("3113",111,time,time, ReceiptState.APPROVED,111,1111,null));
        list.add(new CashBillReceiptVO("3114",111,time,time, ReceiptState.APPROVED,111,1111,null));
        list.add(new CashBillReceiptVO("3115",111,time,time, ReceiptState.APPROVED,111,1111,null));
        list.add(new CashBillReceiptVO("3116",111,time,time, ReceiptState.APPROVED,111,1111,null));
        list.add(new CashBillReceiptVO("3117",111,time,time, ReceiptState.APPROVED,111,1111,null));
        list.add(new CashBillReceiptVO("3118",111,time,time, ReceiptState.APPROVED,111,1111,null));
        list.add(new CashBillReceiptVO("3119",111,time,time, ReceiptState.APPROVED,111,1111,null));
        list.add(new CashBillReceiptVO("3120",111,time,time, ReceiptState.APPROVED,111,1111,null));
        list.add(new CashBillReceiptVO("3121",111,time,time, ReceiptState.APPROVED,111,1111,null));
        return list;
    }

    public ArrayList<CashBillReceiptVO> search(RespectiveReceiptSearchCondition respectiveReceiptSearchCondition) throws RemoteException{
        return null;
    }

}
