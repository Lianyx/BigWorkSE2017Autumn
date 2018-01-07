package vo;

import javafx.scene.image.Image;
import util.MemberCategory;

public class MemberVO {
    int memberId;
    MemberCategory memberCategory;
    Image image;
    int degree;
    String name;
    String phone;
    String address;
    String email;
    String zipCode;
    double receiableAmount;
    double give;
    double get;
    String clerkName;
    String comment;

    public MemberVO(int memberId, MemberCategory memberCategory,Image image, int degree, String name, String phone, String address, String email, String zipCode,double receiableAmount, double give, double get, String clerkName,String comment) {
        this.memberId = memberId;
        this.memberCategory = memberCategory;
        this.degree = degree;
        this.image = image;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.email = email;
        this.zipCode = zipCode;
        this.receiableAmount = receiableAmount;
        this.give = give;
        this.get = get;
        this.clerkName = clerkName;
        this.comment = comment;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getReceiableAmount() {
        return receiableAmount;
    }

    public void setReceiableAmount(double receiableAmount) {
        this.receiableAmount = receiableAmount;
    }

    public double getGive() {
        return give;
    }

    public void setGive(double give) {
        this.give = give;
    }

    public double getGet() {
        return get;
    }

    public void setGet(double get) {
        this.get = get;
    }

    public String getClerkName() {
        return clerkName;
    }

    public void setClerkName(String clerkName) {
        this.clerkName = clerkName;
    }
}
