package ui.memberui;

import blService.memberblService.MemberInfo;
//import blServiceStub.memberblservice_Stub.MemberInfo_Stub;
import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import org.controlsfx.control.ListSelectionView;
import vo.MemberChooseVO;

import java.util.ArrayList;
import java.util.Observable;
import java.util.stream.Collectors;

public class ChoosePane extends AnchorPane {

    ObservableList<String> observableList = FXCollections.observableArrayList();


    MemberInfo memberInfo;

    @FXML
    ListSelectionView<String> selectView;

    @FXML
    JFXButton save;

    @FXML
    JFXButton cancel;

    ObservableList<MemberChooseVO> memberChooseVOs = FXCollections.observableArrayList();

    public ChoosePane(){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/memberui/choosepane.fxml"));
            fxmlLoader.setRoot(this);
            fxmlLoader.setController(this);
            fxmlLoader.load();
        }catch (Exception e){
            e.printStackTrace();
        }
//        memberInfo = new MemberInfo_Stub();
//        selectView.setSourceItems(observableList);
//        observableList.setAll(memberInfo.getAll());

    }


    public ChoosePane(MemberInfo memberInfo){
        this();
        this.memberInfo = memberInfo;
    }

    @FXML
    public void save(){
        memberChooseVOs.clear();
        selectView.getTargetItems().stream().forEach(i->{
            memberChooseVOs.add(new MemberChooseVO(i.split(" ")[1],Integer.parseInt(i.split(" ")[0])));
        });
    }

    @FXML
    public void cancel(){

    }

    public ArrayList<MemberChooseVO> getMemberChooseVOs() {
        ArrayList<MemberChooseVO> list = new ArrayList<>();
        memberChooseVOs.stream().forEach(i->list.add(i));
        return list;
    }

}
