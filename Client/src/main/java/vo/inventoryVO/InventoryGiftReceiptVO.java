package vo.inventoryVO;

import blService.checkblService.CheckInfo;
import blService.checkblService.ReceiptblService;
import javafx.scene.Node;
import po.receiptPO.ReceiptPO;
import util.ReceiptState;
import vo.inventoryVO.inventoryReceiptVO.InventoryReceiptType;
import vo.inventoryVO.inventoryReceiptVO.InventoryReceiptVO;
import vo.inventoryVO.inventoryReceiptVO.ReceiptGoodsItemVO;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class InventoryGiftReceiptVO extends InventoryReceiptVO {
    private List<InventroyGiftGoodsItemVO> list;


    public InventoryGiftReceiptVO(String id, int operatorId, LocalDateTime createTime, LocalDateTime lastModifiedTime, String memberName,
                                  ArrayList<ReceiptGoodsItemVO> items, String comment , ReceiptState receiptState, InventoryReceiptType receiptType) {
        super(id, operatorId, createTime, lastModifiedTime, receiptState,memberName,items,comment,receiptType);
        this.list = list;
    }

    @Override
    protected String getCodeName() {
        return "ZSD";
    }

    @Override
    public InventoryGiftReceiptVO toPO() {
       // return new InventoryGiftReceiptPO(Integer.parseInt(getId()),getOperatorId(),getCreateTime(),getLastModifiedTime(),getReceiptState()
       // ,getClerkName(), list.toArray(new InventoryReceiptGoodsItemPO[list.size()]),getComment());
        return null;
    }

    @Override
    public CheckInfo<InventoryGiftReceiptVO> getService() throws RemoteException, NotBoundException, MalformedURLException {
        return null;
    }

    @Override
    public Node getDetailPane() {
        return null;
    }
}
