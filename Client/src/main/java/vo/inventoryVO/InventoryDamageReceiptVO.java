package vo.inventoryVO;

import blService.checkblService.CheckInfo;
import blService.checkblService.ReceiptblService;
import javafx.scene.Node;
import po.receiptPO.ReceiptPO;
import util.ReceiptState;
import vo.inventoryVO.inventoryReceiptVO.InventoryReceiptType;
import vo.inventoryVO.inventoryReceiptVO.InventoryReceiptVO;
import vo.inventoryVO.inventoryReceiptVO.ReceiptGoodsItemVO;
import vo.receiptVO.ReceiptVO;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class InventoryDamageReceiptVO extends InventoryReceiptVO {
    private List<InventoryReceiptGoodsItemVO> goodsList;

    public InventoryDamageReceiptVO(String id, int operatorId, LocalDateTime createTime, LocalDateTime lastModifiedTime, String memberName,
                                    ArrayList<ReceiptGoodsItemVO> items, String comment , ReceiptState receiptState, InventoryReceiptType receiptType) {
        super(id, operatorId, createTime, lastModifiedTime, receiptState,memberName,items,comment,receiptType);
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
    public <TV extends ReceiptVO> CheckInfo<TV> getService() throws RemoteException, NotBoundException, MalformedURLException {
        return null;
    }

    @Override
    public Node getDetailPane() {
        return null;
    }


}

