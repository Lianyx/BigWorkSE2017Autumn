package ui.userui.usermanagerui;

import com.jfoenix.controls.JFXListCell;
import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.util.Callback;

public class MessageListView extends JFXListView {

    ObservableList observableList= FXCollections.observableArrayList();

    //cell
    public MessageListView(){
        super();
        this.setPrefHeight(300);
        this.setPrefWidth(140);
        this.setCellFactory(new Callback<JFXListView, JFXListCell>()
        {
            public JFXListCell call(JFXListView param) {
                return new MessageCell();
            }
        });
        observableList.add(new Object());
        observableList.add(new Object());

        observableList.add(new Object());

        this.setItems(observableList);


    }



}
