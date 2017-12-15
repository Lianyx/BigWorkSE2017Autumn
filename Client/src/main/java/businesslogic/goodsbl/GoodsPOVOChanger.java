package businesslogic.goodsbl;

import po.GoodsPO;
import vo.GoodsVO;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GoodsPOVOChanger {
    public GoodsVO oneToVO(GoodsPO po){
        GoodsVO vo = new GoodsVO();

        vo.setId(po.getId());
        vo.setGoodName(po.getgoodName());
        vo.setGoodType(po.getgoodType());
        vo.setClassifyId(po.getClassifyId());
        vo.setAlarmNumber(po.getAlarmNumber());
        vo.setInventoryNum(po.getInventoryNum());
        vo.setPurPrice(po.getPurPrice());
        vo.setRecentPurPrice(po.getRecentPurPrice());
        vo.setRecentSalePrice(po.getRecentSalePrice());
        vo.setSalePrice(po.getSalePrice());

        return vo;
    }

    public List<GoodsVO> allToVO(List<GoodsPO> POList){
        List<GoodsVO> VOList = new ArrayList<>(POList.size());
        Iterator<GoodsPO> it = POList.iterator();
        while(it.hasNext())
            VOList.add(oneToVO(it.next()));

        return VOList;
    }

    public GoodsPO oneToPO(GoodsVO vo){
        GoodsPO po = new GoodsPO();

        po.setId(vo.getId());
        po.setGoodName(vo.getGoodName());
        po.setGoodType(vo.getGoodType());
        po.setClassifyId(vo.getClassifyId());
        po.setAlarmNumber(vo.getAlarmNumber());
        po.setInventoryNum(vo.getInventoryNum());
        po.setPurPrice(vo.getPurPrice());
        po.setRecentPurPrice(vo.getRecentPurPrice());
        po.setRecentSalePrice(vo.getRecentSalePrice());
        po.setSalePrice(vo.getSalePrice());

        return po;
    }
}
