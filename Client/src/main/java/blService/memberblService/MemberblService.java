package blService.memberblService;

import util.ResultMessage;
import vo.*;

import java.util.ArrayList;
import java.util.Set;

public interface MemberblService {
    public ResultMessage add(MemberVO MemberVO);
    public ResultMessage delete(int id);
    public ResultMessage delete(ArrayList<Integer> list);

    public ResultMessage update(MemberVO MemberVO);
    public Set<MemberListVO> search(MemberSearchVO memberSearchVO);
    public Set<MemberListVO> search(String keyword);
    public MemberVO showDetail(int id);
    public Set<MemberListVO> getAll();


}
