package businesslogic.checkbl;

import blService.checkblService.CheckInfo;
import blService.checkblService.CheckblService;
import businesslogic.promotionbl.PromotionFactory;
import businesslogic.salesbl.SalesSellbl;
import po.receiptPO.ReceiptPO;
import po.receiptPO.SalesSellReceiptPO;
import util.ResultMessage;
import vo.billReceiptVO.CashReceiptVO;
import vo.billReceiptVO.ChargeReceiptVO;
import vo.billReceiptVO.PaymentReceiptVO;
import vo.inventoryVO.InventoryDamageReceiptVO;
import vo.inventoryVO.InventoryGiftReceiptVO;
import vo.inventoryVO.InventoryOverflowReceiptVO;
import vo.inventoryVO.InventoryWarningReceiptVO;
import vo.receiptVO.ReceiptVO;
import vo.receiptVO.SalesSellReceiptVO;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class Checkbl implements CheckblService {
    private static CheckInfo<SalesSellReceiptVO> salesSellReceiptVOCheckInfo;

    private static CheckInfo<InventoryDamageReceiptVO> inventoryDamageReceiptVOCheckInfo;
    private static CheckInfo<InventoryGiftReceiptVO> inventoryGiftReceiptVOCheckInfo;
    private static CheckInfo<InventoryOverflowReceiptVO> inventoryOverflowReceiptVOCheckInfo;
    private static CheckInfo<InventoryWarningReceiptVO> inventoryWarningReceiptVOCheckInfo;

    private static CheckInfo<CashReceiptVO> cashReceiptVOCheckInfo;
    private static CheckInfo<ChargeReceiptVO> chargeReceiptVOCheckInfo;
    private static CheckInfo<PaymentReceiptVO> paymentReceiptVOCheckInfo;


    public Checkbl() throws RemoteException, NotBoundException, MalformedURLException {
        salesSellReceiptVOCheckInfo = CheckInfoFactory.getSalesSellReceiptVOCheckInfo();

        inventoryDamageReceiptVOCheckInfo = CheckInfoFactory.getInventoryDamageReceiptVOCheckInfo();
        inventoryGiftReceiptVOCheckInfo = CheckInfoFactory.getInventoryGiftReceiptVOCheckInfo();
        inventoryOverflowReceiptVOCheckInfo = CheckInfoFactory.getInventoryOverflowReceiptVOCheckInfo();
        inventoryWarningReceiptVOCheckInfo = CheckInfoFactory.getInventoryWarningReceiptVOCheckInfo();

        cashReceiptVOCheckInfo = CheckInfoFactory.getCashReceiptVOCheckInfo();
        chargeReceiptVOCheckInfo = CheckInfoFactory.getChargeReceiptVOCheckInfo();
        paymentReceiptVOCheckInfo = CheckInfoFactory.getPaymentReceiptVOCheckInfo();
    }

    @Override
    public ArrayList<ReceiptVO> initCheck() throws RemoteException {
        ArrayList<ReceiptVO> resultList = new ArrayList<>();
        resultList.addAll(salesSellReceiptVOCheckInfo.selectPending());

        resultList.addAll(inventoryDamageReceiptVOCheckInfo.selectPending());
        resultList.addAll(inventoryGiftReceiptVOCheckInfo.selectPending());
        resultList.addAll(inventoryOverflowReceiptVOCheckInfo.selectPending());
        resultList.addAll(inventoryWarningReceiptVOCheckInfo.selectPending());

        resultList.addAll(cashReceiptVOCheckInfo.selectPending());
        resultList.addAll(chargeReceiptVOCheckInfo.selectPending());
        resultList.addAll(paymentReceiptVOCheckInfo.selectPending());
        return resultList;
    }

    @Override
    public ResultMessage approve(ReceiptVO receiptVO) throws RemoteException, MalformedURLException, NotBoundException {
        return receiptVO.getService().approve(receiptVO);
    }

    @Override
    public ResultMessage reject(ReceiptVO receiptVO) throws RemoteException, MalformedURLException, NotBoundException {
        return receiptVO.getService().reject(receiptVO);
    }

    @Override
    public ResultMessage update(ReceiptVO receiptVO) throws RemoteException, MalformedURLException, NotBoundException {
        return receiptVO.getService().update(receiptVO);
    }

//    public static void main(String[] args) throws Exception{
//        Checkbl checkbl = new Checkbl();
//        System.out.println(checkbl.initCheck());
//    }
}
