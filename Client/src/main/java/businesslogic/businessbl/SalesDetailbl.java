package businesslogic.businessbl;

import blService.businessblservice.BusinessSearchInfo;
import blService.businessblservice.SalesDetailblService;
import businesslogic.checkbl.MyServiceFactory;
import util.ReceiptSearchCondition;
import vo.ListGoodsItemVO;
import vo.receiptVO.SalesSellReceiptVO;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class SalesDetailbl implements SalesDetailblService {
    BusinessSearchInfo<SalesSellReceiptVO> salesSellSearchInfo;

    public SalesDetailbl() throws RemoteException, NotBoundException, MalformedURLException {
        salesSellSearchInfo = MyServiceFactory.getSalesSellSearchInfo();
    }

    @Override
    public ArrayList<ListGoodsItemVO> searchSalesDetail(ReceiptSearchCondition receiptSearchCondition) throws RemoteException {
        ArrayList<ListGoodsItemVO> goods = salesSellSearchInfo.search(receiptSearchCondition)
                .stream().flatMap(r -> r.getItems().stream())
                .collect(Collectors.toCollection(ArrayList::new));
        return goods;
    }
}
