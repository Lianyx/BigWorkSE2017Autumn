package vo;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleBooleanProperty;

import java.io.Serializable;

public class AccountListVO extends RecursiveTreeObject<AccountListVO> implements Serializable {

    private int ID;
    private String name;
    private double balance;
    private SimpleBooleanProperty selected=new SimpleBooleanProperty(false);

    public AccountListVO(){

    }

    public AccountListVO(String name, double balance) {
        this.name = name;
        this.balance = balance;
    }

    public AccountListVO(int ID, String name, double balance, SimpleBooleanProperty selected) {
        this.ID = ID;
        this.name = name;
        this.balance = balance;
        this.selected.setValue(false);
    }

    public AccountListVO(int ID, String name, double balance) {
        this.ID = ID;
        this.name = name;
        this.balance = balance;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public boolean isSelected() {
        return selected.get();
    }

    public SimpleBooleanProperty selectedProperty() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected.set(selected);
    }

    public String toString(){
        return "AccountListVO{"+ID+" "+name+" "+balance+" "+selected+"}";
    }

}
