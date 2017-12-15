package businesslogic.goodsClassificationbl;

import dataService.goodsdataService.GoodsClassificationDataService;
import exception.ExistException;
import po.GoodsClassificationPO;
import util.ResultMessage;
import vo.GoodsClassificationVO;

import java.util.*;

public class GoodsClassification {
    public static final String START_NODE = "0";

    private GoodsClassificationDataService goodsClassificationData;

    private GoodsClassficatinPOVOChanger changer;
    //这里还需要修改，等rmi的添加
    public GoodsClassification() {
    }

    public List<GoodsClassificationVO> show() {
        List<GoodsClassificationPO> POList = goodsClassificationData.show();

        GoodsClassification goodsClassification = new GoodsClassification();

        List<GoodsClassificationVO> VOList = goodsClassification.getTree(POList);

        return VOList;
    }

    /**
     * 这里先不写id，因为感觉这个在ui层面生成编号更简单，否则需要改动的很多
     * */
    public String getID(String fatherID) {
        return "";
    }

    public ResultMessage addGoodsClassification(GoodsClassificationVO vo) throws ExistException {
        //如果商品分类下有商品，则不能添加分类
        /**
         * 此处修改了dataService，写完之后需要补充,添加getFatherID方法
         */
        String fatherId = vo.getFatherID();
        if(hasLeaf(fatherId)){
            GoodsClassificationPO po = changer.oneToPO(vo);

            goodsClassificationData.insert(po);

            return ResultMessage.SUCCESS;
        }else{
            //商品（id）已存在，添加失败
            throw new ExistException();
        }
    }

    public ResultMessage deleteGoodsClassification(String ID) {
        goodsClassificationData.delete(ID);
        return ResultMessage.SUCCESS;
    }

    public ResultMessage updateGoodsClassification(GoodsClassificationVO vo) {
        GoodsClassificationPO po = changer.oneToPO(vo);
        goodsClassificationData.update(po);
        return ResultMessage.SUCCESS;
    }

    public boolean hasLeaf(String fatherId){
        GoodsClassificationPO po = goodsClassificationData.getFather(fatherId);
        List<String> goodsID = po.getGoodsID();
        return goodsID.isEmpty();
    }

    private List<GoodsClassificationVO> getTree(List<GoodsClassificationPO> POList){
        List<GoodsClassificationVO> VOList = new ArrayList<>(POList.size());

        GoodsClassificationPO root = POList.get(0);
        VOList.add(changer.oneToVO(root));
        POList.remove(0);

        GoodsClassificationPO father = root;
        String fatherId;
        Queue<GoodsClassificationPO> queue = new LinkedList<>();
        queue.offer(father);

        while(!queue.isEmpty()){
            fatherId = queue.poll().getId();
            for (int i = 0; i < POList.size(); i++) {
                GoodsClassificationPO son = POList.get(i);
                if(son.getFatherID().equals(fatherId)){
                    queue.offer(son);
                    VOList.add(changer.oneToVO(son));
                    POList.remove(i);
                }
            }
        }
        return VOList;
    }

    public static void main(String[] args) {
        GoodsClassification goodsClassification = new GoodsClassification();
        List<GoodsClassificationPO> list = new ArrayList<>(10);
        list.add(new GoodsClassificationPO("1","aaa","null"));
        list.add(new GoodsClassificationPO("2","aaa","1"));
        list.add(new GoodsClassificationPO("3","aaa","1"));
        list.add(new GoodsClassificationPO("4","aaa","1"));
        list.add(new GoodsClassificationPO("5","aaa","2"));
        list.add(new GoodsClassificationPO("6","aaa","2"));
        list.add(new GoodsClassificationPO("7","aaa","2"));
        list.add(new GoodsClassificationPO("8","aaa","7"));
        list.add(new GoodsClassificationPO("9","aaa","7"));
        list.add(new GoodsClassificationPO("10","aaa","7"));
        list.add(new GoodsClassificationPO("11","aaa","10"));
        list.add(new GoodsClassificationPO("12","aaa","10"));
        list.add(new GoodsClassificationPO("13","aaa","10"));
        list.add(new GoodsClassificationPO("14","aaa","1"));
        list.add(new GoodsClassificationPO("15","aaa","2"));
        list.add(new GoodsClassificationPO("16","aaa","10"));
        goodsClassification.getTree(list);
    }
}
