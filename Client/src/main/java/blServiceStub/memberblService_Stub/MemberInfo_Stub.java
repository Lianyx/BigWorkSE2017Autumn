package blServiceStub.memberblService_Stub;

import blService.memberblService.MemberInfo;

import java.time.LocalDateTime;

public class MemberInfo_Stub implements MemberInfo {

    @Override
    public String getName(int memberId) {
        return "lyc"+LocalDateTime.now().getSecond();
    }
}
