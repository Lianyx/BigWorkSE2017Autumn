package businesslogic.goodsClassificationbl;

import blService.goodsClassificationblService.GoodsClasssficationblService;
import exception.ExistException;
import po.GoodsClassificationPO;
import util.ResultMessage;
import vo.GoodsClassificationVO;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class GoodsClassificationController implements GoodsClasssficationblService {
    GoodsClassification goodsClassification = new GoodsClassification();
    @Override
    public List<GoodsClassificationVO> show() {
        return goodsClassification.show();
    }

    @Override
    public String getID(String fatherId,int order) {
        String id = goodsClassification.getID(fatherId,order);
        return id;
    }

    @Override
    public ResultMessage addGoodsClassification(GoodsClassificationVO vo) throws ExistException {
        return goodsClassification.addGoodsClassification(vo);
    }

    @Override
    public ResultMessage deleteGoodsClassification(GoodsClassificationVO vo) {
        ResultMessage resultMessage = goodsClassification.deleteGoodsClassification(vo);
        return resultMessage;
    }

    @Override
    public ResultMessage updateGoodsClassification(GoodsClassificationVO vo) {
        return goodsClassification.updateGoodsClassification(vo);
    }
}
