package vo;

import util.MemberCategory;

/**
 * Created by tiberius on 2017/10/21.
 */
public class MemberVO {
    private String id;
    private MemberCategory memberCatogory; // 分为进货商和销售商
    private int VIPgrade; // 1~5
    private String name;
    private String phoneNumber;
    private String address;
    private String zipCode;
    private String emailAddress;
    private int debtCeiling; // 应收额度
    private int debt;
    private int credit;
    private String defaultOperatorID; // 默认业务员id

    public MemberVO(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
