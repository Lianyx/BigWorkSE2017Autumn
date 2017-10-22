package po;

import utilitybl.MemberCategory;

/**
 * Created by tiberius on 2017/10/20.
 */
public class MemberPO {
    private String id;
    private MemberCategory memberCatogory; // ��Ϊ�����̺�������
    private int VIPgrade; // 1~5
    private String name;
    private String phoneNumber;
    private String address;
    private String zipCode;
    private String emailAddress;
    private int debtCeiling; // Ӧ�ն��
    private int debt;
    private int credit;
    private String defaultOperatorID; // Ĭ��ҵ��Աid

    public MemberPO(String id, String name) {
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
