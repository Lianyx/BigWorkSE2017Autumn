package businesslogic.goodsbl.goodsUpdate;

import blService.goodsblService.GoodsSalesUpdateInfo;
import businesslogic.goodsbl.Goodsbl;
import businesslogic.inventorybl.InventoryWarningReceiptbl;
import po.GoodsPO;
import ui.util.UserInfomation;
import vo.ListGoodsItemVO;
import vo.inventoryVO.GoodsVO;
import vo.inventoryVO.inventoryReceiptVO.InventoryWarningReceiptVO;
import vo.inventoryVO.inventoryReceiptVO.ReceiptGoodsItemVO;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class GoodsSalesUpdate implements GoodsSalesUpdateInfo {
    Goodsbl goodsbl;
    InventoryWarningReceiptbl inventoryWarningReceiptbl;
    public GoodsSalesUpdate() throws RemoteException, NotBoundException, MalformedURLException {
        goodsbl = new Goodsbl();
        inventoryWarningReceiptbl = new InventoryWarningReceiptbl();
    }


    @Override
    public void goodsUpdateSaleRet(List<ListGoodsItemVO> list) throws RemoteException {

        for (ListGoodsItemVO vo:list) {
            GoodsVO goodsVO = goodsbl.showDetail(vo.getGoodsId());
            int inventoryNum = goodsVO.getInventoryNum()-vo.getGoodsNum();



            goodsVO.setRecentSalePrice(vo.getPrice());
            goodsVO.setInventoryNum(inventoryNum);
            goodsbl.updateGoods(goodsVO);
        }
    }

    @Override
    public void goodsUpdateSalesSel(List<ListGoodsItemVO> list) throws RemoteException {

        for (ListGoodsItemVO vo:list) {
            GoodsVO goodsVO = goodsbl.showDetail(vo.getGoodsId());

            int inventoryNum = goodsVO.getInventoryNum()-vo.getGoodsNum();

            goodsVO.setRecentSalePrice(vo.getPrice());
            goodsVO.setInventoryNum(inventoryNum);
            goodsbl.updateGoods(goodsVO);

        }
    }

    @Override
    public void goodsUpdateStockPur(List<ListGoodsItemVO> list) throws RemoteException {
        for (ListGoodsItemVO vo:list) {
            GoodsVO goodsVO = goodsbl.showDetail(vo.getGoodsId());

            int inventoryNum = goodsVO.getInventoryNum()+vo.getGoodsNum();

            goodsVO.setRecentPurPrice(vo.getPrice());
            goodsVO.setInventoryNum(inventoryNum);
            goodsbl.updateGoods(goodsVO);
        }

    }

    @Override
    public void goodsUpdateStorckRet(List<ListGoodsItemVO> list) throws RemoteException {

        for (ListGoodsItemVO vo:list) {
            GoodsVO goodsVO = goodsbl.showDetail(vo.getGoodsId());

            int inventoryNum = goodsVO.getInventoryNum()-vo.getGoodsNum();


            goodsVO.setRecentPurPrice(vo.getPrice());
            goodsVO.setInventoryNum(inventoryNum);
            goodsbl.updateGoods(goodsVO);

        }

    }



}
