package ui.managerui.common;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import javafx.scene.control.Label;

public class MyConfirmDialog extends JFXDialog {
    public MyConfirmDialog(String content, Runnable confirmAction) {
        this(content, confirmAction, ()->{});
    }

    public MyConfirmDialog(String content, Runnable confirmAction, Runnable cancelAction) {
        JFXDialogLayout jfxDialogLayout = new JFXDialogLayout();
        jfxDialogLayout.setBody(new Label(content));

        setDialogContainer(Save.mainpane);
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
