package businesslogic.accountbl;

import vo.AccountListVO;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class temp {

    public static void main(String[] args){
        try{
            Accountbl accountbl = new Accountbl();
            ArrayList<AccountListVO> list = accountbl.showAllAccounts();
            for(int i=0;i<list.size();i++){
                System.out.println(list.get(i));
            }
        }catch (Exception e){
            e.printStackTrace();
        }


    }
}
