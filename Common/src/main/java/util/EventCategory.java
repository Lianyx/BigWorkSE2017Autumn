package util;

import java.util.HashMap;

public enum EventCategory {
    CreateSalesReceipt,HandInReceipt;

    public static HashMap<EventCategory,String> map = new HashMap<>();

    static{
        map.put(EventCategory.CreateSalesReceipt,"新建了销售进货单据");
        map.put(EventCategory.HandInReceipt,"提交了单据");
    }


    private final int value;

    private EventCategory() {
        this.value = ordinal();
    }
}
