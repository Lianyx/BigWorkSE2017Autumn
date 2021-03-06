package ui.inventoryui.goodsui;

import blService.goodsblService.GoodsblService;
import com.jfoenix.controls.*;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.scene.control.*;
import javafx.util.Callback;
import ui.common.BoardController;
import ui.util.*;
import vo.inventoryVO.GoodSearchVO;
import vo.inventoryVO.GoodsVO;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Set;

public class GoodsTreeTable extends ReceiptTreeTable<GoodsVO> {
    private GoodsblService goodsblService;
    private GoodSearchVO goodSearchVO;

    public GoodsTreeTable() {
        super();
        rowsPerPage = 7;
        goodsblService = GoodsListPane.goodsblService;//ServiceFactory_Stub.getService(GoodsblService.class.getName());
        //this.goodsblService = ServiceFactory_Stub.getService(GoodsblService.class.getName());

        JFXTreeTableColumn<GoodsVO,Boolean> choose = new JFXTreeTableColumn("  ");
        choose.setPrefWidth(40);
        columnDecorator.setupCellValueFactory(choose, s -> s.selectedProperty().asObject());
        choose.setCellFactory(t->new ChooseCell<GoodsVO>(chosenItem));


        JFXTreeTableColumn<GoodsVO, String> goodName = new JFXTreeTableColumn("GoodName");
        goodName.setPrefWidth(60);
        columnDecorator.setupCellValueFactory(goodName,s->new ReadOnlyObjectWrapper<>(s.getGoodName()));
        goodName.setCellFactory(t->new SearchableStringCell<>(keyword));

        JFXTreeTableColumn<GoodsVO, String> goodId = new JFXTreeTableColumn("GoodId");
        goodId.setPrefWidth(120);
        columnDecorator.setupCellValueFactory(goodId,s->new ReadOnlyObjectWrapper<>(s.getId()));

        JFXTreeTableColumn<GoodsVO, String> goodType = new JFXTreeTableColumn("GoodType");
        goodType.setPrefWidth(125);
        columnDecorator.setupCellValueFactory(goodType,s->new ReadOnlyObjectWrapper(s.getGoodType()));
        goodType.setCellFactory(new Callback<TreeTableColumn<GoodsVO, String>, TreeTableCell<GoodsVO, String>>() {
            @Override
            public TreeTableCell<GoodsVO, String> call(TreeTableColumn<GoodsVO, String> param) {
                return new ButtonCell() {
                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if(item!=null){
                            setButtonStyle("-fx-text-fill: white;-fx-background-radius: 10; -fx-background-color:red");
                        }
                    }
                };
            }
        });

        JFXTreeTableColumn<GoodsVO, Integer> inventoryNum = new JFXTreeTableColumn("Num");
        inventoryNum.setPrefWidth(120);
        columnDecorator.setupCellValueFactory(inventoryNum,s->new ReadOnlyObjectWrapper<>(s.getInventoryNum()));

        JFXTreeTableColumn<GoodsVO, Boolean> more = new JFXTreeTableColumn("");
        more.setPrefWidth(30);
        columnDecorator.setupCellValueFactory(more, s -> new ReadOnlyObjectWrapper(s.isMultiple()));
        more.setCellFactory(new Callback<TreeTableColumn<GoodsVO, Boolean>, TreeTableCell<GoodsVO, Boolean>>() {
            @Override
            public TreeTableCell<GoodsVO, Boolean> call(TreeTableColumn<GoodsVO, Boolean> param) {
                MultiCell multiCell = new MultiCell();
                multiCell.setRunnable1(()->{
                    GoodDetailPane goodDetailPane = null;
                    try {
                        goodDetailPane = new GoodDetailPane(((GoodsVO)multiCell.getTreeTableRow().getTreeItem().getValue()).getId());
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    } catch (NotBoundException e) {
                        e.printStackTrace();
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                    goodDetailPane.refresh(true);});
                multiCell.setRunnable2(()->{
                    try {
                        goodsblService.deleteGoods(((GoodsVO)multiCell.getTreeTableRow().getTreeItem().getValue()));
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    BoardController.getBoardController().refresh();});
                return multiCell;
            }
        });

        this.setRowFactory(tableView-> {
            JFXTreeTableRow<GoodsVO> row = new JFXTreeTableRow();
            RowSetter(row,()->{
                GoodDetailPane goodDetailPane = null;
                try {
                    goodDetailPane = new GoodDetailPane(row.getTreeItem().getValue().getId());
                } catch (RemoteException e) {
                    e.printStackTrace();
                } catch (NotBoundException e) {
                    e.printStackTrace();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                goodDetailPane.refresh(true);});
            return row;
        });

        this.getColumns().addAll(choose,goodName,goodId,goodType,inventoryNum,more);
    }



    public void setGood(Set<GoodsVO> goods){observableList.setAll(goods);}

    public void removeGood(GoodsVO goodsVO){observableList.remove(goodsVO);}

    public GoodSearchVO getGoodSearchVO(){return  goodSearchVO;}

    public void setGoodSearchVO(GoodSearchVO goodSearchVO) {
        this.goodSearchVO = goodSearchVO;
    }

    @Override
    public void delete(Pagination p) {
        chosenItem.getSet().forEach(s -> {observableList.remove(s);
            try {
                goodsblService.deleteGoods(s);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        });
        p.setPageCount(observableList.size() / getRowsPerPage() + 1);
        createPage(p.getCurrentPageIndex());
        p.setCurrentPageIndex(p.getCurrentPageIndex());
        chosenItem.getSet().clear();

    }
}
