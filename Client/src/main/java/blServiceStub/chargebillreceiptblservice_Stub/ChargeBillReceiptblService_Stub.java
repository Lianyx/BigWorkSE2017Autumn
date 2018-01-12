package blServiceStub.chargebillreceiptblservice_Stub;

import blService.billblservice.ChargeBillReceiptblService;
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

/*public class ChargeBillReceiptblService_Stub implements ChargeBillReceiptblService {

    HashSet<ChargeReceiptListVO> set = new HashSet<>();

    int i=111;
    public ChargeBillReceiptblService_Stub(){
        int i=10;

        set.add(new ChargeReceiptListVO("SKD-20170101-000"+(i++), ReceiptState.APPROVED,11,11));
        set.add(new ChargeReceiptListVO("SKD-20170101-000"+(i++), ReceiptState.APPROVED,11,11));
        set.add(new ChargeReceiptListVO("SKD-20170101-000"+(i++), ReceiptState.PENDING,11,11));
        set.add(new ChargeReceiptListVO("SKD-20170101-000"+(i++), ReceiptState.APPROVED,11,11));
        set.add(new ChargeReceiptListVO("SKD-20170101-000"+(i++), ReceiptState.APPROVED,11,11));
        set.add(new ChargeReceiptListVO("SKD-20170101-000"+(i++), ReceiptState.APPROVED,11,11));
        set.add(new ChargeReceiptListVO("SKD-20170101-000"+(i++), ReceiptState.APPROVED,11,11));
        set.add(new ChargeReceiptListVO("SKD-20170101-000"+(i++), ReceiptState.APPROVED,11,11));
        set.add(new ChargeReceiptListVO("SKD-20170101-000"+(i++), ReceiptState.APPROVED,11,11));
        set.add(new ChargeReceiptListVO("SKD-20170101-000"+(i++), ReceiptState.APPROVED,11,11));
        set.add(new ChargeReceiptListVO("SKD-20170101-000"+(i++), ReceiptState.APPROVED,11,11));
        set.add(new ChargeReceiptListVO("SKD-20170101-000"+(i++), ReceiptState.APPROVED,11,11));
        set.add(new ChargeReceiptListVO("SKD-20170101-000"+(i++), ReceiptState.APPROVED,11,11));
        set.add(new ChargeReceiptListVO("SKD-20170101-000"+(i++), ReceiptState.APPROVED,11,11));
        set.add(new ChargeReceiptListVO("SKD-20170101-000"+(i++), ReceiptState.APPROVED,11,11));
        set.add(new ChargeReceiptListVO("SKD-20170101-000"+(i++), ReceiptState.APPROVED,11,11));
        set.add(new ChargeReceiptListVO("SKD-20170101-000"+(i++), ReceiptState.APPROVED,11,11));
        set.add(new ChargeReceiptListVO("SKD-20170101-000"+(i++), ReceiptState.APPROVED,11,11));
        set.add(new ChargeReceiptListVO("SKD-20170101-000"+(i++), ReceiptState.APPROVED,11,11));
        set.add(new ChargeReceiptListVO("SKD-20170101-000"+(i++), ReceiptState.APPROVED,11,11));
        set.add(new ChargeReceiptListVO("SKD-20170101-000"+(i++), ReceiptState.APPROVED,11,11));
        set.add(new ChargeReceiptListVO("SKD-20170101-000"+(i++), ReceiptState.APPROVED,11,11));
        set.add(new ChargeReceiptListVO("SKD-20170101-000"+(i++), ReceiptState.APPROVED,11,11));
        set.add(new ChargeReceiptListVO("SKD-20170101-000"+(i++), ReceiptState.APPROVED,11,11));
        set.add(new ChargeReceiptListVO("SKD-20170101-000"+(i++), ReceiptState.APPROVED,11,11));
        set.add(new ChargeReceiptListVO("SKD-20170101-000"+(i++), ReceiptState.APPROVED,11,11));
        set.add(new ChargeReceiptListVO("SKD-20170101-000"+(i++), ReceiptState.APPROVED,11,11));
        set.add(new ChargeReceiptListVO("SKD-20170101-000"+(i++), ReceiptState.APPROVED,11,11));
        set.add(new ChargeReceiptListVO("SKD-20170101-000"+(i++), ReceiptState.APPROVED,11,11));


    }


    @Override
    public int getDayId() {
        i++;
        return i;
    }

    @Override
    public ResultMessage add(ChargeReceiptVO chargeReceiptVO) {
        set.add(new ChargeReceiptListVO(chargeReceiptVO.getId(),chargeReceiptVO.getReceiptState(),chargeReceiptVO.getOperatorId(),chargeReceiptVO.getSum()));
        return ResultMessage.SUCCESS;
    }

    @Override
    public ResultMessage delete(String id) {
        ChargeReceiptListVO s = null;
        for(ChargeReceiptListVO chargeReceiptListVO:set){
            if(chargeReceiptListVO.getId().equals(id)){
                s = chargeReceiptListVO;
            }
        }
        if(s!=null){
            set.remove(s);
        }
        return ResultMessage.SUCCESS;
    }

    @Override
    public ResultMessage update(ChargeReceiptVO chargeReceiptVO) {
        delete(chargeReceiptVO.getId());
        add(chargeReceiptVO);
        return ResultMessage.SUCCESS;
    }

    @Override
    public Set<ChargeReceiptListVO> search(BillReceiptSearchVO billReceiptSearchVO) {
        HashSet<ChargeReceiptListVO> temp = new HashSet<>();
        for(ChargeReceiptListVO s:set){
            
                for(ReceiptState receiptState:billReceiptSearchVO.getReceiptStates()){
                    if(s.getReceiptState()==receiptState)
                        temp.add(s);
                }
            
        }
        return temp;
    }

    //String id, int operatorId, LocalDateTime createTime, LocalDateTime lastModifiedTime, ReceiptState receiptState, int clientID, List<TransferItemVO> transferList, double sum,boolean isSell
    @Override
    public ChargeReceiptVO showDetail(String id) {
        ArrayList<TransferItemVO> list = new ArrayList();
        list.add(new TransferItemVO(1,1,"1"));
        list.add(new TransferItemVO(2,2,"2"));
        return new ChargeReceiptVO("SKD-20170101-00013",3, LocalDateTime.now(), LocalDateTime.now(),ReceiptState.REJECTED,1,list,3);
        //return new ChargeReceiptVO();
    }

    @Override
    public Set<ChargeReceiptListVO> getALL() {
        HashSet<ChargeReceiptListVO> temp = new HashSet<>();
        for(ChargeReceiptListVO p:set){
            
            temp.add(p);
            
        }
        return temp;
    }

}*/
