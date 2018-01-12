package ui.inventoryui;

import javafx.collections.ObservableList;
import vo.inventoryVO.GoodsVO;

import java.util.List;

public interface GoodsChooseInfo {

    public List<String> choose(ObservableList<String> list);
}
