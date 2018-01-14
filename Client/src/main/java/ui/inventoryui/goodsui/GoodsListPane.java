package ui.inventoryui.goodsui;

import blService.goodsblService.GoodsblService;
import businesslogic.goodsbl.Goodsbl;
import com.jfoenix.controls.JFXTreeTableRow;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.layout.BorderPane;

import ui.common.BoardController;
import ui.util.*;
import vo.inventoryVO.GoodSearchVO;
import vo.inventoryVO.GoodsVO;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class GoodsListPane extends ReceiptListPane<GoodsVO> {
    public static GoodsblService goodsblService;

    private static GoodSearchVO goodSearchVO = new GoodSearchVO();

    private static FilterPane filterPane;

    SimpleStringProperty match = new SimpleStringProperty("");

    public GoodsListPane() throws Exception {
        super("/inventoryui/goodui/goodslistpane.fxml");
        this.goodsblService = new Goodsbl();
        receiptTreeTable = new GoodsTreeTable();
        receiptTreeTable.setPrefSize(600, 435);
        receiptTreeTable.keywordProperty().bind(match);
        borderpane.setTop(new BorderPane(receiptTreeTable));


      /*  filterPane = new FilterPane(filterPopOver, goodSearchVO);
        filterPopOver.setDetachable(false);
        filterPopOver.setArrowLocation(PopOver.ArrowLocation.TOP_RIGHT);
        filterPopOver.setContentNode(filterPane);
        filter.setOnMouseClicked(e -> filterPopOver.show(filter));*/

    }

    @Override
    public void refresh(boolean toSwitch) {
        boardController.Loading();
        try {
            DoubleButtonDialog buttonDialog =
                    new DoubleButtonDialog(mainpane, "错误", "！！！！", "返回", "取消");
            buttonDialog.setButtonTwo(() -> boardController.Ret());
            buttonDialog.setButtonTwo(() -> refresh(false));

            Predicate<Integer> p = (s) -> {
                try {
                    System.out.println("goodUI1");
                    if ((set = new HashSet<>(goodsblService.show())) != null) {
                        System.out.println("goodUI2");
                        System.out.println(set.size());
                        return true;
                    }
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                return false;
            };

            GetTask getTask =
                    new GetTask(() -> {
                        receiptTreeTable.setReceipts(set);
                        pagination.setPageCount(receiptTreeTable.getObservableList().size() / receiptTreeTable.getRowsPerPage() + 1);
                        receiptTreeTable.createPage(0);
                        borderpane.setBottom(pagination);

                        BoardController.getBoardController().switchTo(this);
                    }, buttonDialog, p);

            new Thread(getTask).start();
        } catch (Exception e) {
            System.exit(1);
            e.printStackTrace();

        }

    }

    @Override
    public void deleteList() {
       /* if(receiptTreeTable.chosenItem.getSet().size()==0){
            OneButtonDialog oneButtonDialog = new OneButtonDialog(mainpane,"","请选择商品","继续");
            oneButtonDialog.setButtonOne(()->{});
            oneButtonDialog.show();
        }else {*/
            DoubleButtonDialog doubleButtonDialog = new DoubleButtonDialog(mainpane, "警告", "确认删除？", "确认", "取消");
            doubleButtonDialog.setButtonOne(() -> {
                receiptTreeTable.delete(pagination);
            });

            doubleButtonDialog.setButtonTwo(() -> {
            });
            doubleButtonDialog.show();
      /*  }*/
    }

    @Override
    public void search() {
        if (!searchField.getText().equals("")) {
            match.setValue(searchField.getText().toLowerCase());
            Set<GoodsVO> hashSet;
            hashSet = set.stream().filter(
                    s -> s.getGoodName().contains(match.get()) ||
                            s.getGoodType().contains(match.get()) ||
                            s.getId().contains(match.get()) ||
                            Integer.toString(s.getInventoryNum()).contains(match.get())
            ).collect(Collectors.toSet());
            receiptTreeTable.setReceipts(hashSet);
            pagination.setPageCount(receiptTreeTable.getObservableList().size() / receiptTreeTable.getRowsPerPage() + 1);
            receiptTreeTable.createPage(0);
            borderpane.setBottom(pagination);

            BoardController.getBoardController().switchTo(this);
        }

    }

    @Override
    public void add() throws RemoteException, NotBoundException, MalformedURLException {

    }

}
