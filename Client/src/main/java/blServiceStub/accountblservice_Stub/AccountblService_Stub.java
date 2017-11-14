package blServiceStub.accountblservice_Stub;

import blService.accountblservice.AccountblService;
import util.ResultMessage;
import vo.AccountVO;

import java.util.Set;

public class AccountblService_Stub implements AccountblService{
    public Set<AccountVO> showAllAcounts(){
        Set<AccountVO> set=new Set;
        return set;
    }

    public Set<AccountVO> search(String keyword){
        Set<AccountVO> set=new Set;
        return set;
    }

    public ResultMessage add(AccountVO vo){
        return ResultMessage.SUCCESS;
    }


    public ResultMessage update(String id){
        return  ResultMessage.SUCCESS;
    }
}
