package ui.logui;

import blService.logblService.LogblService;
import businesslogic.logbl.Logbl;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import org.fxmisc.richtext.CodeArea;
import org.fxmisc.richtext.LineNumberFactory;
import po.LogPO;
import ui.common.bigPane.GatePane;
import ui.util.ArrowFactory;
import util.EventCategory;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.function.IntFunction;

public class LogPane extends GatePane {

    @FXML
    CodeArea codeArea;


    LogblService logblService;

    ArrayList<LogPO> list;


    public LogPane(){
        super();
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
        codeArea.moveTo(0, 0);
        codeArea.setEditable(false);

        try{
            logblService = new Logbl();

            setList(logblService.getList());}
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected String getURL() {
        return "/logui/logpane.fxml";
    }

    @Override
    protected void refreshAfterMath() {
        setList(list);
    }

    @Override
    protected void initiateService() throws RemoteException, NotBoundException, MalformedURLException {
        logblService = new Logbl();
    }

    @Override
    protected void updateDataFromBl() throws RemoteException {
        list = logblService.getList();
    }

    public ArrayList<LogPO> getList() {
        return list;
    }

    public void setList(ArrayList<LogPO> list) {
        this.list = list;
        codeArea.clear();
        for(LogPO logPO:list){
            codeArea.appendText(logPO.getCreateTime().toString()+"\n"+
                    logPO.getUserCategory()+": "+logPO.getUsername()+"\n"+
                            EventCategory.map.get(logPO.getEventCategory())+
                    logPO.getEvent()+"\n" +
                    "--------------------------------------" +
                    "\n"
            );
        }
    }

}
