package businesslogic.businessbl;

import blService.businessblservice.BusinessProgressblService;
import blService.businessblservice.BusinessSearchInfo;
import businesslogic.checkbl.MyServiceFactory;
import util.ReceiptSearchCondition;
import vo.billReceiptVO.CashReceiptVO;
import vo.billReceiptVO.ChargeReceiptVO;
import vo.billReceiptVO.PaymentReceiptVO;
import vo.inventoryVO.InventoryDamageReceiptVO;
import vo.inventoryVO.InventoryGiftReceiptVO;
import vo.inventoryVO.InventoryOverflowReceiptVO;
import vo.inventoryVO.InventoryWarningReceiptVO;
import vo.receiptVO.ReceiptVO;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class BusinessProgressbl implements BusinessProgressblService {
    private BusinessSearchInfo<InventoryDamageReceiptVO> inventoryDamageReceiptVOBusinessSearchInfo;
    private BusinessSearchInfo<InventoryGiftReceiptVO> inventoryGiftReceiptVOBusinessSearchInfo;
    private BusinessSearchInfo<InventoryOverflowReceiptVO> inventoryOverflowReceiptVOBusinessSearchInfo;
    private BusinessSearchInfo<InventoryWarningReceiptVO> inventoryWarningReceiptVOBusinessSearchInfo;

    private BusinessSearchInfo<CashReceiptVO> cashReceiptVOBusinessSearchInfo;
    private BusinessSearchInfo<ChargeReceiptVO> chargeReceiptVOBusinessSearchInfo;
    private BusinessSearchInfo<PaymentReceiptVO> paymentReceiptVOBusinessSearchInfo;


    public BusinessProgressbl() throws RemoteException, NotBoundException, MalformedURLException {
        inventoryDamageReceiptVOBusinessSearchInfo = MyServiceFactory.getInventoryDamageSearchInfo();
        inventoryGiftReceiptVOBusinessSearchInfo = MyServiceFactory.getInventoryGiftSearchInfo();
        inventoryOverflowReceiptVOBusinessSearchInfo = MyServiceFactory.getInventoryOverflowSearchInfo();
        inventoryWarningReceiptVOBusinessSearchInfo = MyServiceFactory.getInventoryWarningSearchInfo();

        cashReceiptVOBusinessSearchInfo = MyServiceFactory.getCashReceiptSearchInfo();
        chargeReceiptVOBusinessSearchInfo = MyServiceFactory.getChargeReceiptSearchInfo();
        paymentReceiptVOBusinessSearchInfo = MyServiceFactory.getPaymentReceiptSearchInfo();
    }

    @Override
    public ArrayList<ReceiptVO> search(ReceiptSearchCondition receiptSearchCondition) throws RemoteException {
        ArrayList<ReceiptVO> resultList = new ArrayList<>();

        resultList.addAll(inventoryDamageReceiptVOBusinessSearchInfo.search(receiptSearchCondition));
        resultList.addAll(inventoryGiftReceiptVOBusinessSearchInfo.search(receiptSearchCondition));
        resultList.addAll(inventoryOverflowReceiptVOBusinessSearchInfo.search(receiptSearchCondition));
        resultList.addAll(inventoryWarningReceiptVOBusinessSearchInfo.search(receiptSearchCondition));

        resultList.addAll(cashReceiptVOBusinessSearchInfo.search(receiptSearchCondition));
        resultList.addAll(chargeReceiptVOBusinessSearchInfo.search(receiptSearchCondition));
        resultList.addAll(paymentReceiptVOBusinessSearchInfo.search(receiptSearchCondition));

        return resultList;
    }
}
