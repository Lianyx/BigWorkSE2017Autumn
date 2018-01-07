package util;

import java.util.HashMap;

/**
 * Created by tiberius on 2017/10/20.
 */
public enum MemberCategory {
    SELLER,SUPPLIER;
    public static HashMap<String,MemberCategory> map = new HashMap<>();
    public static HashMap<String,String> color = new HashMap<>();

    static{
        for(MemberCategory memberCategory:values()){
            map.put(memberCategory.name(),memberCategory);
        }
        for(MemberCategory memberCategory:values()){
            if(memberCategory==MemberCategory.SUPPLIER){
                color.put(memberCategory.name(),"-fx-text-fill: white;-fx-background-radius: 10; -fx-background-color:green");
            }else if(memberCategory==MemberCategory.SELLER){
                color.put(memberCategory.name(),"-fx-text-fill: white;-fx-background-radius: 10; -fx-background-color:purple");
            }
        }
    }
}
