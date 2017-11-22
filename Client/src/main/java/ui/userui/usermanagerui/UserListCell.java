package ui.userui.usermanagerui;

import javafx.beans.InvalidationListener;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import utilComponent.CustomListCell;
import vo.UserListVO;
import vo.UserVO;

public class UserListCell extends CustomListCell<UserListVO> {

    private UserHbox data;


    @Override
    public synchronized void updateItem(UserListVO item, boolean empty) {
        super.updateItem(item, empty);
        if(!empty)
        {
            data = new UserHbox();
            data.setInfo(item);

            setGraphic(data);
        }else{
            setGraphic(null);
        }


    }



}
