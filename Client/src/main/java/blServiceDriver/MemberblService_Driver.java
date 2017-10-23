package blServiceDriver;

import blServiceStub.MemberblService_Stub;
import bl.util.SearchConditions;
import blService.memberblService.MemberblService;
import vo.MemberVO;

/**
 * Created by tiberius on 2017/10/21.
 */
public class MemberblService_Driver {
    public static void main(String[] args) {
        MemberblService memberblService = new MemberblService_Stub();
        System.out.println(memberblService.delete("000007"));
        System.out.println(memberblService.insert(new MemberVO("00001", "王小明")));
        System.out.println(memberblService.search(new SearchConditions()));
        System.out.println(memberblService.update(new MemberVO("00001", "王小明")));
    }
}
