package blServiceStub.salesblservice_Stub;

import blService.salesblService.SalesblService;
import po.receiptPO.SalesSellReceiptPO;
import util.ReceiptSearchCondition;
import util.ReceiptState;
import util.RespectiveReceiptSearchCondition;
import util.ResultMessage;
import vo.ListGoodsItemVO;
import vo.SalesSearchVO;
import vo.receiptVO.*;
import vo.StockSearchVO;

import java.rmi.RemoteException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class SalesblService_Stub  implements SalesblService{


    HashSet<SalesReceiptListVO> set = new HashSet<>();

    int i=111;
    public SalesblService_Stub(){
        int i=10;

        set.add(new SalesReceiptListVO("XSD-20170101-000"+(i++), ReceiptState.APPROVED,"sabe","sabe",123,true));
        set.add(new SalesReceiptListVO("XSD-20170101-000"+(i++), ReceiptState.APPROVED,"sabe","sabe",123,true));
        set.add(new SalesReceiptListVO("XSD-20170101-000"+(i++), ReceiptState.PENDING,"sabe","sabe",123,true));
        set.add(new SalesReceiptListVO("XSD-20170101-000"+(i++), ReceiptState.APPROVED,"sabe","sabe",123,true));
        set.add(new SalesReceiptListVO("XSD-20170101-000"+(i++), ReceiptState.APPROVED,"sabe","sabe",123,true));
        set.add(new SalesReceiptListVO("XSD-20170101-000"+(i++), ReceiptState.APPROVED,"sabe","sabe",123,true));
        set.add(new SalesReceiptListVO("XSD-20170101-000"+(i++), ReceiptState.APPROVED,"sabe","sabe",123,true));
        set.add(new SalesReceiptListVO("XSD-20170101-000"+(i++), ReceiptState.APPROVED,"sabe","sabe",123,true));
        set.add(new SalesReceiptListVO("XSD-20170101-000"+(i++), ReceiptState.APPROVED,"sabe","sabe",123,true));
        set.add(new SalesReceiptListVO("XSD-20170101-000"+(i++), ReceiptState.APPROVED,"sabe","sabe",123,true));
        set.add(new SalesReceiptListVO("XSD-20170101-000"+(i++), ReceiptState.APPROVED,"sabe","sabe",123,true));
        set.add(new SalesReceiptListVO("XSD-20170101-000"+(i++), ReceiptState.APPROVED,"sabe","sabe",123,true));
        set.add(new SalesReceiptListVO("XSD-20170101-000"+(i++), ReceiptState.APPROVED,"sabe","sabe",123,true));
        set.add(new SalesReceiptListVO("XSD-20170101-000"+(i++), ReceiptState.APPROVED,"sabe","sabe",123,true));
        set.add(new SalesReceiptListVO("XSD-20170101-000"+(i++), ReceiptState.APPROVED,"sabe","sabe",123,true));
        set.add(new SalesReceiptListVO("XSD-20170101-000"+(i++), ReceiptState.APPROVED,"sabe","sabe",123,true));
        set.add(new SalesReceiptListVO("XSD-20170101-000"+(i++), ReceiptState.APPROVED,"sabe","sabe",123,true));
        set.add(new SalesReceiptListVO("XSD-20170101-000"+(i++), ReceiptState.APPROVED,"sabe","sabe",123,true));
        set.add(new SalesReceiptListVO("XSD-20170101-000"+(i++), ReceiptState.APPROVED,"sabe","sabe",123,true));
        set.add(new SalesReceiptListVO("XSD-20170101-000"+(i++), ReceiptState.APPROVED,"sabe","sabe",123,true));
        set.add(new SalesReceiptListVO("XSD-20170101-000"+(i++), ReceiptState.APPROVED,"sabe","sabe",123,true));
        set.add(new SalesReceiptListVO("XSTHD-20170101-000"+(i++), ReceiptState.APPROVED,"sabe","sabe",123,false));
        set.add(new SalesReceiptListVO("XSTHD-20170101-000"+(i++), ReceiptState.APPROVED,"sabe","sabe",123,false));
        set.add(new SalesReceiptListVO("XSTHD-20170101-000"+(i++), ReceiptState.APPROVED,"sabe","sabe",123,false));
        set.add(new SalesReceiptListVO("XSTHD-20170101-000"+(i++), ReceiptState.APPROVED,"sabe","sabe",123,false));
        set.add(new SalesReceiptListVO("XSTHD-20170101-000"+(i++), ReceiptState.APPROVED,"sabe","sabe",123,false));
        set.add(new SalesReceiptListVO("XSTHD-20170101-000"+(i++), ReceiptState.APPROVED,"sabe","sabe",123,false));
        set.add(new SalesReceiptListVO("XSTHD-20170101-000"+(i++), ReceiptState.APPROVED,"sabe","sabe",123,false));
        set.add(new SalesReceiptListVO("XSTHD-20170101-000"+(i++), ReceiptState.APPROVED,"sabe","sabe",123,false));


    }


    @Override
    public int getDayId() {
        i++;
        return i;
    }

    @Override
    public SalesReceiptVO getNew() throws RemoteException {
        return null;
    }

    @Override
    public ResultMessage insert(SalesReceiptVO stockReceiptVO) {
        set.add(new SalesReceiptListVO(stockReceiptVO.getId(),stockReceiptVO.getReceiptState(),stockReceiptVO.getMemberName(),stockReceiptVO.getStockName(),stockReceiptVO.getOriginSum()-stockReceiptVO.getDiscountAmount(),stockReceiptVO instanceof SalesSellReceiptVO));
        return ResultMessage.SUCCESS;
    }

    @Override
    public ResultMessage delete(String id) {
        SalesReceiptListVO s = null;
        for(SalesReceiptListVO stockReceiptListVO:set){
            if(stockReceiptListVO.getId().equals(id)){
                s = stockReceiptListVO;
            }
        }
        if(s!=null){
            set.remove(s);
        }
        return ResultMessage.SUCCESS;
    }

    @Override
    public ResultMessage update(SalesReceiptVO stockReceiptVO) {
        delete(stockReceiptVO.getId());
        insert(stockReceiptVO);
        return ResultMessage.SUCCESS;
    }

    @Override
    public ResultMessage delete(SalesReceiptVO receiptVO) throws RemoteException {
        return null;
    }

    @Override
    public ArrayList<SalesReceiptVO> search(ReceiptSearchCondition receiptSearchCondition) throws RemoteException {
        return null;
    }

    @Override
    public ArrayList<SalesReceiptVO> search(RespectiveReceiptSearchCondition respectiveReceiptSearchCondition) throws RemoteException {
        return null;
    }

    @Override
    public Set<SalesReceiptListVO> search(SalesSearchVO stockSearchVO, boolean isPur) {
        HashSet<SalesReceiptListVO> temp = new HashSet<>();
        for(SalesReceiptListVO s:set){
            if(s.isSell()==isPur){
                for(ReceiptState receiptState:stockSearchVO.getReceiptStates()){
                    if(s.getReceiptState()==receiptState)
                        temp.add(s);
                }
            }
        }
        return temp;
    }
    @Override
    public SalesReceiptVO showDetail(String id) {
        ArrayList<ListGoodsItemVO> list = new ArrayList();
        list.add(new ListGoodsItemVO("a",1,"a",1,1,"a"));
        list.add(new ListGoodsItemVO("a",2,"a",1,1,"a"));
        return new SalesRetReceiptVO("XSD-20170101-00013",3, LocalDateTime.of(2017,1,1,0,0), LocalDateTime.now(),ReceiptState.REJECTED,
                1, "wad","laji","stock",list,
                "haha",13,13,25);
    }

    @Override
    public Set<SalesReceiptListVO> getALL(boolean isPur) {
        HashSet<SalesReceiptListVO> temp = new HashSet<>();
        for(SalesReceiptListVO s:set){
            if(s.isSell()==isPur){
                temp.add(s);
            }
        }
        return temp;
    }

}
