package po;

/**
 * 商品持久化对象
 * @author 林鹏
 *
 */
public class GoodsPO {
    private static final long serialVersionUID = 1L;
    /**编号*/
    private String id;
    /** 商品名称 */
    private String goodName;
    /** 商品型号 */
    private String goodType;
    /** 商品分类ID */
    private String classifyId;
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
    public GoodsPO() {
    }

    public GoodsPO(String id, String goodName, String goodType, String classifyId, int inventoryNum, double purPrice,
                   double salePrice, double recentPurPrice, double recentSalePrice, int alarmNumber) {
        this.id = id;
        this.goodName = goodName;
        this.goodType = goodType;
        this.classifyId = classifyId;
        this.inventoryNum = inventoryNum;
        this.purPrice = purPrice;
        this.salePrice = salePrice;
        this.recentPurPrice = recentPurPrice;
        this.recentSalePrice = recentSalePrice;
        this.alarmNumber = alarmNumber;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGoodName() {
        return goodName;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }

    public String getGoodType() {
        return goodType;
    }

    public void setGoodType(String goodType) {
        this.goodType = goodType;
    }

    public String getId() {
        return id;
    }

    public void setID(String id) { id = id;}

    public String getgoodName() {
        return goodName;
    }

    public void setgoodName(String goodName) {
        this.goodName = goodName;
    }

    public String getgoodType() {
        return goodType;
    }

    public void setgoodType(String goodType) {
        this.goodType = goodType;
    }

    public String getClassifyId() {
        return classifyId;
    }

    public void setClassifyId(String classifyId) {
        this.classifyId = classifyId;
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

    @Override
    public String toString() {
        return "GoodsPO{" +
                "Id='" + id + '\'' +
                ", goodName='" + goodName + '\'' +
                ", goodType='" + goodType + '\'' +
                ", classifyId='" + classifyId + '\'' +
                ", inventoryNum=" + inventoryNum +
                ", purPrice=" + purPrice +
                ", salePrice=" + salePrice +
                ", recentPurPrice=" + recentPurPrice +
                ", recentSalePrice=" + recentSalePrice +
                ", alarmNumber=" + alarmNumber +
                '}';
    }
}
