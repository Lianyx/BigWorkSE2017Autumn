package blServiceStub.memberblservice_Stub;

import blService.memberblService.MemberblService;
import javafx.scene.image.Image;
import util.MemberCategory;
import util.ResultMessage;
import vo.MemberListVO;
import vo.MemberSearchVO;
import vo.MemberVO;

import java.util.HashSet;
import java.util.Set;


public class MemberblService_Stub implements MemberblService {


    HashSet<MemberListVO> set = new HashSet<>();

    int i=0;


    public MemberblService_Stub(){
        set.add(new MemberListVO(4,"sabi",i++, MemberCategory.SUPPLIER,"SABI"));
        set.add(new MemberListVO(4,"sabi",i++, MemberCategory.SUPPLIER,"SABI"));
        set.add(new MemberListVO(4,"sabi",i++, MemberCategory.SUPPLIER,"SABI"));
        set.add(new MemberListVO(4,"sabi",i++, MemberCategory.SELLER,"SABI"));
        set.add(new MemberListVO(4,"sabi",i++, MemberCategory.SELLER,"SABI"));
        set.add(new MemberListVO(3,"sabi",i++, MemberCategory.SUPPLIER,"SABI"));
        set.add(new MemberListVO(2,"sabi",i++, MemberCategory.SUPPLIER,"SABI"));
        set.add(new MemberListVO(1,"sabi",i++, MemberCategory.SUPPLIER,"SABI"));
        set.add(new MemberListVO(5,"sabi",i++, MemberCategory.SUPPLIER,"SABI"));
        set.add(new MemberListVO(4,"sabi",i++, MemberCategory.SUPPLIER,"SABI"));
        set.add(new MemberListVO(4,"sabi",i++, MemberCategory.SUPPLIER,"SABI"));
        set.add(new MemberListVO(4,"sabi",i++, MemberCategory.SUPPLIER,"SABI"));
        set.add(new MemberListVO(4,"sabi",i++, MemberCategory.SUPPLIER,"SABI"));
        set.add(new MemberListVO(4,"sabi",i++, MemberCategory.SUPPLIER,"SABI"));


    }


    @Override
    public ResultMessage add(MemberVO MemberVO) {
        set.add(new MemberListVO(MemberVO.getDegree(),MemberVO.getName(),MemberVO.getImage(),MemberVO.getMemberId(),MemberVO.getMemberCategory(),MemberVO.getClerkName()));
        return ResultMessage.SUCCESS;
    }

    @Override
    public ResultMessage delete(int id) {
        MemberListVO s = null;
        for(MemberListVO stockReceiptListVO:set){
            if(stockReceiptListVO.getMemberId()==id){
                s = stockReceiptListVO;
            }
        }
        if(s!=null){
            set.remove(s);
        }
        return ResultMessage.SUCCESS;
    }

    @Override
    public ResultMessage update(MemberVO MemberVO) {
        delete(MemberVO.getMemberId());
        add(MemberVO);
        return null;
    }

    @Override
    public Set<MemberListVO> search(MemberSearchVO userSearchVO) {
        HashSet<MemberListVO> temp = new HashSet<>();
        for(MemberListVO s:set){
            for(MemberCategory receiptState:userSearchVO.getMemberCategories()){
                if(s.getMemberCategory()==receiptState)
                    temp.add(s);
            }

        }
        return temp;
    }



    @Override
    public MemberVO showDetail(int id) {
        return new MemberVO(id, MemberCategory.SELLER,new Image("/default/timg.jpg"),1,"sabe","123123","123123123123","gzyz12306@163.com","12306",300,100,200,"admin","sabi");
    }

    @Override
    public Set<MemberListVO> getAll() {
        return set;
    }

    @Override
    public int getId() {
        i++;
        return i;
    }

}
