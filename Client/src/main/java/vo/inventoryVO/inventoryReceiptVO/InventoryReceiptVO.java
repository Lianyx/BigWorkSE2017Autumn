package vo.inventoryVO.inventoryReceiptVO;

import blService.checkblService.CheckInfo;
import blService.checkblService.ReceiptblService;
import javafx.scene.Node;
import util.ReceiptState;
import vo.ListGoodsItemVO;
import vo.receiptVO.ReceiptVO;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class InventoryReceiptVO extends ReceiptVO {
    private int memberId;
    private String memberName;
    private ArrayList<ListGoodsItemVO> items = new ArrayList<>();
    private String comment;
    private InventoryReceiptType receiptType;

    public InventoryReceiptVO(String id, int operatorId, LocalDateTime createTime, LocalDateTime lastModifiedTime,
                              ReceiptState receiptState, int memberId, String memberName, ArrayList<ListGoodsItemVO> items,
                              String comment, InventoryReceiptType receiptType) {
        super(id, operatorId, createTime, lastModifiedTime, receiptState);
        this.memberId = memberId;
        this.memberName = memberName;

        this.items = items;
        this.comment = comment;
        this.receiptType = receiptType;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public ArrayList<ListGoodsItemVO> getItems() {
        return items;
    }

    public void setItems(ArrayList<ListGoodsItemVO> items) {
        this.items = items;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public InventoryReceiptType getReceiptType() {
        return receiptType;
    }

    public void setReceiptType(InventoryReceiptType receiptType) {
        this.receiptType = receiptType;
    }


    @Override
    public <TV extends ReceiptVO> CheckInfo<TV> getService() throws RemoteException, NotBoundException, MalformedURLException {
        return null;
    }

    @Override
    public Node getDetailPane() {
        return null;
    }

    @Override
    protected String getCodeName() {
        return null;
    }

    @Override
    public <TF> TF toPO() {
        return null;
    }
}
