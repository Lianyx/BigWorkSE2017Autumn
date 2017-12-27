package ui.managerui.promotion;

import com.jfoenix.controls.JFXTreeTableColumn;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TreeTableColumn;
import vo.promotionVO.MemberPromotionVO;
import vo.promotionVO.PromotionGoodsItemVO;
import vo.promotionVO.PromotionVO;

import java.io.IOException;

public class MemberPromotionDetailPane extends PromotionDetailPane {
    private MemberPromotionVO memberPromotionVO;

    public MemberPromotionDetailPane(MemberPromotionVO memberPromotionVO) {
        this();
        this.memberPromotionVO = memberPromotionVO;

        initialGifts();
    }

    public MemberPromotionDetailPane() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/managerui/MemberPromotionDetailPane.fxml"));
            fxmlLoader.setRoot(this);
            fxmlLoader.setController(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    void initialGifts() {
        JFXTreeTableColumn<PromotionGoodsItemVO, String> idColumn = new JFXTreeTableColumn<>("商品编号");
        idColumn.setPrefWidth(100);
        idColumn.setCellValueFactory((TreeTableColumn.CellDataFeatures<PromotionGoodsItemVO, String> param) -> new ReadOnlyStringWrapper(param.getValue().getValue().getId()));

        JFXTreeTableColumn<PromotionGoodsItemVO, String> nameColumn = new JFXTreeTableColumn<>("商品名称");
        nameColumn.setPrefWidth(100);
        nameColumn.setCellValueFactory((p) -> new ReadOnlyStringWrapper(p.getValue().getValue().getName()));

        JFXTreeTableColumn<PromotionGoodsItemVO, String> endTimeColumn = new JFXTreeTableColumn<>("单价");
        endTimeColumn.setPrefWidth(100);
        endTimeColumn.setCellValueFactory((p) -> new ReadOnlyStringWrapper(String.valueOf(p.getValue().getValue().getUnitPrice())));

    }

    @FXML
    public void modify() {

    }

    @Override
    public void refresh() { // TODO

    }

}
