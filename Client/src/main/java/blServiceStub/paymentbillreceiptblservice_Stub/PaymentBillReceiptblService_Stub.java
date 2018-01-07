package blServiceStub.paymentbillreceiptblservice_Stub;

import blService.billblService.PaymentBillReceiptblService;
import ui.accountantui.PaymentReceiptListPane;
import util.ReceiptSearchCondition;
import util.ReceiptState;
import util.RespectiveReceiptSearchCondition;
import util.ResultMessage;
import vo.ListGoodsItemVO;
import vo.SalesSearchVO;
import vo.billReceiptVO.BillReceiptSearchVO;
import vo.billReceiptVO.PaymentReceiptListVO;
import vo.billReceiptVO.PaymentReceiptVO;
import vo.billReceiptVO.TransferItemVO;
import vo.receiptVO.SalesReceiptListVO;
import vo.receiptVO.SalesReceiptVO;

import java.rmi.RemoteException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PaymentBillReceiptblService_Stub implements PaymentBillReceiptblService{

    HashSet<PaymentReceiptListVO> set = new HashSet<>();

    int i=111;
    public PaymentBillReceiptblService_Stub(){
        int i=10;

        set.add(new PaymentReceiptListVO("XSD-20170101-000"+(i++), ReceiptState.APPROVED,11,11,true));
        set.add(new PaymentReceiptListVO("XSD-20170101-000"+(i++), ReceiptState.APPROVED,11,11,true));
        set.add(new PaymentReceiptListVO("XSD-20170101-000"+(i++), ReceiptState.PENDING,11,11,true));
        set.add(new PaymentReceiptListVO("XSD-20170101-000"+(i++), ReceiptState.APPROVED,11,11,true));
        set.add(new PaymentReceiptListVO("XSD-20170101-000"+(i++), ReceiptState.APPROVED,11,11,true));
        set.add(new PaymentReceiptListVO("XSD-20170101-000"+(i++), ReceiptState.APPROVED,11,11,true));
        set.add(new PaymentReceiptListVO("XSD-20170101-000"+(i++), ReceiptState.APPROVED,11,11,true));
        set.add(new PaymentReceiptListVO("XSD-20170101-000"+(i++), ReceiptState.APPROVED,11,11,true));
        set.add(new PaymentReceiptListVO("XSD-20170101-000"+(i++), ReceiptState.APPROVED,11,11,true));
        set.add(new PaymentReceiptListVO("XSD-20170101-000"+(i++), ReceiptState.APPROVED,11,11,true));
        set.add(new PaymentReceiptListVO("XSD-20170101-000"+(i++), ReceiptState.APPROVED,11,11,true));
        set.add(new PaymentReceiptListVO("XSD-20170101-000"+(i++), ReceiptState.APPROVED,11,11,true));
        set.add(new PaymentReceiptListVO("XSD-20170101-000"+(i++), ReceiptState.APPROVED,11,11,true));
        set.add(new PaymentReceiptListVO("XSD-20170101-000"+(i++), ReceiptState.APPROVED,11,11,true));
        set.add(new PaymentReceiptListVO("XSD-20170101-000"+(i++), ReceiptState.APPROVED,11,11,true));
        set.add(new PaymentReceiptListVO("XSD-20170101-000"+(i++), ReceiptState.APPROVED,11,11,true));
        set.add(new PaymentReceiptListVO("XSD-20170101-000"+(i++), ReceiptState.APPROVED,11,11,true));
        set.add(new PaymentReceiptListVO("XSD-20170101-000"+(i++), ReceiptState.APPROVED,11,11,true));
        set.add(new PaymentReceiptListVO("XSD-20170101-000"+(i++), ReceiptState.APPROVED,11,11,true));
        set.add(new PaymentReceiptListVO("XSD-20170101-000"+(i++), ReceiptState.APPROVED,11,11,true));
        set.add(new PaymentReceiptListVO("XSD-20170101-000"+(i++), ReceiptState.APPROVED,11,11,true));
        set.add(new PaymentReceiptListVO("XSTHD-20170101-000"+(i++), ReceiptState.APPROVED,11,11,false));
        set.add(new PaymentReceiptListVO("XSTHD-20170101-000"+(i++), ReceiptState.APPROVED,11,11,false));
        set.add(new PaymentReceiptListVO("XSTHD-20170101-000"+(i++), ReceiptState.APPROVED,11,11,false));
        set.add(new PaymentReceiptListVO("XSTHD-20170101-000"+(i++), ReceiptState.APPROVED,11,11,false));
        set.add(new PaymentReceiptListVO("XSTHD-20170101-000"+(i++), ReceiptState.APPROVED,11,11,false));
        set.add(new PaymentReceiptListVO("XSTHD-20170101-000"+(i++), ReceiptState.APPROVED,11,11,false));
        set.add(new PaymentReceiptListVO("XSTHD-20170101-000"+(i++), ReceiptState.APPROVED,11,11,false));
        set.add(new PaymentReceiptListVO("XSTHD-20170101-000"+(i++), ReceiptState.APPROVED,11,11,false));


    }


    @Override
    public int getDayId() {
        i++;
        return i;
    }

    @Override
    public ResultMessage add(PaymentReceiptVO paymentReceiptVO) {
        set.add(new PaymentReceiptListVO(paymentReceiptVO.getId(),paymentReceiptVO.getReceiptState(),paymentReceiptVO.getOperatorId(),paymentReceiptVO.getSum(),paymentReceiptVO.isSell()));
        return ResultMessage.SUCCESS;
    }

    @Override
    public ResultMessage delete(String id) {
        PaymentReceiptListVO s = null;
        for(PaymentReceiptListVO paymentReceiptListVO:set){
            if(paymentReceiptListVO.getId().equals(id)){
                s = paymentReceiptListVO;
            }
        }
        if(s!=null){
            set.remove(s);
        }
        return ResultMessage.SUCCESS;
    }

    @Override
    public ResultMessage update(PaymentReceiptVO paymentReceiptVO) {
        delete(paymentReceiptVO.getId());
        add(paymentReceiptVO);
        return ResultMessage.SUCCESS;
    }

    @Override
    public Set<PaymentReceiptListVO> search(BillReceiptSearchVO billReceiptSearchVO, boolean isPur) {
        HashSet<PaymentReceiptListVO> temp = new HashSet<>();
        for(PaymentReceiptListVO s:set){
            if(s.isSell()==isPur){
                for(ReceiptState receiptState:billReceiptSearchVO.getReceiptStates()){
                    if(s.getReceiptState()==receiptState)
                        temp.add(s);
                }
            }
        }
        return temp;
    }

    //String id, int operatorId, LocalDateTime createTime, LocalDateTime lastModifiedTime, ReceiptState receiptState, int clientID, List<TransferItemVO> transferList, double sum,boolean isSell
    @Override
    public PaymentReceiptVO showDetail(String id) {
        ArrayList<TransferItemVO> list = new ArrayList();
        list.add(new TransferItemVO(1,1,"1"));
        list.add(new TransferItemVO(2,2,"2"));
        return new PaymentReceiptVO("XSD-20170101-00013",3, LocalDateTime.now(), LocalDateTime.now(),ReceiptState.REJECTED,1,list,3,true);
        //return new PaymentReceiptVO();
    }

    @Override
    public Set<PaymentReceiptListVO> getALL(boolean isPur) {
        HashSet<PaymentReceiptListVO> temp = new HashSet<>();
        for(PaymentReceiptListVO p:set){
            if(p.isSell()==isPur){
                temp.add(p);
            }
        }
        return temp;
    }

}