package blServiceStub.accountblservice_Stub;

import blService.accountblService.AccountblService;
import util.ResultMessage;
import vo.AccountVO;

import java.util.HashSet;
import java.util.Set;

public class AccountblService_Stub implements AccountblService{
    public Set<AccountVO> showAllAcounts(){
        Set<AccountVO> set=new HashSet();
        return set;
    }

    public Set<AccountVO> search(String keyword){
        Set<AccountVO> set=new HashSet();
        return set;
    }

    public ResultMessage add(AccountVO vo){
        return ResultMessage.SUCCESS;
    }


    public ResultMessage update(String id){
        return  ResultMessage.SUCCESS;
    }
}
