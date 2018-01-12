package ui.inventoryui.goodsui;

import blService.blServiceFactory.ServiceFactory_Stub;
import blService.goodsblService.GoodsblService;
import blServiceStub.goodsblservice_Stub.GoodsblService_Stub;
import businesslogic.goodsbl.Goodsbl;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTreeTableRow;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

import org.controlsfx.control.PopOver;
import po.GoodsPO;
import ui.managerui.common.MyBoardController;
import ui.util.*;
import vo.inventoryVO.GoodSearchVO;
import vo.inventoryVO.GoodsVO;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class GoodsListPane extends ReceiptListPane<GoodsVO> {
    public static GoodsblService goodsblService;

    private static GoodSearchVO goodSearchVO = new GoodSearchVO();

    private static FilterPane filterPane;

    SimpleStringProperty match = new SimpleStringProperty("");

    public GoodsListPane() throws Exception {
        super("/inventoryui/goodui/goodslistpane.fxml");
        this.goodsblService = new Goodsbl();//ServiceFactory_Stub.getService(GoodsblService.class.getName());// new Goodsbl();
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
                    new DoubleButtonDialog(mainpane, "Wrong", "sabi", "Last", "Ret");
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
//                        switchPane(toSwitch);
                        MyBoardController.getMyBoardController().switchTo(this);
                    }, buttonDialog, p);

            new Thread(getTask).start();
        } catch (Exception e) {
            System.exit(1);
            e.printStackTrace();

        }

    }

    @Override
    public void deleteList() {
        DoubleButtonDialog doubleButtonDialog = new DoubleButtonDialog(mainpane, "Delete", "sabi", "Yes", "No");
        doubleButtonDialog.setButtonOne(() -> {
            receiptTreeTable.delete(pagination);
 /*                   JFXTreeTableRow<GoodsVO> row = new JFXTreeTableRow();
                    // RowSetter(row,()->{
                    String goodId = row.getTreeItem().getValue().getId();
                    System.out.println(goodId);
         *//*   try {
                goodsblService.deleteGoods(new GoodsVO(goodId));
            } catch (RemoteException e) {
                e.printStackTrace();
            }*//*
                    // return row;*/
        });

        doubleButtonDialog.setButtonTwo(() -> {
        });
        doubleButtonDialog.show();
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
//            switchPane(false);
            MyBoardController.getMyBoardController().switchTo(this);
        }

    }

    @Override
    public void add() {
        System.out.println("????");
        GoodDetailPane goodDetailPane = new GoodDetailPane(true);
    }
}
