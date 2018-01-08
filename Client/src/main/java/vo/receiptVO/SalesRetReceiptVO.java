package vo.receiptVO;

import blService.checkblService.CheckInfo;
import javafx.scene.Node;
import po.promotionPO.PromotionGoodsItemPO;
import util.ReceiptState;
import vo.ListGoodsItemVO;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class SalesRetReceiptVO extends SalesReceiptVO {

    public SalesRetReceiptVO(String id, int operatorId, LocalDateTime createTime, LocalDateTime lastModifiedTime, ReceiptState receiptState, int memberId, String memberName, String clerkName, String stockName, ArrayList<ListGoodsItemVO> items, String comment, double discountAmount, double tokenAmount, double originSum) {
        super(id, operatorId, createTime, lastModifiedTime, receiptState, memberId, memberName, clerkName, stockName, items, comment, discountAmount, tokenAmount, originSum);
    }

    @Override
    public CheckInfo<SalesRetReceiptVO> getService() throws RemoteException, NotBoundException, MalformedURLException {
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
