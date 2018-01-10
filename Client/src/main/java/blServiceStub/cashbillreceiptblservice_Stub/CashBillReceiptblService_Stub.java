package blServiceStub.cashbillreceiptblservice_Stub;

import blService.billblservice.CashBillReceiptblService;
import util.ReceiptSearchCondition;
import util.ReceiptState;
import util.RespectiveReceiptSearchCondition;
import util.ResultMessage;
import vo.billReceiptVO.*;

import java.rmi.RemoteException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*public class CashBillReceiptblService_Stub implements CashBillReceiptblService {

    HashSet<CashReceiptListVO> set = new HashSet<>();

    int i=111;
    public CashBillReceiptblService_Stub(){
        int i=10;

        set.add(new CashReceiptListVO("XJFYD-20170101-000"+(i++), ReceiptState.APPROVED,11,11));
        set.add(new CashReceiptListVO("XJFYD-20170101-000"+(i++), ReceiptState.APPROVED,11,11));
        set.add(new CashReceiptListVO("XJFYD-20170101-000"+(i++), ReceiptState.PENDING,11,11));
        set.add(new CashReceiptListVO("XJFYD-20170101-000"+(i++), ReceiptState.APPROVED,11,11));
        set.add(new CashReceiptListVO("XJFYD-20170101-000"+(i++), ReceiptState.APPROVED,11,11));
        set.add(new CashReceiptListVO("XJFYD-20170101-000"+(i++), ReceiptState.APPROVED,11,11));
        set.add(new CashReceiptListVO("XJFYD-20170101-000"+(i++), ReceiptState.APPROVED,11,11));
        set.add(new CashReceiptListVO("XJFYD-20170101-000"+(i++), ReceiptState.APPROVED,11,11));
        set.add(new CashReceiptListVO("XJFYD-20170101-000"+(i++), ReceiptState.APPROVED,11,11));
        set.add(new CashReceiptListVO("XJFYD-20170101-000"+(i++), ReceiptState.APPROVED,11,11));
        set.add(new CashReceiptListVO("XJFYD-20170101-000"+(i++), ReceiptState.APPROVED,11,11));
        set.add(new CashReceiptListVO("XJFYD-20170101-000"+(i++), ReceiptState.APPROVED,11,11));
        set.add(new CashReceiptListVO("XJFYD-20170101-000"+(i++), ReceiptState.APPROVED,11,11));
        set.add(new CashReceiptListVO("XJFYD-20170101-000"+(i++), ReceiptState.APPROVED,11,11));
        set.add(new CashReceiptListVO("XJFYD-20170101-000"+(i++), ReceiptState.APPROVED,11,11));
        set.add(new CashReceiptListVO("XJFYD-20170101-000"+(i++), ReceiptState.APPROVED,11,11));
        set.add(new CashReceiptListVO("XJFYD-20170101-000"+(i++), ReceiptState.APPROVED,11,11));
        set.add(new CashReceiptListVO("XJFYD-20170101-000"+(i++), ReceiptState.APPROVED,11,11));
        set.add(new CashReceiptListVO("XJFYD-20170101-000"+(i++), ReceiptState.APPROVED,11,11));
        set.add(new CashReceiptListVO("XJFYD-20170101-000"+(i++), ReceiptState.APPROVED,11,11));
        set.add(new CashReceiptListVO("XJFYD-20170101-000"+(i++), ReceiptState.APPROVED,11,11));
        set.add(new CashReceiptListVO("XJFYD-20170101-000"+(i++), ReceiptState.APPROVED,11,11));
        set.add(new CashReceiptListVO("XJFYD-20170101-000"+(i++), ReceiptState.APPROVED,11,11));
        set.add(new CashReceiptListVO("XJFYD-20170101-000"+(i++), ReceiptState.APPROVED,11,11));
        set.add(new CashReceiptListVO("XJFYD-20170101-000"+(i++), ReceiptState.APPROVED,11,11));
        set.add(new CashReceiptListVO("XJFYD-20170101-000"+(i++), ReceiptState.APPROVED,11,11));
        set.add(new CashReceiptListVO("XJFYD-20170101-000"+(i++), ReceiptState.APPROVED,11,11));
        set.add(new CashReceiptListVO("XJFYD-20170101-000"+(i++), ReceiptState.APPROVED,11,11));
        set.add(new CashReceiptListVO("XJFYD-20170101-000"+(i++), ReceiptState.APPROVED,11,11));


    }


    @Override
    public int getDayId() {
        i++;
        return i;
    }

    @Override
    public ResultMessage add(CashReceiptVO cashReceiptVO) {
        set.add(new CashReceiptListVO(cashReceiptVO.getId(),cashReceiptVO.getReceiptState(),cashReceiptVO.getOperatorId(),cashReceiptVO.getTotal()));
        return ResultMessage.SUCCESS;
    }

    @Override
    public ResultMessage delete(String id) {
        CashReceiptListVO s = null;
        for(CashReceiptListVO cashReceiptListVO:set){
            if(cashReceiptListVO.getId().equals(id)){
                s = cashReceiptListVO;
            }
        }
        if(s!=null){
            set.remove(s);
        }
        return ResultMessage.SUCCESS;
    }

    @Override
    public ResultMessage update(CashReceiptVO cashReceiptVO) {
        delete(cashReceiptVO.getId());
        add(cashReceiptVO);
        return ResultMessage.SUCCESS;
    }

    @Override
    public Set<CashReceiptListVO> search(BillReceiptSearchVO billReceiptSearchVO) {
        HashSet<CashReceiptListVO> temp = new HashSet<>();
        for(CashReceiptListVO s:set){
            for(ReceiptState receiptState:billReceiptSearchVO.getReceiptStates()){
                if(s.getReceiptState()==receiptState)
                    temp.add(s);
            }
        }
        return temp;
    }

    //String id, int operatorId, LocalDateTime createTime, LocalDateTime lastModifiedTime, ReceiptState receiptState, int clientID, List<TransferItemVO> transferList, double sum,boolean isSell
    @Override
    public CashReceiptVO showDetail(String id) {
        ArrayList<CashItemVO> list = new ArrayList();
        list.add(new CashItemVO("1",1,"1"));
        list.add(new CashItemVO("2",2,"2"));
        return new CashReceiptVO("XJFYD-20170101-00013",3, LocalDateTime.now(), LocalDateTime.now(),ReceiptState.REJECTED,1,3,list);
        //return new CashReceiptVO();
    }

    @Override
    public Set<CashReceiptListVO> getALL() {
        HashSet<CashReceiptListVO> temp = new HashSet<>();
        for(CashReceiptListVO p:set){
            temp.add(p);
        }
        return temp;
    }

}*/
