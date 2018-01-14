package ui.salesui.salesSellui;

import blService.genericblService.ReceiptblService;
import blService.promotionblService.PromotionInfo;
import blService.salesblService.SalesSellblService;
import businesslogic.blServiceFactory.MyServiceFactory;
import ui.salesui.SalesReceiptPane;
import vo.receiptVO.SalesSellReceiptVO;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class SalesSellDetailPane extends SalesReceiptPane<SalesSellReceiptVO> {
    private PromotionInfo promotionInfo;

    public SalesSellDetailPane() {
    }

    public SalesSellDetailPane(SalesSellReceiptVO receiptVO) {
        super(receiptVO);
    }

    @Override
    public void initiate() {
        super.initiate();

        try { // 这样的try catch 不行
            promotionInfo = MyServiceFactory.getPromotionInfo();
        } catch (RemoteException | NotBoundException | MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected Class<? extends ReceiptblService<SalesSellReceiptVO>> getServiceClass() {
        return SalesSellblService.class;
    }

//    @Override
//    protected void save() {
//        if (validate()) {
//            receiptVO.setReceiptState(ReceiptState.PENDING);
//            try { // 这样的try catch不行
//                ArrayList<PromotionVO> promotions = promotionInfo.getMatch(receiptVO);
//                // 这个目前只可能是0个元素或者1个元素
//
//                if (! promotions.isEmpty()) {
//                    PromotionVO promotionVO = promotions.get(0);
//
//                    Label label = promotionVO.getInfoLabel();
//                    String content = label.getText();
//
//                    new MyOneButtonDialog(content, () -> {
//                        promotionVO.modifySalesSell(receiptVO);
//                        saveTask();
//                    }).show();
//                }
//
//                saveTask();
//            } catch (RemoteException e) {
//                e.printStackTrace();
//            }
//            saveTask();
//        } else {
//            new MyOneButtonDialog("不合法数据").show();
//        }
//    }
}
