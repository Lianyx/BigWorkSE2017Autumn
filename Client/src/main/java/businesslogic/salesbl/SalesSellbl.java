package businesslogic.salesbl;

import blService.salesblService.SalesSellblService;
import businesslogic.checkbl.Receiptbl;
import businesslogic.goodsbl.goodsUpdate.GoodsSalesUpdate;
import businesslogic.inventorybl.inventoryInfo.WarningReceiptInfoImpl;
import po.receiptPO.SalesSellReceiptPO;
import util.ResultMessage;
import vo.receiptVO.SalesSellReceiptVO;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class SalesSellbl extends Receiptbl<SalesSellReceiptVO, SalesSellReceiptPO> implements SalesSellblService {


    public SalesSellbl() throws RemoteException, NotBoundException, MalformedURLException {
        super(SalesSellReceiptVO.class, SalesSellReceiptPO.class);
    }

    @Override
    public ResultMessage approve(SalesSellReceiptVO receiptVO) throws RemoteException{
        try{
            new WarningReceiptInfoImpl().checkSaleSel(receiptVO.getItems());
        new GoodsSalesUpdate().goodsUpdateSalesSel(receiptVO.getItems());}
        catch (Exception e){
            e.printStackTrace();
        }
        return ResultMessage.SUCCESS;
    }
}
