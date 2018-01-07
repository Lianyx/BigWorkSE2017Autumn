package vo.inventoryVO;

import blService.checkblService.CheckInfo;
import blService.checkblService.ReceiptblService;
import javafx.scene.Node;
import po.receiptPO.ReceiptPO;
import util.ReceiptState;
import vo.receiptVO.ReceiptVO;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.time.LocalDateTime;
import java.util.List;

public class InventoryWarningReceiptVO extends InventoryReceiptVO {
    private List<InventoryWarningGoodsItemVO> goodsList;

    public InventoryWarningReceiptVO(String id, int operatorId, LocalDateTime createTime, LocalDateTime lastModifiedTime,
                                     ReceiptState receiptState, List<InventoryWarningGoodsItemVO> goodsList) {
        super(id, operatorId, createTime, lastModifiedTime, receiptState);
        this.goodsList = goodsList;
    }

    @Override
    protected String getCodeName() {
        return "BJD";
    }

    @Override
    public InventoryWarningReceiptVO toPO() {
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
