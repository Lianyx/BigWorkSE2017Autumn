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

        List<String> childrenId = arrayToList(po.getChildrenId());
        vo.setChildrenId(childrenId);

        List<String> goodsId = arrayToList(po.getGoodsId());
        vo.setGoodsID(goodsId);

        return vo;
    }

    public GoodsClassificationPO oneToPO(GoodsClassificationVO vo){
        GoodsClassificationPO po = new GoodsClassificationPO();
        po.setId(vo.getID());
        po.set_name(vo.getName());
        po.setFatherId(vo.getFatherID());

        String[] childrenId = listToArray(vo.getChildrenId());
        po.setChildrenId(childrenId);

        String[] goodsId = listToArray(vo.getGoodsID());
        po.setGoodsId(goodsId);
        return po;
    }

    public List<String> arrayToList(String[] strings){
        List<String> list = new ArrayList<>(strings.length);

        for (int i = 0; i < strings.length; i++)
            list.add(strings[i]);

        return list;
    }

    public String[] listToArray(List<String> list){
        String[] strings = new String[list.size()];
        Iterator<String> it = list.iterator();

        int i = 0;
        while (it.hasNext()){
            strings[i++] = it.next();
        }

        return strings;
    }
}
