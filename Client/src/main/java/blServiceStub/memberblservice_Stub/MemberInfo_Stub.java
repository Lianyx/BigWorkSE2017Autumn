package blServiceStub.memberblservice_Stub;

import blService.blServiceFactory.ServiceFactory_Stub;
import blService.memberblService.MemberInfo;
import blService.memberblService.MemberblService;
import vo.MemberChooseVO;
import vo.MemberListVO;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class MemberInfo_Stub implements MemberInfo {

    MemberblService memberblService = ServiceFactory_Stub.getService(MemberblService.class.getName());
    @Override
    public Set<String> getAll() {
        return memberblService.getAll().stream().map(t->t.toChooseVO().toString()).collect(Collectors.toSet());
    }
}
