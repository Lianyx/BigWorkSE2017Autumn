package businesslogic.goodsClassificationbl;

import po.GoodsClassificationPO;
import vo.GoodsClassificationVO;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GoodsClassficatinPOVOChanger {

    public  List<GoodsClassificationVO> allToVO(List<GoodsClassificationPO> POList){
        GoodsClassficatinPOVOChanger changer = new GoodsClassficatinPOVOChanger();
        Iterator<GoodsClassificationPO> it = POList.iterator();
        List<GoodsClassificationVO> VOList = new ArrayList<>();

        while (it.hasNext())
            VOList.add(changer.oneToVO(it.next()));
        return VOList;
    }

    public   GoodsClassificationVO oneToVO(GoodsClassificationPO po){
        GoodsClassificationVO vo = new GoodsClassificationVO();
        vo.setID(po.getId());
        vo.setFatherID(po.getFatherID());
        vo.setName(po.getName());
        vo.setGoodsID(po.getGoodsID());
        return vo;
    }

    public GoodsClassificationPO oneToPO(GoodsClassificationVO vo){
        GoodsClassificationPO po = new GoodsClassificationPO();
        po.setId(vo.getID());
        po.set_name(vo.getName());
        po.setFatherId(vo.getFatherID());
        po.setGoodsID((ArrayList<String>) vo.getGoodsID());
        return po;
    }
}
