package businesslogic.goodsClassificationbl;

import dataService.goodsdataService.GoodsClassificationDataService;
import exception.ExistException;
import po.GoodsClassificationPO;
import util.ResultMessage;
import vo.inventoryVO.GoodsClassificationVO;

import java.util.*;

public class GoodsClassification {
    public static final String START_NODE = "0";

    private GoodsClassificationDataService goodsClassificationData;

    private GoodsClassficatinPOVOChanger changer;
    //这里还需要修改，等rmi的添加
    public GoodsClassification() {
    }

    /**
     * 得到所有的商品分类
     * @return List
     */
    public List<GoodsClassificationVO> show() {
        List<GoodsClassificationPO> POList = goodsClassificationData.show();

        GoodsClassification goodsClassification = new GoodsClassification();

        List<GoodsClassificationVO> VOList = goodsClassification.getTree(POList);

        return VOList;
    }

    /**
     * 得到id
     * */
    public String getID(String fatherId, int order) {
        String id = fatherId + " " + order;
        return id;
    }

    /**
     * 添加商品分类
     */
    public ResultMessage addGoodsClassification(GoodsClassificationVO vo) throws ExistException {
        //如果商品分类下有商品，则不能添加分类
        String fatherId = vo.getFatherID();
        if(!hasLeaf(fatherId)){
            GoodsClassificationPO po = changer.oneToPO(vo);

            goodsClassificationData.insert(po);

            return ResultMessage.SUCCESS;
        }else{
            //商品（id）已存在，添加失败
            throw new ExistException();
        }
    }

    /**
     * 对商品分类进行删除，共有三种情况
     *
     */
    public ResultMessage deleteGoodsClassification(GoodsClassificationVO vo) {
        boolean hasGoods = vo.getGoodsID().isEmpty();
        boolean isLeaf = vo.getChildrenId().isEmpty();

        String id = vo.getID();
        //是叶节点且无商品的时候直接删除
        if(!hasGoods && isLeaf){
            goodsClassificationData.delete(id);
        }else if(hasGoods && isLeaf){
            //是叶节点且有商品，这个时候要把其下面的商品也删除，需要提供的接口
            goodsClassificationData.delete(id);
            /**
             * .....
             */
        }else if (!hasGoods && !isLeaf){
            //中间节点，删除该分类后，用递归一一删除其子分类
            goodsClassificationData.delete(id);
            GoodsClassificationPO po = goodsClassificationData.getById(id);
            String[] childrenId = po.getChildrenId();

            for (int i = 0; i < childrenId.length; i++) {
                //进行递归
                GoodsClassificationPO tmpPO = goodsClassificationData.getById(childrenId[i]);
                GoodsClassificationVO tmpVO = changer.oneToVO(tmpPO);
                deleteGoodsClassification(tmpVO);
            }
        }
        return ResultMessage.SUCCESS;
    }

    public ResultMessage updateGoodsClassification(GoodsClassificationVO vo) {
        GoodsClassificationPO po = changer.oneToPO(vo);
        goodsClassificationData.update(po);
        return ResultMessage.SUCCESS;
    }

    public boolean hasLeaf(String fatherId){
        GoodsClassificationPO po = goodsClassificationData.getById(fatherId);
        String[] goodsID = po.getGoodsId();
        return !(goodsID.length == 0);
    }


    public GoodsClassificationPO getById(String id){
        return goodsClassificationData.getById(id);
    }

    //经过初步处理的商品分类树，每一层的商品分类是在一起的，root后面跟着他的所有孩子leve1，level后面跟着他们的孩子level2
    private List<GoodsClassificationVO> getTree(List<GoodsClassificationPO> POList){
        List<GoodsClassificationVO> VOList = new ArrayList<>(POList.size());

        GoodsClassificationVO head = changer.oneToVO(POList.get(0));
        VOList.add(head);

        GoodsClassificationVO vo = head;

        Queue<GoodsClassificationVO> que = new LinkedList<>();
        que.offer(vo);

        while (!que.isEmpty()){
            vo = que.poll();
            VOList.add(vo);
            List<String> childrenId = vo.getChildrenId();

            if(childrenId.size() == 0)
                continue;

            //顺着整个商品分类找
            for (int i = 1; i < POList.size(); i++) {
                GoodsClassificationPO po = POList.get(i);
                String tmpId = po.getId();

                //如果该商品分类是其子分类，就把该商品分类放到其后面
                if(childrenId.indexOf(tmpId) != -1){
                    GoodsClassificationVO tmpVO = changer.oneToVO(po);
                    VOList.add(tmpVO);
                    que.offer(tmpVO);
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
