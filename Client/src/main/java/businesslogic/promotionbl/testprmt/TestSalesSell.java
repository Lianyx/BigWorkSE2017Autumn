package businesslogic.promotionbl.testprmt;

import blService.salesblService.SalesSellblService;
import businesslogic.salesbl.SalesSellbl;
import util.ReceiptState;
import util.RespectiveReceiptSearchCondition;
import vo.ListGoodsItemVO;
import vo.receiptVO.SalesSellReceiptVO;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class TestSalesSell {

    public static void main(String[] args) {
        ArrayList<ListGoodsItemVO> list = new ArrayList<>();
        list.add(new ListGoodsItemVO("sabi", 12, "SABI", 100, 2, "nishisabi"));
        list.add(new ListGoodsItemVO("sabi", 4, "SABI", 50, 2, "nishisabi"));

        SalesSellReceiptVO salesSellReceiptVO = new SalesSellReceiptVO("JHT-20170101-00023", 12, LocalDateTime.now(), LocalDateTime.now().plusDays(1), ReceiptState.PENDING, -1, "linyuchao", "awb", "awd", list, "awd", 1200000, 12, 12, null, 123);
        SalesSellReceiptVO salesSellReceiptVO1 = new SalesSellReceiptVO("JHT-20170101-00011", 12, LocalDateTime.now(), LocalDateTime.now().minusDays(4), ReceiptState.PENDING, 1, "linyuchao", "awx", "21c", list, "x", 12, 132, 12, null, 123);
        SalesSellReceiptVO salesSellReceiptVO2 = new SalesSellReceiptVO("JHT-20170101-00000", 12, LocalDateTime.now(), LocalDateTime.now().plusDays(1), ReceiptState.PENDING, -1, "linyuchao", "brb", "cva", list, "cc", 12, 12123, 12, null, 123);
        SalesSellReceiptVO salesSellReceiptVO3 = new SalesSellReceiptVO("JHT-20170101-00000", 12, LocalDateTime.now(), LocalDateTime.now().plusDays(1), ReceiptState.PENDING, 1, "linyuchao", "wad", "wadw", list, "beba", 12, 123, 12, null, 123);
        SalesSellReceiptVO salesSellReceiptVO4 = new SalesSellReceiptVO("JHT-20170101-12333", 12, LocalDateTime.now(), LocalDateTime.now().plusDays(1), ReceiptState.PENDING, -1, "linyuchao", "awdaw", "awd", list, "qe1ewdawdwadjawidhzkhvkdhlwaclwawdawdawdhvilahwl", 12, 12, 12, null, 123);
        SalesSellReceiptVO salesSellReceiptVO5 = new SalesSellReceiptVO("JHT-20170101-11515", 12, LocalDateTime.now(), LocalDateTime.now().plusDays(1), ReceiptState.PENDING, -1, "linyuchao", "", "", list, "2", 12, 12, 1882, null, 112223);

        try {
            SalesSellblService sbl= new SalesSellbl();

//            SalesSellReceiptVO ssr = salesSellblService.getNew();
//            System.out.println(ssr.getId());
//            salesSellReceiptVO.setId(ssr.getId());
//            salesSellReceiptVO.setCreateTime(ssr.getCreateTime());

//            salesSellblService.insert(salesSellReceiptVO);

//            System.out.println(salesSellblService.selectByMold(ssr).getId());


            ArrayList<SalesSellReceiptVO> sss = sbl.search(new RespectiveReceiptSearchCondition());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
