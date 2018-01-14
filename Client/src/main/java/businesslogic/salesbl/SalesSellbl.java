package businesslogic.salesbl;

import blService.memberblService.MemberInfo;
import blService.salesblService.SalesSellblService;
import businesslogic.businessbl.BusinessConditionbl;
import businesslogic.genericbl.Receiptbl;
import businesslogic.goodsbl.goodsUpdate.GoodsSalesUpdate;
import businesslogic.inventorybl.inventoryInfo.GiftReceiptInfoImpl;
import businesslogic.inventorybl.inventoryInfo.InventoryGiftReceiptInfo;
import businesslogic.memberbl.MemberInfo_Impl;
import po.BusinessConditionPO;
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
        new GoodsSalesUpdate().goodsUpdateSalesSel(receiptVO.getItems());
            InventoryGiftReceiptInfo inventoryGiftReceiptInfo = new GiftReceiptInfoImpl();
            inventoryGiftReceiptInfo.addInventoryGiftReceipt(receiptVO.getItems());
            BusinessConditionbl businessConditionbl = new BusinessConditionbl();
            BusinessConditionPO businessConditionPO = new BusinessConditionPO();
            businessConditionPO.setSalesIncome(receiptVO.getOriginSum()-receiptVO.getDiscountAmount());
            double temp = receiptVO.getGiveTokenAmount()-(receiptVO.getOriginSum()-receiptVO.getDiscountAmount());
            businessConditionPO.setTokenIncome(temp>0?temp:0);
            businessConditionPO.setDiscount(receiptVO.getDiscountAmount());

            businessConditionbl.insert(businessConditionPO);

            MemberInfo memberInfo = new MemberInfo_Impl();
            memberInfo.updateDebt(receiptVO.getMemberId(),receiptVO.getOriginSum()-receiptVO.getDiscountAmount());

        }
        catch (Exception e){
            e.printStackTrace();
        }
        return ResultMessage.SUCCESS;
    }
}
