package ui.managerui.businessConditionui;

import blService.businessblservice.BusinessConditionblService;
import businesslogic.checkbl.MyServiceFactory;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRippler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import po.BusinessConditionPO;
import ui.common.FXMLRefreshableAnchorPane;
import ui.common.GatePane;
import ui.managerui.common.MyBoardController;
import ui.util.DoubleButtonDialog;
import ui.util.GetTask;
import ui.util.PaneFactory;
import ui.util.Refreshable;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class BusinessConditionPane extends GatePane {
    @FXML
    private JFXRippler search;
    @FXML
    private JFXDatePicker beginTimePicker, endTimePicker;
    @FXML
    private PieChart incomePieChart, costPieChart;
    @FXML
    private Label salesIncomeLabel, overflowIncomeLabel, purPriceAdjustIncomeLabel, priceDiffIncomeLabel, tokenIncomeLabel;
    @FXML
    private Label purCostLabel, damageCostLabel, giftCostLabel;
    @FXML
    private Label incomeLabel, discountLabel, costLabel, profitLabel;

    private BusinessConditionblService businessConditionblService;

    private BusinessConditionPO businessConditionPO;

    public BusinessConditionPane() {
        beginTimePicker.setValue(LocalDate.now().minusDays(30));
        endTimePicker.setValue(LocalDate.now().plusDays(30));
    }

    /**
     * implement methods
     */

    @Override
    protected String getURL() {
        return "/managerui/BusinessConditionPane.fxml";
    }

    @Override
    protected void refreshAfterMath() {
        setDataPresentation();
    }

    @Override
    protected void initiateService() throws RemoteException, NotBoundException, MalformedURLException {
        businessConditionblService = MyServiceFactory.getBusinessConditionblService();
    }

    @Override
    protected void updateDataFromBl() throws RemoteException {
        businessConditionPO = businessConditionblService.search(LocalDateTime.of(beginTimePicker.getValue(), LocalTime.MIN), LocalDateTime.of(endTimePicker.getValue(), LocalTime.MIN));
    }

    /**
     * FXML methods
     */

    @FXML
    private void search() {
        refresh(false);
    }

    /**
     * private methods
     * */

    private void setDataPresentation() {
        ObservableList<PieChart.Data> incomePieChartData = FXCollections.observableArrayList();
        incomePieChartData.addAll(new PieChart.Data("销售收入", businessConditionPO.getSalesIncome()),
                new PieChart.Data("商品报溢收入", businessConditionPO.getOverFlowIncome()),
                new PieChart.Data("成本调价收入", businessConditionPO.getPurPriceAdjustIncome()),
                new PieChart.Data("进货退货差价", businessConditionPO.getPriceDiffIncome()),
                new PieChart.Data("代金券与实际收款差额", businessConditionPO.getTokenIncome()));

        ObservableList<PieChart.Data> costPieChartData = FXCollections.observableArrayList();
        costPieChartData.addAll(new PieChart.Data("销售成本", businessConditionPO.getPurCost()),
                new PieChart.Data("商品报损支出", businessConditionPO.getDamageCost()),
                new PieChart.Data("商品赠出支出", businessConditionPO.getGiftCost()));

        incomePieChart.getData().setAll(incomePieChartData);
        costPieChart.getData().setAll(costPieChartData);

        salesIncomeLabel.setText("销售收入：" + businessConditionPO.getSalesIncome());
        overflowIncomeLabel.setText("商品报溢收入：" + businessConditionPO.getOverFlowIncome());
        purPriceAdjustIncomeLabel.setText("成本调价收入：" + businessConditionPO.getPurPriceAdjustIncome());
        priceDiffIncomeLabel.setText("进货退货差价收入：" + businessConditionPO.getPriceDiffIncome());
        tokenIncomeLabel.setText("代金券与实际收款差额：" + businessConditionPO.getTokenIncome());

        purCostLabel.setText("销售成本：" + businessConditionPO.getPurCost());
        damageCostLabel.setText("商品报损支出：" + businessConditionPO.getDamageCost());
        giftCostLabel.setText("商品赠出支出：" + businessConditionPO.getGiftCost());

        incomeLabel.setText("总收入：" + businessConditionPO.getTotalIncome());
        discountLabel.setText("折让：" + businessConditionPO.getDiscount());
        costLabel.setText("总支出：" + businessConditionPO.getTotalCost());
        profitLabel.setText("总利润：" + businessConditionPO.getTotalProfit());
    }

}
