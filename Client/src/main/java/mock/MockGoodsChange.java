package mock;

import item.GoodsChangeItem;
import util.ResultMessage;

import java.util.ArrayList;
import java.util.Set;

public class MockGoodsChange {

    public ResultMessage update(ArrayList<GoodsChangeItem> goods){
        return ResultMessage.SUCCESS;

    }


}
