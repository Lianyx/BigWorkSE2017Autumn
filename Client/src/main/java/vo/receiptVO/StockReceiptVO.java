package vo.receiptVO;

import blService.checkblService.ReceiptblService;
import javafx.beans.property.*;
import javafx.scene.Node;
import po.receiptPO.ReceiptPO;
import util.ReceiptState;
import vo.ListGoodsItemVO;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class StockReceiptVO extends ReceiptVO {

    private int memberId;
    private String memberName;
    private String stockName;
    private double sum;
    private ArrayList<ListGoodsItemVO> items = new ArrayList<>();
    private String comment;
    private boolean isPur;


    public StockReceiptVO(String id, int operatorId, LocalDateTime createTime, LocalDateTime lastModifiedTime, ReceiptState receiptState, int memberId, String memberName, String stockName, double sum, ArrayList<ListGoodsItemVO> items, String comment, boolean isPur) {
        super(id, operatorId, createTime, lastModifiedTime, receiptState);
        this.memberId = memberId;
        this.memberName =memberName;
        this.stockName = stockName;
        this.sum = sum;

        this.items = items;
        this.comment = comment;
        this.isPur = isPur;
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

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(Double sum) {
        this.sum = sum;
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

    public boolean isPur() {
        return isPur;
    }

    public void setPur(boolean pur) {
        isPur = pur;
    }

    @Override
    public <T extends ReceiptPO> T toPO() {
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
