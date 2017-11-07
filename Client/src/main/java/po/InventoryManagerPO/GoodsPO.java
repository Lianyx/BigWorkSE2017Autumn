package po.InventoryManagerPO;

/**
 * 商品持久化对象
 * @author 林鹏
 *
 */
public class GoodsPO {
    private static final long serialVersionUID = 1L;
    /**编号*/
    private String ID;
    /** 商品名称 */
    private String name;
    /** 商品型号 */
    private String type;
    /** 商品分类ID */
    private String classifyID;
    /** 商品库存数量 */
    private int inventoryNum;
    /** 商品进价 */
    private double purPrice;
    /** 商品零售价 */
    private double salePrice;
    /** 商品最近进价 */
    private double recentPurPrice;
    /** 商品最近零售价 */
    private double recentSalePrice;
    /** 商品警戒数量 */
    private int alarmNumber;

    public GoodsPO(String ID, String name, String sortID, String type, double purPrice, double salePrice, int alarmNumber) {
        this.ID = ID;
        this.name = name;
        this.type = type;
        this.classifyID = classifyID;
        this.purPrice = purPrice;
        this.salePrice = salePrice;
        this.alarmNumber = alarmNumber;

    }

    public String getID() {
        return ID;
    }

    public void setID(String iD) {
        ID = iD;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getClassifyID() {
        return classifyID;
    }

    public void setClassifyID(String classifyID) {
        this.classifyID = classifyID;
    }

    public int getInventoryNum() {
        return inventoryNum;
    }

    public void setInventoryNum(int inventoryNum) {
        this.inventoryNum = inventoryNum;
    }

    public double getPurPrice() {
        return purPrice;
    }

    public void setPurPrice(double purPrice) {
        this.purPrice = purPrice;
    }

    public double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(double salePrice) {
        this.salePrice = salePrice;
    }

    public double getRecentPurPrice() {
        return recentPurPrice;
    }

    public void setRecentPurPrice(double recentPurPrice) {
        this.recentPurPrice = recentPurPrice;
    }

    public double getRecentSalePrice() {
        return recentSalePrice;
    }

    public void setRecentSalePrice(double recentSalePrice) {
        this.recentSalePrice = recentSalePrice;
    }

    public int getAlarmNumber() {
        return alarmNumber;
    }

    public void setAlarmNumber(int alarmNumber) {
        this.alarmNumber = alarmNumber;
    }
}
