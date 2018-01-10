package data.initialdata;

import java.rmi.RemoteException;

public class itest {
    public static void main(String[] args){

        try{
            InitialData initialData =new InitialData();
            initialData.initial("2011");
        }catch (RemoteException e){
            e.printStackTrace();
        }

    }

}
