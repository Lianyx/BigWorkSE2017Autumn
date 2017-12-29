package ui.inventoryManagerui;

import vo.inventoryVO.GoodsVO;

import java.util.Set;
import java.util.TreeSet;

public class GoodsChosenItem {
    private static TreeSet<GoodsVO> chosenitems=new TreeSet();
    static public void addItem(GoodsVO GoodsVO){
        chosenitems.add(GoodsVO);
    }
    static public void removeItem(GoodsVO GoodsVO){
        chosenitems.remove(GoodsVO);
    }
    static public Set<GoodsVO> getSet(){return chosenitems;}
}
