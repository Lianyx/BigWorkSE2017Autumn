package businesslogic.memberbl;

import blService.memberblService.MemberblService;
import util.MemberSearchCondition;
import util.ResultMessage;
import vo.MemberListVO;
import vo.MemberVO;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class Memberbl implements MemberblService{
    @Override
    public MemberVO getNew() throws RemoteException {
        return null;
    }

    @Override
    public ResultMessage delete(int id) throws RemoteException {
        return null;
    }

    @Override
    public ResultMessage update(MemberVO MemberVO) throws RemoteException {
        return null;
    }

    @Override
    public ArrayList<MemberListVO> search(MemberSearchCondition memberSearchCondition) throws RemoteException {
        return null;
    }

    @Override
    public MemberVO showDetail(int id) throws RemoteException {
        return null;
    }
}
