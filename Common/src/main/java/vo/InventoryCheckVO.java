package vo;

import java.util.Set;

public class InventoryCheckVO {
    /** 商品列表 */
    public Set<InventoryCheckItemVO> commodities;
    /** 批号 */
    public String today;
    /** 批次 */
    public String lot;


    public InventoryCheckVO(Set<InventoryCheckItemVO> commodities, String today, String lot) {
        this.commodities = commodities;
        this.today = today;
        this.lot = lot;
    }
}
