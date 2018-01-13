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
        List<ReceiptGoodsItemVO> warningList = new ArrayList<>();

        for (ListGoodsItemVO vo:list) {
            GoodsVO goodsVO = goodsbl.showDetail(vo.getGoodsId());

            int inventoryNum = goodsVO.getInventoryNum()-vo.getGoodsNum();

            if(checkWarning(inventoryNum,goodsVO.getInventoryNum())){
                ReceiptGoodsItemVO goodsItemVO = new ReceiptGoodsItemVO();
                goodsItemVO.setGoodsId(goodsVO.getId());
                goodsItemVO.setGoodsName(goodsVO.getGoodName());
                goodsItemVO.setInventoryNum(inventoryNum);
                goodsItemVO.setWarningNum(goodsItemVO.getWarningNum());
                warningList.add(new ReceiptGoodsItemVO());
            }

            goodsVO.setRecentSalePrice(vo.getPrice());
            goodsVO.setInventoryNum(inventoryNum);
            goodsbl.updateGoods(goodsVO);
        }

        if(warningList.size() > 0){
            InventoryWarningReceiptVO receiptVO = inventoryWarningReceiptbl.getNew();
            receiptVO.setItems(warningList);
            receiptVO.setOperatorId(UserInfomation.userid);
            inventoryWarningReceiptbl.update(receiptVO);
        }

    }

    @Override
    public void goodsUpdateSalesSel(List<ListGoodsItemVO> list) throws RemoteException {
        List<ReceiptGoodsItemVO> warningList = new ArrayList<>();
        for (ListGoodsItemVO vo:list) {
            GoodsVO goodsVO = goodsbl.showDetail(vo.getGoodsId());

            int inventoryNum = goodsVO.getInventoryNum()-vo.getGoodsNum();

            if(checkWarning(inventoryNum,goodsVO.getInventoryNum())){
                ReceiptGoodsItemVO goodsItemVO = new ReceiptGoodsItemVO();
                goodsItemVO.setGoodsId(goodsVO.getId());
                goodsItemVO.setGoodsName(goodsVO.getGoodName());
                goodsItemVO.setInventoryNum(inventoryNum);
                goodsItemVO.setWarningNum(goodsItemVO.getWarningNum());
                warningList.add(new ReceiptGoodsItemVO());
            }

            goodsVO.setRecentSalePrice(vo.getPrice());
            goodsVO.setInventoryNum(inventoryNum);
            goodsbl.updateGoods(goodsVO);

            if(warningList.size() > 0){
                InventoryWarningReceiptVO receiptVO = inventoryWarningReceiptbl.getNew();
                receiptVO.setItems(warningList);
                receiptVO.setOperatorId(UserInfomation.userid);
                inventoryWarningReceiptbl.update(receiptVO);
            }
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
        List<ReceiptGoodsItemVO> warningList = new ArrayList<>();
        for (ListGoodsItemVO vo:list) {
            GoodsVO goodsVO = goodsbl.showDetail(vo.getGoodsId());

            int inventoryNum = goodsVO.getInventoryNum()-vo.getGoodsNum();

            if(checkWarning(inventoryNum,goodsVO.getInventoryNum())){
                ReceiptGoodsItemVO goodsItemVO = new ReceiptGoodsItemVO();
                goodsItemVO.setGoodsId(goodsVO.getId());
                goodsItemVO.setGoodsName(goodsVO.getGoodName());
                goodsItemVO.setInventoryNum(inventoryNum);
                goodsItemVO.setWarningNum(goodsItemVO.getWarningNum());
                warningList.add(new ReceiptGoodsItemVO());
            }

            goodsVO.setRecentPurPrice(vo.getPrice());
            goodsVO.setInventoryNum(inventoryNum);
            goodsbl.updateGoods(goodsVO);

            if(warningList.size() > 0){
                InventoryWarningReceiptVO receiptVO = inventoryWarningReceiptbl.getNew();
                receiptVO.setItems(warningList);
                receiptVO.setOperatorId(UserInfomation.userid);
                inventoryWarningReceiptbl.update(receiptVO);
            }
        }

    }


    private boolean checkWarning(int now,int inventoryNum){
        return (now<inventoryNum)?true:false;
    }
}
