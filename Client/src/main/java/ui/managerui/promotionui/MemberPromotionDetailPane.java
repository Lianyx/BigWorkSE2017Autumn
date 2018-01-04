package ui.managerui.promotionui;

import blService.promotionblService.PromotionblService;
import businesslogic.promotionbl.PromotionFactory;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.cells.editors.IntegerTextFieldEditorBuilder;
import com.jfoenix.controls.cells.editors.TextFieldEditorBuilder;
import com.jfoenix.controls.cells.editors.base.GenericEditableTreeTableCell;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.binding.Bindings;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.layout.StackPane;
import javafx.util.StringConverter;
import vo.promotionVO.MemberPromotionVO;
import vo.promotionVO.PromotionGoodsItemVO;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class MemberPromotionDetailPane extends PromotionDetailPane {
    @FXML
    private StackPane giftsPane;
    @FXML
    private JFXButton save;
    @FXML
    private JFXButton delete;

    private JFXTreeTableView<PromotionGoodsItemVO> giftsTable;
    private MemberPromotionVO memberPromotionVO;

    private PromotionblService<MemberPromotionVO> memberPromotionblService;

    public MemberPromotionDetailPane(MemberPromotionVO memberPromotionVO) {
        this();
        this.memberPromotionVO = memberPromotionVO;

        initiateGifts();
        initiateOtherLayout();

        try { // TODO
            memberPromotionblService = PromotionFactory.getMemberPromotionblService();
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
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

    private void initiateGifts() {
        ObservableList<PromotionGoodsItemVO> gifts = FXCollections.observableArrayList(memberPromotionVO.getGifts());
        TreeItem<PromotionGoodsItemVO> root = new RecursiveTreeItem<>(gifts, RecursiveTreeObject::getChildren);
        giftsTable = new JFXTreeTableView<>(root);

        JFXTreeTableColumn<PromotionGoodsItemVO, String> idColumn = new JFXTreeTableColumn<>("商品编号");
        idColumn.setPrefWidth(94);
        idColumn.setCellValueFactory((TreeTableColumn.CellDataFeatures<PromotionGoodsItemVO, String> param) -> new ReadOnlyStringWrapper(param.getValue().getValue().getId()));

        JFXTreeTableColumn<PromotionGoodsItemVO, String> nameColumn = new JFXTreeTableColumn<>("商品名称");
        nameColumn.setPrefWidth(94);
        nameColumn.setCellValueFactory((p) -> new ReadOnlyStringWrapper(p.getValue().getValue().getName()));

        JFXTreeTableColumn<PromotionGoodsItemVO, Integer> unitPriceColumn = new JFXTreeTableColumn<>("单价");
        unitPriceColumn.setPrefWidth(93);
        unitPriceColumn.setCellValueFactory((p) -> new ReadOnlyObjectWrapper<>(p.getValue().getValue().getUnitPrice()));

        JFXTreeTableColumn<PromotionGoodsItemVO, Integer> numColumn = new JFXTreeTableColumn<>("数量");
        numColumn.setPrefWidth(93);
        numColumn.setCellValueFactory(p -> p.getValue().getValue().numProperty().asObject());
        numColumn.setCellFactory((TreeTableColumn<PromotionGoodsItemVO, Integer> param) -> new GenericEditableTreeTableCell<>(
                new IntegerTextFieldEditorBuilder()));
//        numColumn.setOnEditCommit((TreeTableColumn.CellEditEvent<PromotionGoodsItemVO, String> t) -> {
//            t.getTreeTableView()
//                    .getTreeItem(t.getTreeTablePosition().getRow())
//                    .getValue().setNum(Integer.parseInt(t.getNewValue())); // 因为除了memberPromotionVO，其他都是bind在一起地
//            memberPromotionVO.setGifts(gifts);
//        });

        giftsTable.setShowRoot(false);
        giftsTable.setEditable(true);
        giftsTable.getColumns().setAll(idColumn, nameColumn, unitPriceColumn, numColumn);

        giftsPane.getChildren().add(giftsTable);
    }

    private void initiateOtherLayout() { // TODO
        save.layoutYProperty().bind(giftsTable.heightProperty().add(400));
        delete.layoutYProperty().bind(giftsTable.heightProperty().add(400));
    }

    @FXML
    private void modify() {

    }

    @FXML
    private void save() {
        // TODO 先只有update…
        try {
            memberPromotionblService.update(memberPromotionVO);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void delete() {

    }


    @Override
    public void refresh(boolean toSwitch) {

    }
}
