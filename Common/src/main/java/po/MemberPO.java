package po;

import util.MemberCategory;
import util.ReceiptCategory;

public class MemberPO {
    private int id;
    private MemberCategory memberCatogory; // 分为进货商和销售商
    private int VIPgrade; // 1~5
    private String memberName;
    private String phoneNumber;
    private String address;
    private String zipCode;
    private String emailAddress;
    private int debtCeiling; // 应收额度
    private int debt;
    private int credit;
    private int defaultOperatorID; // 默认业务员id
    private boolean isDelete=false;

    public MemberPO(int id, MemberCategory memberCatogory, int VIPgrade, String memberName, String phoneNumber, String address, String zipCode, String emailAddress, int debtCeiling, int debt, int credit, int defaultOperatorID) {
        this.id=id;
        this.memberCatogory = memberCatogory;
        this.VIPgrade = VIPgrade;
        this.memberName = memberName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.zipCode = zipCode;
        this.emailAddress = emailAddress;
        this.debtCeiling = debtCeiling;
        this.debt = debt;
        this.credit = credit;
        this.defaultOperatorID = defaultOperatorID;
    }

    public boolean isDelete() {
        return isDelete;
    }

    public void setDelete(boolean delete) {
        isDelete = delete;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public MemberCategory getMemberCatogory() {
        return memberCatogory;
    }

    public void setMemberCatogory(MemberCategory memberCatogory) {
        this.memberCatogory = memberCatogory;
    }

    public int getVIPgrade() {
        return VIPgrade;
    }

    public void setVIPgrade(int VIPgrade) {
        this.VIPgrade = VIPgrade;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public int getDebtCeiling() {
        return debtCeiling;
    }

    public void setDebtCeiling(int debtCeiling) {
        this.debtCeiling = debtCeiling;
    }

    public int getDebt() {
        return debt;
    }

    public void setDebt(int debt) {
        this.debt = debt;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public int getDefaultOperatorID() {
        return defaultOperatorID;
    }

    public void setDefaultOperatorID(int defaultOperatorID) {
        this.defaultOperatorID = defaultOperatorID;
    }
}
