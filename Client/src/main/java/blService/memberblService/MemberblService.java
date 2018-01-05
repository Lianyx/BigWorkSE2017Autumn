package blService.memberblService;

import util.ResultMessage;
import vo.*;

import java.util.ArrayList;
import java.util.Set;

public interface MemberblService {
    public int getId();
    public ResultMessage add(MemberVO MemberVO);
    public ResultMessage delete(int id);
    public ResultMessage update(MemberVO MemberVO);
    public Set<MemberListVO> search(MemberSearchVO memberSearchVO);
    public MemberVO showDetail(int id);
    public Set<MemberListVO> getAll();


}
