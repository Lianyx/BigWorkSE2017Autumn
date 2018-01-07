package blServiceStub.inventoryblservice_Stub;

import blService.inventoryblService.InventoryblService;
import util.ReceiptSearchCondition;
import util.ReceiptState;
import util.RespectiveReceiptSearchCondition;
import util.ResultMessage;
import vo.ListGoodsItemVO;
import vo.inventoryVO.inventoryReceiptVO.InventoryReceiptListVO;
import vo.inventoryVO.inventoryReceiptVO.InventoryReceiptType;
import vo.inventoryVO.inventoryReceiptVO.InventoryReceiptVO;
import vo.inventoryVO.inventoryReceiptVO.InventorySearchVO;

import java.rmi.RemoteException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class InventoryblService_Stub implements InventoryblService {

    HashSet<InventoryReceiptListVO> set = new HashSet<>();
    int i = 111;

    public InventoryblService_Stub() {
        int i=10;

        set.add(new InventoryReceiptListVO("ZSD-20170101-000"+(i++), ReceiptState.APPROVED,"sabe",true,InventoryReceiptType.InventoryGift));
        set.add(new InventoryReceiptListVO("ZSD-20170101-000"+(i++), ReceiptState.APPROVED,"sabe",true,InventoryReceiptType.InventoryGift));
        set.add(new InventoryReceiptListVO("ZSD-20170101-000"+(i++), ReceiptState.APPROVED,"sabe",true,InventoryReceiptType.InventoryGift));
        set.add(new InventoryReceiptListVO("ZSD-20170101-000"+(i++), ReceiptState.APPROVED,"sabe",true,InventoryReceiptType.InventoryGift));
        set.add(new InventoryReceiptListVO("ZSD-20170101-000"+(i++), ReceiptState.APPROVED,"sabe",true,InventoryReceiptType.InventoryGift));
        set.add(new InventoryReceiptListVO("ZSD-20170101-000"+(i++), ReceiptState.APPROVED,"sabe",true,InventoryReceiptType.InventoryGift));
        set.add(new InventoryReceiptListVO("ZSD-20170101-000"+(i++), ReceiptState.APPROVED,"sabe",true,InventoryReceiptType.InventoryGift));
        set.add(new InventoryReceiptListVO("ZSD-20170101-000"+(i++), ReceiptState.APPROVED,"sabe",true,InventoryReceiptType.InventoryGift));
    }

    @Override
    public int getDayId() {
        i++;
        return i;
    }

    @Override
    public Set<InventoryReceiptListVO> search(InventorySearchVO stockSearchVO, InventoryReceiptType receiptType) {
        return set;
    }

    @Override
    public Set<InventoryReceiptListVO> search(String keyword, InventoryReceiptType receiptType) {
        return null;
    }

    @Override
    public InventoryReceiptVO showDetail(String id) {
        ArrayList<ListGoodsItemVO> list = new ArrayList();
        list.add(new ListGoodsItemVO("a",1,"a",1,1,"a"));
        list.add(new ListGoodsItemVO("a",2,"a",1,1,"a"));
        return new InventoryReceiptVO("JHD-20170101-00013",3,LocalDateTime.of(2017,1,1,0,0),LocalDateTime.now(),ReceiptState.REJECTED,1,"wad",list,"awda",InventoryReceiptType.InventoryGift);

    }

    @Override
    public Set<InventoryReceiptListVO> getALL(InventoryReceiptType receiptType) {
        return set;
    }

    @Override
    public InventoryReceiptVO getNew() throws RemoteException {
        return null;
    }

    @Override
    public ResultMessage insert(InventoryReceiptVO receiptVO) throws RemoteException {
        return null;
    }

    @Override
    public ResultMessage update(InventoryReceiptVO receiptVO) throws RemoteException {
        return null;
    }

    @Override
    public ResultMessage delete(InventoryReceiptVO receiptVO) throws RemoteException {
        return null;
    }

    @Override
    public ArrayList<InventoryReceiptVO> search(ReceiptSearchCondition receiptSearchCondition) throws RemoteException {
        return null;
    }

    @Override
    public ArrayList<InventoryReceiptVO> search(RespectiveReceiptSearchCondition respectiveReceiptSearchCondition) throws RemoteException {
        return null;
    }
}
