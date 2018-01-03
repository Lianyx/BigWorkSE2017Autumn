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

public class InventoryDamageReceiptVO extends InventoryReceiptVO {
    private List<InventoryReceiptGoodsItemVO> goodsList;

    public InventoryDamageReceiptVO(String id, int operatorId, LocalDateTime createTime, LocalDateTime lastModifiedTime,
                                    ReceiptState receiptState, List<InventoryReceiptGoodsItemVO> goodsList) {
        super(id, operatorId, createTime, lastModifiedTime, receiptState);
        this.goodsList = goodsList;
    }

    @Override
    protected String getCodeName() {
        return "BSD";
    }

    @Override
    public InventoryDamageReceiptVO toPO() {
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

