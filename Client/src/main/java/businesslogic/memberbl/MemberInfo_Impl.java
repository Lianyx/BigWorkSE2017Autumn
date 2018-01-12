package businesslogic.memberbl;

import blService.memberblService.MemberInfo;
import blService.memberblService.MemberblService;
import util.ResultMessage;
import vo.MemberVO;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class MemberInfo_Impl implements MemberInfo {
    private MemberblService memberblService;

    public MemberInfo_Impl() throws RemoteException, MalformedURLException, NotBoundException {
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

    @Override
    public ArrayList<MemberVO> getALL() {
        return null;
    }

    @Override
    public double getStockPur(LocalDateTime floor, LocalDateTime ceil) {
        return 0;
    }

    @Override
    public double getStockRet(LocalDateTime floor, LocalDateTime ceil) {
        return 0;
    }

    @Override
    public double getSalesSell(LocalDateTime floor, LocalDateTime ceil) {
        return 0;
    }

    @Override
    public double getSalesRet(LocalDateTime floor, LocalDateTime ceil) {
        return 0;
    }

}
