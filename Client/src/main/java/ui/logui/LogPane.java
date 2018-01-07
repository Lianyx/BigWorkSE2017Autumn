package ui.logui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import org.fxmisc.richtext.CodeArea;
import org.fxmisc.richtext.LineNumberFactory;
import po.LogPO;
import ui.util.ArrowFactory;

import java.util.function.IntFunction;

public class LogPane extends AnchorPane {

    @FXML
    CodeArea codeArea;

    public LogPane(){

        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/logui/logpane.fxml"));
            fxmlLoader.setRoot(this);
            fxmlLoader.setController(this);
            fxmlLoader.load();
        }catch (Exception e){
            e.printStackTrace();
        }
        IntFunction<Node> numberFactory = LineNumberFactory.get(codeArea);
        IntFunction<Node> arrowFactory = new ArrowFactory(codeArea.currentParagraphProperty());
        IntFunction<Node> graphicFactory = line -> {
            HBox hbox = new HBox(
                    numberFactory.apply(line),
                    arrowFactory.apply(line));
            hbox.setAlignment(Pos.CENTER_LEFT);
            return hbox;
        };
        codeArea.setParagraphGraphicFactory(graphicFactory);
        codeArea.replaceText("The green arrow will only be on the line where the caret appears.\n\nTry it.");
        codeArea.moveTo(0, 0);
        codeArea.setEditable(false);
    }



}
