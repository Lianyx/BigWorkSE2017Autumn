package ui.memberui;

import blService.memberblService.MemberInfo;
import com.jfoenix.controls.JFXListCell;
import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;
import ui.messageui.MessageCell;
import vo.MemberVO;

public class ChooseList extends JFXListView<MemberVO> {


    ObservableList<MemberVO> observableList= FXCollections.observableArrayList();

    MemberInfo memberInfo;

    public ChooseList(){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/memberui/memChoose.fxml"));
            fxmlLoader.setRoot(this);
            fxmlLoader.setController(this);
            fxmlLoader.load();
            this.setCellFactory(new Callback<ListView<MemberVO>, ListCell<MemberVO>>() {
                @Override
                public ListCell<MemberVO> call(ListView<MemberVO> param) {
                    return new ChooseListCell();
                }
            });
            this.setItems(observableList);
        }catch (Exception e){
            e.printStackTrace();
        }
    }







}
