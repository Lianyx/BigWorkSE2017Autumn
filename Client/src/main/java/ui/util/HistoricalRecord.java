package ui.util;

import com.sun.org.apache.xpath.internal.operations.Bool;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Control;
import javafx.scene.layout.Pane;
import vo.UserListVO;

import java.sql.Ref;
import java.util.Set;
import java.util.TreeSet;

public class HistoricalRecord {
    private static ObservableList<Refreshable> record= FXCollections.observableArrayList();
    private static int index=0;
    public static SimpleBooleanProperty canBack = new SimpleBooleanProperty(false);
    public static SimpleBooleanProperty canForward = new SimpleBooleanProperty(false);




    static public boolean addRecord(Refreshable pane){
        if(record.get(index).getId().equals(pane.getId())){
            if(index>0)
                canBack.setValue(true);
            if(index==record.size()-1)
            canForward.setValue(false);
            return false;
        }

        if(index!=(record.size()-1))
            record.remove(index+1,record.size());
            record.add(pane);
            index++;
            if(index>0)
                canBack.setValue(true);
            canForward.setValue(false);
        return true;
      }



    public static Refreshable pop(){
        index--;
        if(index==0)
            canBack.setValue(false);
        else
            canBack.setValue(true);
        if(index==record.size()-1)
            canForward.setValue(false);
        else
            canForward.setValue(true);
        return record.get(index);
    }



    public static Pane push(){
        index++;
        if(index==0)
            canBack.setValue(false);
        else
            canBack.setValue(true);
        if(index==record.size()-1)
            canForward.setValue(false);
        else
            canForward.setValue(true);
        return record.get(index);
    }

    public static void addPane(Refreshable pane){
        record.add(pane);
    }

    public static int getIndex(){
        return index;
    }
}
