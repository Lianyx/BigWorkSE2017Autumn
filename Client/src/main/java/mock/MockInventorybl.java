package mock;

import po.InventoryManagerPO.GoodsPO;
import util.ResultMessage;

import java.util.ArrayList;

public class MockInventorybl {

    public ResultMessage InventoryGiftUpdate(ArrayList<GoodsPO> list){
        return ResultMessage.SUCCESS;
    }


}
