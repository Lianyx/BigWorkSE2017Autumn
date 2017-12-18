package businesslogic.salesbl;


import blService.checkblService.CheckInfo;
import blService.checkblService.ReceiptblService;
import blService.salesblService.SalesSellblService;
import businesslogic.checkbl.Receiptbl;
import po.ReceiptPO;
import po.SalesSellReceiptPO;
import util.ReceiptSearchCondition;
import util.ResultMessage;
import vo.ReceiptVO;
import vo.SalesSellReceiptVO;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

// 这个有点交叉继承的感觉了
public class SalesSellbl extends Receiptbl<SalesSellReceiptVO, SalesSellReceiptPO> implements SalesSellblService {
    public SalesSellbl() throws RemoteException, NotBoundException, MalformedURLException {
        super(SalesSellReceiptPO.class, SalesSellReceiptVO.class, "SalesSellReceiptData");
    }
}
