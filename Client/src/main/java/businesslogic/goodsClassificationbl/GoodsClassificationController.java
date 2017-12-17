package businesslogic.goodsClassificationbl;

import blService.goodsClassificationblService.GoodsClasssficationblService;
import exception.ExistException;
import util.ResultMessage;
import vo.GoodsClassificationVO;

import java.util.List;
import java.util.Set;

public class GoodsClassificationController implements GoodsClasssficationblService {
    GoodsClassification goodsClassification = new GoodsClassification();
    @Override
    public List<GoodsClassificationVO> show() {
        return goodsClassification.show();
    }

    @Override
    public String getID(String upID) {
        return null;
    }

    @Override
    public ResultMessage addGoodsClassification(GoodsClassificationVO vo) throws ExistException {
        return goodsClassification.addGoodsClassification(vo);
    }

    @Override
    public ResultMessage deleteGoodsClassification(String ID) {

        return goodsClassification.deleteGoodsClassification(ID);
    }

    @Override
    public ResultMessage updateGoodsClassification(GoodsClassificationVO vo) {
        return goodsClassification.updateGoodsClassification(vo);
    }
}
