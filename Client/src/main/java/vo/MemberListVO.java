package vo;

import javafx.scene.image.Image;
import util.MemberCategory;
import vo.abstractVO.SelectableVO;

public class MemberListVO extends SelectableVO<MemberListVO>{
    int degree;
    String name;
    Image image;
    int memberId;
    MemberCategory memberCategory;
    String clerkName;
    boolean multiple = true;

    public MemberListVO(int degree, String name, Image image,int memberId, MemberCategory memberCategory,String clerk) {
        this.degree = degree;
        this.name = name;
        this.image = image;
        this.memberId = memberId;
        this.memberCategory = memberCategory;
        this.clerkName = clerk;
    }

    public MemberListVO(int degree, String name, int memberId, MemberCategory memberCategory, String clerkName) {
        this.degree = degree;
        this.name = name;
        this.image = new Image("/default/timg.jpg");
        this.memberId = memberId;
        this.memberCategory = memberCategory;
        this.clerkName = clerkName;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public int getDegree() {
        return degree;
    }

    public void setDegree(int degree) {
        this.degree = degree;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public MemberCategory getMemberCategory() {
        return memberCategory;
    }

    public void setMemberCategory(MemberCategory memberCategory) {
        this.memberCategory = memberCategory;
    }

    public String getClerkName() {
        return clerkName;
    }

    public void setClerkName(String clerkName) {
        this.clerkName = clerkName;
    }

    public boolean isMultiple() {
        return multiple;
    }

    public void setMultiple(boolean multiple) {
        this.multiple = multiple;
    }


    public MemberChooseVO toChooseVO(){
        return new MemberChooseVO(this.name,this.memberId);
    }

}
