package vo.receiptVO;

import blService.checkblService.CheckInfo;
import javafx.scene.Node;
import po.receiptPO.StockRetReceiptPO;
import util.ReceiptState;
import vo.ListGoodsItemVO;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class StockRetReceiptVO extends StockReceiptVO {
    public StockRetReceiptVO(String id, int operatorId, LocalDateTime createTime, LocalDateTime lastModifiedTime, ReceiptState receiptState, int memberId, String memberName, String stockName, double sum, ArrayList<ListGoodsItemVO> items, String comment) {
        super(id, operatorId, createTime, lastModifiedTime, receiptState, memberId, memberName, stockName, sum, items, comment);
    }


    public StockRetReceiptVO(StockRetReceiptPO stockRetReceiptPO){
        super(stockRetReceiptPO);
    }

    public StockRetReceiptVO() {
    }

    @Override
    public CheckInfo<StockRetReceiptVO> getService() throws RemoteException, NotBoundException, MalformedURLException {
        return null;
    }

    @Override
    public Node getDetailPane() {
        return null;
    }

    @Override
    protected String getCodeName() {
        return "JHTHD";
    }

    @Override
    public StockRetReceiptPO toPO() {
        StockRetReceiptPO result = toStockReceiptPO(StockRetReceiptPO.class);
        return result;
    }


}
