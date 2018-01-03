package vo.inventoryVO;

import blService.checkblService.ReceiptblService;
import javafx.scene.Node;
import po.receiptPO.ReceiptPO;
import util.ReceiptState;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.time.LocalDateTime;
import java.util.List;

public class InventoryGiftReceiptVO extends InventoryReceiptVO {
    private List<InventroyGiftGoodsItemVO> list;


    public InventoryGiftReceiptVO(String id, int operatorId, LocalDateTime createTime, LocalDateTime lastModifiedTime,
                                  ReceiptState receiptState, List<InventroyGiftGoodsItemVO> list) {
        super(id, operatorId, createTime, lastModifiedTime, receiptState);
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
    public ReceiptblService getService() throws RemoteException, NotBoundException, MalformedURLException {
        return null;
    }

    @Override
    public Node getDetailPane() {
        return null;
    }
}
