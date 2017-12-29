package blServiceStub.goodsblService_Stub;

import blService.goodsblService.GoodsblService;
import businesslogic.goodsbl.Goods;
import util.ResultMessage;
import vo.inventoryVO.GoodsVO;

import java.util.*;

public class GoodsblService_Stub implements GoodsblService{
    TreeSet<GoodsVO> set=new TreeSet<>();

    public GoodsblService_Stub() {
        set.add(new GoodsVO("10000","灯泡","中国灯","1000",100,90.0,90.0,90.0,90.0,99));
        set.add(new GoodsVO("10001","灯泡","中国灯","1000",100,90.0,90.0,90.0,90.0,99));
        set.add(new GoodsVO("10002","灯泡","中国灯","1000",100,90.0,90.0,90.0,90.0,99));
        set.add(new GoodsVO("10003","灯泡","中国灯","1000",100,90.0,90.0,90.0,90.0,99));
        set.add(new GoodsVO("10004","灯泡","中国灯","1000",100,90.0,90.0,90.0,90.0,99));
        set.add(new GoodsVO("10005","灯泡","中国灯","1000",100,90.0,90.0,90.0,90.0,99));
        set.add(new GoodsVO("10006","灯泡","中国灯","1000",100,90.0,90.0,90.0,90.0,99));
        set.add(new GoodsVO("10007","灯泡","中国灯","1000",100,90.0,90.0,90.0,90.0,99));
        set.add(new GoodsVO("10008","灯泡","中国灯","1000",100,90.0,90.0,90.0,90.0,99));
        set.add(new GoodsVO("10009","灯泡","中国灯","1000",100,90.0,90.0,90.0,90.0,99));
        set.add(new GoodsVO("10010","灯泡","中国灯","1000",100,90.0,90.0,90.0,90.0,99));
    }

    @Override
    public List<GoodsVO> show() {
        return new ArrayList<GoodsVO>(set);
    }

    @Override
    public ResultMessage addGoods(GoodsVO goodsVO) {
        return null;
    }

    @Override
    public ResultMessage deleteGoods(GoodsVO goodsVO) {
        set.remove(goodsVO);

        return null;
    }

    @Override
    public ResultMessage updateGoods(GoodsVO goodsVO) {
        return null;
    }

    @Override
    public List<GoodsVO> SearchGoods(String info) {
        return null;
    }

    @Override
    public String getID(String upID, int order) {
        return null;
    }
   /* *//**
     * 显示所有商品
     *//*
    public Set<GoodsVO> show(){
        System.out.println("show success");
        return new HashSet<GoodsVO>();
    }

    *//**
     * 增加商品
     * @param goodsVO
     *//*
    public ResultMessage addGoods(GoodsVO goodsVO){
        System.out.println("insert success");
        return ResultMessage.SUCCESS;
    }

    *//**
     * 删除商品
     * @param ID
     * @return
     *//*
    public ResultMessage deleteGoods(String ID){
        System.out.println("delete success");
        return ResultMessage.SUCCESS;
    }

    *//**
     * 更改商品属性
     * @param goodsVO
     * @return
     *//*
    public ResultMessage updateGoods(GoodsVO goodsVO){
        System.out.println("update success");
        return ResultMessage.SUCCESS;
    }

    *//**
     * 根据商品编号或名称查找商品
     * @param info
     * @return
     *//*
    public Set<GoodsVO> SearchGoods(String info){
        return new HashSet<GoodsVO>();
    }

    *//**
     * 根据分类编号和添加次序生成编号
     * @param upID
     * @return
     *//*
    public String getID(String upID){
        return "123";
    }*/
}
