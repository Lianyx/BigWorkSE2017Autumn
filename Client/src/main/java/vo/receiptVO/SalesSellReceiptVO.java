package vo.receiptVO;

import blService.checkblService.CheckInfo;
import blService.checkblService.ReceiptblService;
import javafx.scene.Node;
import po.promotionPO.PromotionGoodsItemPO;
import po.receiptPO.ReceiptPO;
import util.ReceiptState;
import vo.ListGoodsItemVO;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class SalesSellReceiptVO extends SalesReceiptVO {

    public SalesSellReceiptVO(String id, int operatorId, LocalDateTime createTime, LocalDateTime lastModifiedTime, ReceiptState receiptState, int memberId, String memberName, String clerkName, String stockName, ArrayList<ListGoodsItemVO> items, String comment, double discountAmount, double tokenAmount, double originSum, PromotionGoodsItemPO[] gifts, double giveTokenAmount) {
        super(id, operatorId, createTime, lastModifiedTime, receiptState, memberId, memberName, clerkName, stockName, items, comment, discountAmount, tokenAmount, originSum, gifts, giveTokenAmount);
    }

    @Override
    public CheckInfo<SalesSellReceiptVO> getService() throws RemoteException, NotBoundException, MalformedURLException {
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