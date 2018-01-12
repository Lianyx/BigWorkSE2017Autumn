package businesslogic.memberbl;

import blService.memberblService.MemberInfo;
import blService.memberblService.MemberblService;
import util.ResultMessage;

import java.rmi.RemoteException;

public class MemberInfo_Impl implements MemberInfo {
    private MemberblService memberblService;

    public MemberInfo_Impl() throws RemoteException{
        memberblService = new Memberbl();
    }


    @Override
    public ResultMessage updateDebt(int memberId, double debt) {

        return ResultMessage.SUCCESS;
    }

    @Override
    public ResultMessage updateCredit(int memberId, double credit) {
        return ResultMessage.SUCCESS;
    }

}
