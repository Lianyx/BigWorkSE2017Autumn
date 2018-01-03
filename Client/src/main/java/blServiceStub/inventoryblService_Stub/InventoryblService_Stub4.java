package blServiceStub.inventoryblService_Stub;

import blService.inventoryblService.InventoryblService;
import util.ReceiptSearchCondition;
import util.ReceiptState;
import util.RespectiveReceiptSearchCondition;
import util.ResultMessage;
import vo.inventoryVO.uiReceipt.GiftuiGoodsItemVO;
import vo.inventoryVO.InventoryGiftReceiptVO;
import vo.inventoryVO.InventoryReceiptVO;
import vo.inventoryVO.uiReceipt.InventoryGiftuiVO;

import java.rmi.RemoteException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

public class InventoryblService_Stub4 implements InventoryblService {
    Set<InventoryGiftuiVO> set = new TreeSet<>();

    public InventoryblService_Stub4() {
        Set<GiftuiGoodsItemVO> goodsSet = new TreeSet<>();
        goodsSet.add(new GiftuiGoodsItemVO("X灯","123",50,60,80.0,"无"));
        goodsSet.add(new GiftuiGoodsItemVO("X灯","124",50,60,80.0,"无"));
        goodsSet.add(new GiftuiGoodsItemVO("X灯","125",50,60,80.0,"无"));
        goodsSet.add(new GiftuiGoodsItemVO("X灯","126",50,60,80.0,"无"));
        goodsSet.add(new GiftuiGoodsItemVO("X灯","127",50,60,80.0,"无"));
        goodsSet.add(new GiftuiGoodsItemVO("X灯","128",50,60,80.0,"无"));
        goodsSet.add(new GiftuiGoodsItemVO("X灯","129",50,60,80.0,"无"));
        goodsSet.add(new GiftuiGoodsItemVO("X灯","130",50,60,80.0,"无"));
        goodsSet.add(new GiftuiGoodsItemVO("X灯","131",50,60,80.0,"无"));
        goodsSet.add(new GiftuiGoodsItemVO("X灯","132",50,60,80.0,"无"));
        goodsSet.add(new GiftuiGoodsItemVO("X灯","133",50,60,80.0,"无"));
        set.add(new InventoryGiftuiVO("123",321,LocalDateTime.now(),LocalDateTime.now(), "无",ReceiptState.PENDING,goodsSet));
        set.add(new InventoryGiftuiVO("124",321,LocalDateTime.now(),LocalDateTime.now(), "无",ReceiptState.PENDING,goodsSet));
        set.add(new InventoryGiftuiVO("125",321,LocalDateTime.now(),LocalDateTime.now(), "无",ReceiptState.PENDING,goodsSet));
        set.add(new InventoryGiftuiVO("126",321,LocalDateTime.now(),LocalDateTime.now(), "无",ReceiptState.PENDING,goodsSet));
        set.add(new InventoryGiftuiVO("127",321,LocalDateTime.now(),LocalDateTime.now(), "无",ReceiptState.PENDING,goodsSet));
        set.add(new InventoryGiftuiVO("128",321,LocalDateTime.now(),LocalDateTime.now(), "无",ReceiptState.PENDING,goodsSet));
    }

    @Override
    public int getDayId() throws RemoteException {
        return 0;
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

        public Set<InventoryGiftuiVO> getAll(){
        return set;
    }
}
