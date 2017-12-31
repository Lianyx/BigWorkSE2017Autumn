package vo.receiptVO;

import blService.checkblService.ReceiptblService;
import blService.promotionblService.PromotionblService;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.Node;
import po.promotionPO.PromotionPO;
import po.receiptPO.ReceiptPO;
import ui.managerui.promotion.PromotionDetailPane;
import util.ReceiptState;
import vo.abstractVO.SelectableVO;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.time.LocalDateTime;

public abstract class ReceiptVO extends SelectableVO<ReceiptVO> {
    private String id;
    private int operatorId; // 很可能会改成名字之类

    private LocalDateTime createTime;
    private LocalDateTime lastModifiedTime;

    private ReceiptState receiptState;

    public ReceiptVO() {
    }

    public ReceiptVO(String id, int operatorId, LocalDateTime createTime, LocalDateTime lastModifiedTime, ReceiptState receiptState) {
        this.id = id;
        this.operatorId = operatorId;
        this.createTime = createTime;
        this.lastModifiedTime = lastModifiedTime;
        this.receiptState = receiptState;
    }

    public abstract <T extends ReceiptPO> T toPO();

    public abstract ReceiptblService getService() throws RemoteException, NotBoundException, MalformedURLException;

    public abstract Node getDetailPane();

    protected static String formatId(String codeName, ReceiptPO receiptPO) {
        LocalDateTime createTime = receiptPO.getCreateTime();
        return String.format(codeName + "-%04d%02d%02d-%05d"
                , createTime.getYear()
                , createTime.getMonthValue()
                , createTime.getDayOfMonth()
                , receiptPO.getDayId());
    }

    protected int idToDayId() {
        return Integer.parseInt(id.substring(id.length() - 5));
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(int operatorId) {
        this.operatorId = operatorId;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getLastModifiedTime() {
        return lastModifiedTime;
    }

    public void setLastModifiedTime(LocalDateTime lastModifiedTime) {
        this.lastModifiedTime = lastModifiedTime;
    }

    public ReceiptState getReceiptState() {
        return receiptState;
    }

    public void setReceiptState(ReceiptState receiptState) {
        this.receiptState = receiptState;
    }
}
