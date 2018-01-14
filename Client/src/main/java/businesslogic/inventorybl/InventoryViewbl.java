package businesslogic.inventorybl;

import blService.businessblservice.BusinessSearchInfo;
import blService.inventoryblService.InventoryViewblService;
import businesslogic.blServiceFactory.MyServiceFactory;
import ui.salesui.ViewInfo;
import util.ReceiptSearchCondition;
import vo.ListGoodsItemVO;
import vo.inventoryVO.InventoryViewItemVO;
import vo.inventoryVO.InventoryViewVO;
import vo.receiptVO.SalesRetReceiptVO;
import vo.receiptVO.SalesSellReceiptVO;
import vo.receiptVO.StockPurReceiptVO;
import vo.receiptVO.StockRetReceiptVO;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class InventoryViewbl implements InventoryViewblService {
    @Override
    public InventoryViewVO inventoryView(LocalDate beginDate, LocalDate endDate) throws Exception {
        ViewInfo viewInfo = new ViewInfo();


        //进货单
        BusinessSearchInfo<StockPurReceiptVO> stockPurReceiptVOBusinessSearchInfo = MyServiceFactory.getStockPurSearchInfo();
        //进货退货单
        BusinessSearchInfo<StockRetReceiptVO> stockRetReceiptVOBusinessSearchInfo = MyServiceFactory.getStockRetSearchInfo();
        //销售单
        BusinessSearchInfo<SalesSellReceiptVO> salesReceiptVOBusinessSearchInfo = MyServiceFactory.getSalesSellSearchInfo();
        //销售退货单
        BusinessSearchInfo<SalesRetReceiptVO> salesRetReceiptVOBusinessSearchInfo = MyServiceFactory.getSalesRetSearchInfo();

        ReceiptSearchCondition receiptSearchCondition = new ReceiptSearchCondition();
        receiptSearchCondition.setBegin(LocalDateTime.of(beginDate, LocalTime.MIN));
        receiptSearchCondition.setEnd(LocalDateTime.of(endDate,LocalTime.MIN));

        List<StockPurReceiptVO> stockPurReceiptVOList =  stockPurReceiptVOBusinessSearchInfo.search(receiptSearchCondition);
        List<StockRetReceiptVO> stockRetReceiptVOList = stockRetReceiptVOBusinessSearchInfo.search(receiptSearchCondition);
        List<SalesSellReceiptVO> salesSellReceiptVOList = salesReceiptVOBusinessSearchInfo.search(receiptSearchCondition);
        List<SalesRetReceiptVO> salesRetReceiptVOSList = salesRetReceiptVOBusinessSearchInfo.search(receiptSearchCondition);

        InventoryViewVO inventoryViewVO = new InventoryViewVO();

        List<InventoryViewItemVO> list = new ArrayList<>();
        Set<InventoryViewItemVO> set = new HashSet<>();

        //入库数量金额
        for (StockPurReceiptVO vo:stockPurReceiptVOList) {
            List<ListGoodsItemVO> goodsList = vo.getItems();
            for (int i = 0; i < goodsList.size(); i++) {
                ListGoodsItemVO goodsItemVO = goodsList.get(i);

                InventoryViewItemVO itemVO = new InventoryViewItemVO();

                itemVO.setGoodId(goodsItemVO.getGoodsId());

                int size = set.size();

                set.add(itemVO);
                if(set.size() == size){
                    for (InventoryViewItemVO inventoryViewItemVO:set) {
                        if(inventoryViewItemVO.getGoodId().equals(itemVO.getGoodId())){
                            inventoryViewItemVO.setStockPurMoney(goodsItemVO.getPrice()+inventoryViewItemVO.getStockPurMoney());
                            inventoryViewItemVO.setStockInMoney(goodsItemVO.getPrice()+inventoryViewItemVO.getStockInMoney());
                            inventoryViewItemVO.setStockPurNum(goodsItemVO.getGoodsNum()+inventoryViewItemVO.getStockPurNum());
                            inventoryViewItemVO.setStockInNum(goodsItemVO.getGoodsNum()+inventoryViewItemVO.getStockInNum());
                            set.remove(inventoryViewItemVO);
                            set.add(inventoryViewItemVO);
                        }
                    }
                }
            }
        }

        //出库数量金额
        for (StockRetReceiptVO stockRetReceiptVO:stockRetReceiptVOList) {
            List<ListGoodsItemVO> goodsList = stockRetReceiptVO.getItems();
            for (int i = 0; i < goodsList.size(); i++) {
                ListGoodsItemVO goodsItemVO = goodsList.get(i);

                InventoryViewItemVO itemVO = new InventoryViewItemVO();

                itemVO.setGoodId(goodsItemVO.getGoodsId());

                int size = set.size();

                set.add(itemVO);
                if(set.size() == size){
                    for (InventoryViewItemVO inventoryViewItemVO:set) {
                        if(inventoryViewItemVO.getGoodId().equals(itemVO.getGoodId())){
                            inventoryViewItemVO.setStockOutMoney(goodsItemVO.getPrice()+inventoryViewItemVO.getStockPurMoney());
                            inventoryViewItemVO.setStockPurNum(goodsItemVO.getGoodsNum()+inventoryViewItemVO.getStockPurNum());
                            set.remove(inventoryViewItemVO);
                            set.add(inventoryViewItemVO);
                        }
                    }
                }
            }
        }

        //销售数量金额
        for (SalesSellReceiptVO sellReceiptVO:salesSellReceiptVOList) {
            List<ListGoodsItemVO> goodsList = sellReceiptVO.getItems();
            for (int i = 0; i < goodsList.size(); i++) {
                ListGoodsItemVO goodsItemVO = goodsList.get(i);

                InventoryViewItemVO itemVO = new InventoryViewItemVO();

                itemVO.setGoodId(goodsItemVO.getGoodsId());

                int size = set.size();

                set.add(itemVO);
                if(set.size() == size){
                    for (InventoryViewItemVO inventoryViewItemVO:set) {
                        if(inventoryViewItemVO.getGoodId().equals(itemVO.getGoodId())){
                            inventoryViewItemVO.setSaleMoney(goodsItemVO.getPrice()+inventoryViewItemVO.getSaleMoney());
                            inventoryViewItemVO.setSaleNum(goodsItemVO.getGoodsNum()+inventoryViewItemVO.getSaleNum());
                            set.remove(inventoryViewItemVO);
                            set.add(inventoryViewItemVO);
                        }
                    }
                }
            }
        }

        inventoryViewVO.setViewList(set);


        return inventoryViewVO;
    }
}
