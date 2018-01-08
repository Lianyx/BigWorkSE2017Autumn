package businesslogic.checkbl;

import blService.businessblservice.BusinessConditionblService;
import blService.checkblService.CheckInfo;
import businesslogic.billreceiptbl.CashBillReceiptbl;
import businesslogic.billreceiptbl.ChargeBillReceiptbl;
import businesslogic.billreceiptbl.PaymentBillReceiptbl;
import businesslogic.businessbl.BusinessConditionbl;
import businesslogic.inventorybl.InventoryDamageReceiptbl;
import businesslogic.inventorybl.InventoryGiftReceiptbl;
import businesslogic.inventorybl.InventoryOverflowReceiptbl;
import businesslogic.inventorybl.InventoryWarningReceiptbl;
import businesslogic.salesbl.SalesSellbl;
import vo.billReceiptVO.CashReceiptVO;
import vo.billReceiptVO.ChargeReceiptVO;
import vo.billReceiptVO.PaymentReceiptVO;
import vo.inventoryVO.InventoryDamageReceiptVO;
import vo.inventoryVO.InventoryGiftReceiptVO;
import vo.inventoryVO.InventoryOverflowReceiptVO;
import vo.inventoryVO.InventoryWarningReceiptVO;
import vo.receiptVO.SalesSellReceiptVO;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class MyServiceFactory {
    private static CheckInfo<SalesSellReceiptVO> salesSellReceiptVOCheckInfo;

    private static CheckInfo<InventoryDamageReceiptVO> inventoryDamageReceiptVOCheckInfo;
    private static CheckInfo<InventoryGiftReceiptVO> inventoryGiftReceiptVOCheckInfo;
    private static CheckInfo<InventoryOverflowReceiptVO> inventoryOverflowReceiptVOCheckInfo;
    private static CheckInfo<InventoryWarningReceiptVO> inventoryWarningReceiptVOCheckInfo;

    private static CheckInfo<CashReceiptVO> cashReceiptVOCheckInfo;
    private static CheckInfo<ChargeReceiptVO> chargeReceiptVOCheckInfo;
    private static CheckInfo<PaymentReceiptVO> paymentReceiptVOCheckInfo;


    private static BusinessConditionblService businessConditionblService;

    public static CheckInfo<SalesSellReceiptVO> getSalesSellReceiptVOCheckInfo() throws RemoteException, NotBoundException, MalformedURLException {
        if (salesSellReceiptVOCheckInfo == null) {
            return salesSellReceiptVOCheckInfo = new SalesSellbl();
        }
        return salesSellReceiptVOCheckInfo;
    }

    public static CheckInfo<InventoryDamageReceiptVO> getInventoryDamageReceiptVOCheckInfo() throws RemoteException, NotBoundException, MalformedURLException {
        if (inventoryDamageReceiptVOCheckInfo == null) {
            return inventoryDamageReceiptVOCheckInfo = new InventoryDamageReceiptbl();
        }
        return inventoryDamageReceiptVOCheckInfo;
    }

    public static CheckInfo<InventoryGiftReceiptVO> getInventoryGiftReceiptVOCheckInfo() throws RemoteException, NotBoundException, MalformedURLException {
        if (inventoryGiftReceiptVOCheckInfo == null) {
            return inventoryGiftReceiptVOCheckInfo = new InventoryGiftReceiptbl();
        }
        return inventoryGiftReceiptVOCheckInfo;
    }

    public static CheckInfo<InventoryOverflowReceiptVO> getInventoryOverflowReceiptVOCheckInfo() throws RemoteException, NotBoundException, MalformedURLException {
        if (inventoryOverflowReceiptVOCheckInfo == null) {
            return inventoryOverflowReceiptVOCheckInfo = new InventoryOverflowReceiptbl();
        }
        return inventoryOverflowReceiptVOCheckInfo;
    }

    public static CheckInfo<InventoryWarningReceiptVO> getInventoryWarningReceiptVOCheckInfo() throws RemoteException, NotBoundException, MalformedURLException {
        if (inventoryWarningReceiptVOCheckInfo == null) {
            return inventoryWarningReceiptVOCheckInfo = new InventoryWarningReceiptbl();
        }
        return inventoryWarningReceiptVOCheckInfo;
    }

    public static CheckInfo<CashReceiptVO> getCashReceiptVOCheckInfo() throws RemoteException, NotBoundException, MalformedURLException {
        if (cashReceiptVOCheckInfo == null) {
            return cashReceiptVOCheckInfo = new CashBillReceiptbl();
        }
        return cashReceiptVOCheckInfo;
    }

    public static CheckInfo<ChargeReceiptVO> getChargeReceiptVOCheckInfo() throws RemoteException, NotBoundException, MalformedURLException {
        if (chargeReceiptVOCheckInfo == null) {
            return chargeReceiptVOCheckInfo = new ChargeBillReceiptbl();
        }
        return chargeReceiptVOCheckInfo;
    }

    public static CheckInfo<PaymentReceiptVO> getPaymentReceiptVOCheckInfo() throws RemoteException, NotBoundException, MalformedURLException {
        if (paymentReceiptVOCheckInfo == null) {
            return paymentReceiptVOCheckInfo = new PaymentBillReceiptbl();
        }
        return paymentReceiptVOCheckInfo;
    }

    public static BusinessConditionblService getBusinessConditionblService() throws RemoteException, NotBoundException, MalformedURLException {
        if (businessConditionblService == null) {
            return businessConditionblService = new BusinessConditionbl();
        }
        return businessConditionblService;
    }
}
