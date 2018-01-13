package businesslogic.businessbl;

import blService.businessblservice.BusinessProgressblService;
import blService.businessblservice.BusinessSearchInfo;
import businesslogic.checkbl.MyServiceFactory;
import util.ReceiptSearchCondition;
import util.ReceiptState;
import vo.billReceiptVO.CashReceiptVO;
import vo.billReceiptVO.ChargeReceiptVO;
import vo.billReceiptVO.PaymentReceiptVO;
import vo.inventoryVO.inventoryReceiptVO.InventoryDamageReceiptVO;
import vo.inventoryVO.inventoryReceiptVO.InventoryGiftReceiptVO;
import vo.inventoryVO.inventoryReceiptVO.InventoryOverflowReceiptVO;
import vo.inventoryVO.inventoryReceiptVO.InventoryWarningReceiptVO;
import vo.receiptVO.ReceiptVO;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.stream.Collectors;

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
        // TODO 这个要根据是否contain，来决定是否需要调这个SearchInfo。以及，这里还缺stock和sales
        ArrayList<ReceiptVO> resultList = new ArrayList<>();

        resultList.addAll(getApprovedReceipt(inventoryDamageReceiptVOBusinessSearchInfo, receiptSearchCondition));
        resultList.addAll(getApprovedReceipt(inventoryGiftReceiptVOBusinessSearchInfo, receiptSearchCondition));
        resultList.addAll(getApprovedReceipt(inventoryOverflowReceiptVOBusinessSearchInfo, receiptSearchCondition));
        resultList.addAll(getApprovedReceipt(inventoryWarningReceiptVOBusinessSearchInfo,receiptSearchCondition));

        resultList.addAll(getApprovedReceipt(cashReceiptVOBusinessSearchInfo, receiptSearchCondition));
        resultList.addAll(getApprovedReceipt(chargeReceiptVOBusinessSearchInfo, receiptSearchCondition));
        resultList.addAll(getApprovedReceipt(paymentReceiptVOBusinessSearchInfo, receiptSearchCondition));

        return resultList;
    }

    /**
     * private search, in order to filter only saved receipt.
     * */
    private <T extends ReceiptVO> ArrayList<T> getApprovedReceipt(BusinessSearchInfo<T> businessSearchInfo, ReceiptSearchCondition receiptSearchCondition) throws RemoteException {
        // TODO
//        return businessSearchInfo.search(receiptSearchCondition).stream().filter(r -> r.getReceiptState() == ReceiptState.APPROVED).collect(Collectors.toCollection(ArrayList::new));
        return businessSearchInfo.search(receiptSearchCondition);
    }
}
