package data.accountdata;


import po.AccountPO;

import java.time.LocalDateTime;

public class AccountDataTest {

    public static void main(String[] args){
        AccountData accountData = new AccountData();
        AccountPO accountPO = new AccountPO(null,"lp",100.0);

        accountData.insert(accountPO);
    }
}
