package businesslogic.salesbl;


import blService.salesblService.SalesSellblService;
import businesslogic.checkbl.Receiptbl;
import po.receiptPO.SalesSellReceiptPO;
import util.ResultMessage;
import vo.receiptVO.SalesSellReceiptVO;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

// 这个有点交叉继承的感觉了
public class SalesSellbl extends Receiptbl<SalesSellReceiptVO, SalesSellReceiptPO> implements SalesSellblService {
    public SalesSellbl() throws RemoteException, NotBoundException, MalformedURLException {
        super(SalesSellReceiptVO.class, SalesSellReceiptPO.class);
    }

    @Override
    public ResultMessage approve(SalesSellReceiptPO receiptPO) throws RemoteException {
        return null;
    }
}
