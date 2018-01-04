package ui.managerui.common;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import javafx.scene.control.Label;

public class MyInformationDialog extends JFXDialog {
    public MyInformationDialog(String content, Runnable confirmAction) {
        JFXDialogLayout jfxDialogLayout = new JFXDialogLayout();
        jfxDialogLayout.setBody(new Label(content));

        setDialogContainer(Save.mainpane);
        setContent(jfxDialogLayout);

        JFXButton button = new JFXButton("确认");

        button.setOnAction(e -> {
            close();
            confirmAction.run();
        });
        jfxDialogLayout.setActions(button);
    }
}
