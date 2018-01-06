package blServiceStub.stockblservice_Stub;

import blService.stockblService.StockblService;
import util.ReceiptState;
import util.ResultMessage;
import vo.ListGoodsItemVO;
import vo.receiptVO.StockReceiptListVO;
import vo.receiptVO.StockReceiptVO;
import vo.StockSearchVO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class StockblService_Stub implements StockblService {
    HashSet<StockReceiptListVO> set = new HashSet<>();

    int i=111;
    public StockblService_Stub(){
        int i=10;
        set.add(new StockReceiptListVO("JHD-20170101-000"+(i++), ReceiptState.APPROVED,"sabe","sabe",123,true));
        set.add(new StockReceiptListVO("JHD-20170101-000"+(i++), ReceiptState.APPROVED,"sabe","sabe",123,true));
        set.add(new StockReceiptListVO("JHTHD-20170101-000"+(i++), ReceiptState.APPROVED,"sabe","sabe",123,false));
        set.add(new StockReceiptListVO("JHD-20170101-000"+(i++), ReceiptState.REJECTED,"sabe","sabe",123,true));
        set.add(new StockReceiptListVO("JHD-20170101-000"+(i++), ReceiptState.REJECTED,"sabe","sabe",123,true));
        set.add(new StockReceiptListVO("JHTHD-20170101-000"+(i++), ReceiptState.APPROVED,"sabe","sabe",123,false));
        set.add(new StockReceiptListVO("JHTHD-20170101-000"+(i++), ReceiptState.PENDING,"sabe","sabe",123,false));
        set.add(new StockReceiptListVO("JHTHD-20170101-000"+(i++), ReceiptState.PENDING,"sabe","sabe",123,false));
        set.add(new StockReceiptListVO("JHTHD-20170101-000"+(i++), ReceiptState.APPROVED,"sabe","sabe",123,false));
        set.add(new StockReceiptListVO("JHD-20170101-000"+(i++), ReceiptState.DRAFT,"sabe","sabe",123,true));
        set.add(new StockReceiptListVO("JHD-20170101-000"+(i++), ReceiptState.DRAFT,"sabe","sabe",123,true));
        set.add(new StockReceiptListVO("JHD-20170101-000"+(i++), ReceiptState.DRAFT,"sabe","sabe",123,true));
        set.add(new StockReceiptListVO("JHD-20170101-000"+(i++), ReceiptState.DRAFT,"sabe","sabe",123,true));
        set.add(new StockReceiptListVO("JHD-20170101-000"+(i++), ReceiptState.DRAFT,"sabe","sabe",123,true));
        set.add(new StockReceiptListVO("JHD-20170101-000"+(i++), ReceiptState.DRAFT,"sabe","sabe",123,true));
        set.add(new StockReceiptListVO("JHD-20170101-000"+(i++), ReceiptState.DRAFT,"sabe","sabe",123,true));

    }


    @Override
    public int getDayId() {
        i++;
        return i;
    }

    /*
        private String id;
        private ReceiptState receiptState;
        private String memberName;
        private String stockName;
        private int sum;
        private BooleanProperty selected;
        private boolean multiple = true;
        SimpleBooleanProperty isPur = new SimpleBooleanProperty();
        */
    @Override
    public ResultMessage add(StockReceiptVO stockReceiptVO) {
        set.add(new StockReceiptListVO(stockReceiptVO.getId(),stockReceiptVO.getReceiptState(),stockReceiptVO.getMemberName(),stockReceiptVO.getStockName(),stockReceiptVO.getSum(),stockReceiptVO.isPur()));
        return ResultMessage.SUCCESS;
    }

    @Override
    public ResultMessage delete(String id) {
        StockReceiptListVO s = null;
        for(StockReceiptListVO stockReceiptListVO:set){
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
    public ResultMessage update(StockReceiptVO stockReceiptVO) {
        delete(stockReceiptVO.getId());
        add(stockReceiptVO);
        return ResultMessage.SUCCESS;
    }

    @Override
    public Set<StockReceiptListVO> search(StockSearchVO stockSearchVO,boolean isPur) {
        HashSet<StockReceiptListVO> temp = new HashSet<>();
        for(StockReceiptListVO s:set){
            if(s.isPur()==isPur){
                for(ReceiptState receiptState:stockSearchVO.getReceiptStates()){
                    if(s.getReceiptState()==receiptState)
                        temp.add(s);
                }
            }
        }
        return temp;
    }

    @Override
    public Set<StockReceiptListVO> search(String keyword,boolean isPur) {
        HashSet<StockReceiptListVO> temp = new HashSet<>();
        for(StockReceiptListVO s:set){
            if(s.isPur()==isPur){
                temp.add(s);
            }
        }
        return temp;
    }

    @Override
    public StockReceiptVO showDetail(String id) {
        ArrayList<ListGoodsItemVO> list = new ArrayList();
        list.add(new ListGoodsItemVO("a",1,"a",1,1,"a"));
        list.add(new ListGoodsItemVO("a",2,"a",1,1,"a"));
        return new StockReceiptVO("JHD-20170101-00013",3,LocalDateTime.of(2017,1,1,0,0),LocalDateTime.now(),ReceiptState.REJECTED,1,"wad","awda",1,list,"haha",true);
    }

    @Override
    public Set<StockReceiptListVO> getALL(boolean isPur) {
        HashSet<StockReceiptListVO> temp = new HashSet<>();
        for(StockReceiptListVO s:set){
            if(s.isPur()==isPur){
                temp.add(s);
            }
        }
        return temp;
    }

}
