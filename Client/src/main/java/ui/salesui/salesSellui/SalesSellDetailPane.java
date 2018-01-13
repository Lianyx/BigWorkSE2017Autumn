package ui.salesui.salesSellui;

import blService.checkblService.ReceiptblService;
import blService.promotionblService.PromotionInfo;
import blService.salesblService.SalesRetblService;
import blService.salesblService.SalesSellblService;
import businesslogic.checkbl.MyServiceFactory;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import ui.managerui.common.MyOneButtonDialog;
import ui.myAccountantui.common.MyReceiptDetailPane;
import ui.salesui.SalesReceiptPane;
import ui.util.PaneFactory;
import util.ReceiptState;
import vo.promotionVO.PromotionVO;
import vo.receiptVO.SalesRetReceiptVO;
import vo.receiptVO.SalesSellReceiptVO;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class SalesSellDetailPane extends SalesReceiptPane<SalesSellReceiptVO> {
    private PromotionInfo promotionInfo;

    public SalesSellDetailPane() {
    }

    public SalesSellDetailPane(SalesSellReceiptVO receiptVO) {
        super(receiptVO);
    }

    @Override
    public void initiate() {
        super.initiate();

        try { // TODO 如果不是因为大作业来不及……这样的肯定不行
            promotionInfo = MyServiceFactory.getPromotionInfo();
        } catch (RemoteException | NotBoundException | MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected Class<? extends ReceiptblService<SalesSellReceiptVO>> getServiceClass() {
        return SalesSellblService.class;
    }

    @Override
    protected void save() {
        if (validate()) {
            receiptVO.setReceiptState(ReceiptState.PENDING);
            try { // TODO 和上面同理
                ArrayList<PromotionVO> promotions = promotionInfo.getMatch(receiptVO);

                JFXDialogLayout jfxDialogLayout = new JFXDialogLayout();
                jfxDialogLayout.setPrefWidth(220.0);

                VBox vBox = new VBox();
                vBox.getChildren().addAll(promotions.stream().map(PromotionVO::getInfoLabel).collect(Collectors.toList()));
                jfxDialogLayout.setBody(vBox);

                JFXButton save = new JFXButton("Save");
                JFXDialog dialog = new JFXDialog(PaneFactory.getMainPane(), jfxDialogLayout, JFXDialog.DialogTransition.CENTER);

                JFXButton button = new JFXButton("确认");

                dialog.show();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            saveTask();
        } else {
            new MyOneButtonDialog("不合法数据").show();
        }
    }
}
