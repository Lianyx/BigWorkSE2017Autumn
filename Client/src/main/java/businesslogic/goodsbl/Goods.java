package businesslogic.goodsbl;

import dataService.goodsdataService.GoodsDataService;
import exception.ExistException;
import po.GoodsPO;
import util.ResultMessage;
import vo.GoodsVO;

import java.util.List;

public class Goods {
    private GoodsDataService dataService;

    private GoodsPOVOChanger changer;

    public List<GoodsVO> show() {
        List<GoodsPO> POList = dataService.show();
        List<GoodsVO> VOList = changer.allToVO(POList);
        return VOList;
    }

    public ResultMessage addGoods(GoodsVO goodsVO) {
        GoodsPO po = changer.oneToPO(goodsVO);
        dataService.insert(po);
        return ResultMessage.SUCCESS;
    }

    public ResultMessage deleteGoods(String ID) {
        dataService.delete(ID);
        return ResultMessage.SUCCESS;
    }

    public ResultMessage updateGoods(GoodsVO goodsVO) {
        GoodsPO po = changer.oneToPO(goodsVO);
        dataService.update(po);
        return ResultMessage.SUCCESS;
    }

    public List<GoodsVO> SearchGoods(String info) {
        return null;
    }

    /**
     * 这里将商品编号设置为 分类ID + 添加次序的形式（最多为999，最小为001；暂时考虑为这样，到时候参考其他人写的形式应该会有改动）
     * @param upID
     * @param order
     * @return
     */
    public String getID(String upID, int order) {
        String id = upID;
        int[] ladder = {0,10,100,1000};
        for (int i = 0; i < ladder.length-1; i++) {
            if(order >= ladder[i] && order <= ladder[i+1]){
                for (int j = 0; j < ladder.length-1-i;j++)
                    id += "0";
                id += order;
                break;
            }
        }
        return id;
    }
}
