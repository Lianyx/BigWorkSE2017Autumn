package dataServiceStub;

import dataService.MemberDataService;
import dataServiceDriver.MemberDataService_Stub;
import po.MemberPO;

/**
 * Created by tiberius on 2017/10/22.
 */
public class MemberDataService_Driver {
    public static void main(String[] args) {
        MemberDataService memberDataService = new MemberDataService_Stub();
        System.out.println(memberDataService.delete("00001"));
        System.out.println(memberDataService.insert(new MemberPO("00001", "王小明")));
        System.out.println(memberDataService.update(new MemberPO("00001", "王小明")));
        memberDataService.search(null).forEach(System.out::println);
    }
}
