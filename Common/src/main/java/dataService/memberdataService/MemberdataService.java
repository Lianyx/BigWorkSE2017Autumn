package dataService.memberdataService;

import po.MemberPO;
import util.ResultMessage;

import java.util.ArrayList;


public interface MemberdataService {
    public ResultMessage insert(MemberPO MemberPO);
    public ResultMessage delete(int id);
    public ResultMessage delete(ArrayList<Integer> list);
    public ResultMessage update(MemberPO MemberPO);
    public ArrayList<MemberPO> select(String keyword);
    public MemberPO showDetail(int id);
    public ArrayList<MemberPO> getAll();



}