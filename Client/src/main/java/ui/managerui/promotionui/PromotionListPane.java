package ui.managerui.promotionui;

import blService.promotionblService.PromotionListblService;
import businesslogic.promotionbl.PromotionFactory;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPopup;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import org.controlsfx.control.PopOver;
import ui.managerui.common.MyBoardController;
import ui.managerui.common.MyTwoButtonDialog;
import ui.managerui.promotionui.addPopUpRelated.CombineLabel;
import ui.managerui.promotionui.addPopUpRelated.MemberLabel;
import ui.managerui.promotionui.addPopUpRelated.TotalLabel;
import ui.util.*;
import vo.PromotionSearchVO;
import vo.promotionVO.PromotionVO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class PromotionListPane extends Refreshable { // TODO Refreshable改成接口吧？
    @FXML
    private JFXButton filter, add;
    @FXML
    private BorderPane borderPane;
    @FXML
    private JFXTextField keywordField;
    private StringProperty keywordProperty = new SimpleStringProperty("");

    private PromotionTreeTable promotionTreeTable;

    private PromotionListblService promotionListblService;
    private Set<PromotionVO> chosenItems = new HashSet<>();
    private PromotionSearchVO promotionSearchVO = new PromotionSearchVO();

    private ArrayList<PromotionVO> keywordFilterList = new ArrayList<>();

    public PromotionListPane() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/managerui/promotionListPane.fxml"));
            fxmlLoader.setRoot(this);
            fxmlLoader.setController(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        promotionTreeTable = new PromotionTreeTable(chosenItems, keywordFilterList, keywordProperty, borderPane);

        PopOver filterPopOver = new PopOver();
        filterPopOver.setDetachable(false);
        filterPopOver.setArrowLocation(PopOver.ArrowLocation.TOP_RIGHT);
        PromotionFilterPane promotionFilterPane = new PromotionFilterPane(this, promotionSearchVO);
        filterPopOver.setContentNode(promotionFilterPane);
        filter.setOnAction(e -> filterPopOver.show(filter));

        VBox addList = new VBox();
        addList.setPrefHeight(130);
        addList.setPrefWidth(100);
        addList.getChildren().addAll(new TotalLabel(), new MemberLabel(), new CombineLabel());
        JFXPopup addPopup = new JFXPopup(addList);
        add.setOnAction(event -> addPopup.show(add));


//        keywordField.textProperty().addListener((observable, oldValue, newValue) -> {
//            keywordFilter(newValue);
//        });
        keywordProperty.bind(keywordField.textProperty()); // TODO 这里要加，感觉应该listPane只需要refresh那一个接口，剩下的都让TreeTable来管
    }

    @FXML
    private void SearchKeyword() {
    }

    @FXML
    public void delete() {
        new MyTwoButtonDialog("请确认删除", this::deleteTask).show();
    }

    private void deleteTask() {
        MyBoardController myBoardController = MyBoardController.getMyBoardController();
        myBoardController.Loading();
        ArrayList<PromotionVO> tempList = new ArrayList<>();

        DoubleButtonDialog buttonDialog = new DoubleButtonDialog(PaneFactory.getMainPane(), "Wrong", "连接失败", "重试", "返回");
        buttonDialog.setButtonOne(this::delete);
        buttonDialog.setButtonTwo(myBoardController::Ret);

        GetTask getTask = new GetTask(() -> {
            promotionTreeTable.refresh(tempList);
            myBoardController.switchTo(this);

        }, buttonDialog, woid -> {
            try {
                for (PromotionVO chosenItem : new ArrayList<>(chosenItems)) {
                    chosenItem.getService().delete(chosenItem);
                    chosenItems.remove(chosenItem);
                }

                ArrayList<PromotionVO> promotions;
                if ((promotions = promotionListblService.search(promotionSearchVO.toSC())) == null) {
                    return false;
                }
                tempList.clear();
                tempList.addAll(promotions);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        });
        new Thread(getTask).start();
    }

    // TODO 这样写，toSwitch已经没用了
    @Override
    public void refresh(boolean toSwitch) {
        MyBoardController myBoardController = MyBoardController.getMyBoardController();
        myBoardController.Loading();
        ArrayList<PromotionVO> tempList = new ArrayList<>();

        DoubleButtonDialog buttonDialog = new DoubleButtonDialog(PaneFactory.getMainPane(), "Wrong", "连接失败", "重试", "返回");
        buttonDialog.setButtonOne(() -> refresh(false));
        buttonDialog.setButtonTwo(myBoardController::Ret);

        GetTask getTask = new GetTask(() -> {
            promotionTreeTable.refresh(tempList);
//            keywordFilter(keywordField.getText());
            
            myBoardController.switchTo(this);
        }, buttonDialog, p -> {
            try {
                if (promotionListblService == null) { // 说明是第一次
                    promotionListblService = PromotionFactory.getPromotionListblService();
                }

                ArrayList<PromotionVO> promotions;
                if ((promotions = promotionListblService.search(promotionSearchVO.toSC())) == null) {
                    return false;
                }
                tempList.clear();
                tempList.addAll(promotions);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        });
        new Thread(getTask).start();
    }

//    private void keywordFilter(String newValue) {
//        keywordFilterList.clear();
//        keywordProperty.set(newValue);
//        promotionTreeTable.refresh(keywordFilterList);
//    }
}
