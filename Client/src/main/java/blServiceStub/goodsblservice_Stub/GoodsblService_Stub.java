package blServiceStub.goodsblservice_Stub;

import blService.goodsblService.GoodsblService;
import util.ResultMessage;
import vo.inventoryVO.GoodsVO;

import java.util.*;

public class GoodsblService_Stub implements GoodsblService{
    HashSet<GoodsVO> set=new HashSet<>();

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
    public Set<GoodsVO> show() {
        return set;
    }

    @Override
    public ResultMessage addGoods(GoodsVO goodsVO) {
        System.out.println("add finish");
        return null;
    }

    @Override
    public ResultMessage deleteGoods(GoodsVO goodsVO) {
        System.out.println("delete finish");
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

    public GoodsVO showDetail(int id){
        return new GoodsVO();
    }

}
