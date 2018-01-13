package ui.common.dialog;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import javafx.scene.control.Label;
import ui.util.PaneFactory;

public class MyTwoButtonDialog extends JFXDialog {
    public MyTwoButtonDialog(String content, Runnable confirmAction) {
        this(content, confirmAction, ()->{});
    }

    public MyTwoButtonDialog(String content, Runnable confirmAction, Runnable cancelAction) {
        JFXDialogLayout jfxDialogLayout = new JFXDialogLayout();
        jfxDialogLayout.setBody(new Label(content));

        setDialogContainer(PaneFactory.getMainPane());
        setContent(jfxDialogLayout);

        JFXButton button = new JFXButton("确认");
        JFXButton re = new JFXButton("取消");

        button.setOnAction(e -> {
            close();
            confirmAction.run();
        });
        re.setOnAction(e -> {
            close();
            cancelAction.run();
        });
        jfxDialogLayout.setActions(button, re);
    }

}
