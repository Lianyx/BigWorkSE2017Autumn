package ui.memberui;

import blService.memberblService.MemberInfo;
import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import org.controlsfx.control.ListSelectionView;

public class ChoosePane extends AnchorPane {

    ObservableList<String> observableList = FXCollections.observableArrayList();


    MemberInfo memberInfo;

    @FXML
    ListSelectionView selectView;

    @FXML
    JFXButton save;

    @FXML
    JFXButton cancel;


    public ChoosePane(){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/memberui/choosepane.fxml"));
            fxmlLoader.setRoot(this);
            fxmlLoader.setController(this);
            fxmlLoader.load();
        }catch (Exception e){
            e.printStackTrace();
        }
        selectView.setSourceItems(observableList);
        observableList.add("aaa");


    }


    public ChoosePane(MemberInfo memberInfo){
        this();
        this.memberInfo = memberInfo;

    }

}
