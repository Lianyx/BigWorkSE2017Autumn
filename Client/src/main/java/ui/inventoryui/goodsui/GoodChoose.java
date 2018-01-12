package ui.inventoryui.goodsui;

import businesslogic.goodsbl.Goodsbl;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import ui.inventoryui.GoodsChooseInfo;
import ui.util.PaneFactory;
import vo.inventoryVO.GoodsVO;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;

public class GoodChoose implements GoodsChooseInfo {
    List<String> goodsId;

    Goodsbl goodsbl;

    @Override
    public List<String> choose(List<String> list) {
        ChoosePane choosePane = new ChoosePane();
        try {
            goodsbl = new Goodsbl();
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        JFXDialogLayout jfxDialogLayout = new JFXDialogLayout();
        jfxDialogLayout.setPrefWidth(220.0);
        //jfxDialogLayout.setHeading(new Label("GoodsCl"));
        //JFXTextField name = new JFXTextField();
        jfxDialogLayout.setBody(choosePane);
        JFXButton save = new JFXButton("Save");
        JFXDialog dialog = new JFXDialog(PaneFactory.getMainPane(), jfxDialogLayout, JFXDialog.DialogTransition.CENTER);

        JFXButton button = new JFXButton("确认");

        choosePane.setDialog(dialog);

        GoodChooseui_stub stub = new GoodChooseui_stub();

        choosePane.setObservableList(stub.getGoods());

        button.setOnAction(e -> {
            goodsId = choosePane.getChooseList();

            System.out.println(goodsId.toString());

            list.addAll(goodsId);

            System.out.println(list.toString());
            dialog.close();
        });
        jfxDialogLayout.setActions(button);


        dialog.show();

        //System.out.println(goodsId);
        return goodsId;
    }

    public void setGoodsId(List<String> goodsId) {
        this.goodsId = goodsId;
    }
}
