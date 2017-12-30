package ui.userui.usermanagerui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import util.UserCategory;
import vo.UserSearchVO;

public class FilterPane extends AnchorPane{


    @FXML
    JFXComboBox usertype;

    @FXML
    JFXButton save;

    UserListPane ulp;


    UserSearchVO userSearchVO;

    public FilterPane(){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/userui/filter.fxml"));
            fxmlLoader.setRoot(this);
            fxmlLoader.setController(this);
            fxmlLoader.load();
            usertype.getItems().add(new Label(UserCategory.InventoryManager.name()));
            usertype.getItems().add(new Label(UserCategory.Salesman.name()));
            usertype.getItems().add(new Label(UserCategory.SalesManager.name()));
            usertype.getItems().add(new Label(UserCategory.Accountant.name()));
            usertype.getItems().add(new Label(UserCategory.GeneralManager.name()));

        }catch (Exception e){
            e.printStackTrace();
        }
    }



    public UserSearchVO getUserSearchVO() {
        return userSearchVO;
    }

    public void setUlp(UserListPane ulp) {
        this.ulp = ulp;
    }


    public void setUserSearchVO(UserSearchVO userSearchVO) {
        this.userSearchVO = userSearchVO;
    }

    @FXML
    public void save(){
        this.userSearchVO.setUserCategory(UserCategory.map.get(((Label)usertype.getSelectionModel().getSelectedItem()).getText()));
        ulp.refreshUlv();
    }
}
