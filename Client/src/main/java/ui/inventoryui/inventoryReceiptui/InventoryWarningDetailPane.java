package ui.inventoryui.inventoryReceiptui;

import blService.checkblService.ReceiptblService;
import blService.inventoryblService.InventoryWarningReceiptblService;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import ui.inventoryui.goodsui.GoodChoose;
import ui.common.MyReceiptDetailPane;
import vo.inventoryVO.inventoryReceiptVO.InventoryWarningReceiptVO;
import vo.inventoryVO.inventoryReceiptVO.ReceiptGoodsItemVO;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;

public class InventoryWarningDetailPane extends MyReceiptDetailPane<InventoryWarningReceiptVO> {
    @FXML
    InventoryWarningTreeTable warningItemTreeTable;

    ObservableList<ReceiptGoodsItemVO> observableList = FXCollections.observableArrayList();

    @FXML
    private TextArea commentArea;
    @FXML
    private TextField operator;
    @FXML
    private TextField stateField;


    public InventoryWarningDetailPane() {
        warningItemTreeTable.setEditable(true);

    }
    
    public InventoryWarningDetailPane(InventoryWarningReceiptVO receiptVO) {
        super(receiptVO);
        stateField.setDisable(true);
        warningItemTreeTable.setList(receiptVO.getItems());
        warningItemTreeTable.setEditable(true);
    }

    @Override
    protected String getURL() {
        return "/inventoryui/inventoryreceiptui/inventoryWarningDetailPane.fxml";
    }

    @Override
    protected Class<? extends ReceiptblService<InventoryWarningReceiptVO>> getServiceClass() {
        return InventoryWarningReceiptblService.class;
    }

    @Override
    protected boolean validate() {
        return super.validate();
    }

    @Override
    protected void updateReceiptVO() { // 这里不需要再检查了
        List<ReceiptGoodsItemVO> goodsList = warningItemTreeTable.getList();
        System.out.println(operator.getText());
        receiptVO.setOperatorId(Integer.parseInt(operator.getText()));
        receiptVO.setComment(commentArea.getText());
        receiptVO.setItems(goodsList);

        // TODO 我没管transferList相关的（下面的reset同理）。而且这个vo里面没有comment………
    }

    @Override
    protected void setRedCredit(InventoryWarningReceiptVO redCreditVO) {
        List<ReceiptGoodsItemVO> list = redCreditVO.getItems();

        int index = 0;
        for (ReceiptGoodsItemVO vo:list) {
            vo.setInventoryNum(vo.getInventoryNum()*(-1));
            vo.setWarningNum(vo.getWarningNum()*(-1));
            list.set(index++,vo);
        }
        redCreditVO.setItems(list);
    }

    @FXML
    @Override
    protected void reset() {
        super.reset();
        operator.setText(String.valueOf(receiptVO.getOperatorId()));
        commentArea.setText(receiptVO.getComment());
        stateField.setText(receiptVO.getReceiptState().toString());

        warningItemTreeTable.setList(receiptVO.getItems());
    }

    @FXML
    public void addGoods() throws RemoteException, NotBoundException, MalformedURLException {
        GoodChoose goodsChooseInfo = new GoodChoose();

        goodsChooseInfo.choose(observableList,new SimpleIntegerProperty());
    }
}
