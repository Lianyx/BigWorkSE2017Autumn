package vo.inventoryVO;

import businesslogic.goodsbl.GoodsPOVOChanger;
import po.GoodsClassificationPO;
import po.GoodsPO;

import java.util.ArrayList;
import java.util.List;

public class MyGoodsClassificationVO {
    private String id;
    private String name;
    private MyGoodsClassificationVO father;
    private List<MyGoodsClassificationVO> children; // 如果是第一次建的空类，两个都是null的？
    private List<GoodsVO> goods;
    // TODO 其实我想能不能通过面向对象把这两个解决掉的。只有一个…但是乍一想似乎还是很多逻辑判断。
    // TODO 另外，是否应该所有的array和list都宁愿是空的，也不要是null。这是个好习惯？


    public MyGoodsClassificationVO() {
    }

    // 这是一个做了一半的方法，不太确定是否应该这么写，因为剩下的转换职则交给MyGoodsClassificationbl了
    public MyGoodsClassificationVO(GoodsClassificationPO goodsClassificationPO) {
        id = goodsClassificationPO.getId();
        name = goodsClassificationPO.getName();
        children = new ArrayList<>();
        goods = new ArrayList<>();
    }

    public GoodsClassificationPO toPO() {
        GoodsClassificationPO result = new GoodsClassificationPO();

        result.setId(id);
        result.set_name(name);
        result.setFatherId(father == null ? null : father.getId());
        result.setGoodsId(goods == null || goods.isEmpty() ? null : goods.stream().map(GoodsVO::getId).toArray(String[]::new));
        result.setChildrenId(children == null || children.isEmpty() ? null : children.stream().map(MyGoodsClassificationVO::getId).toArray(String[]::new));

        return result;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MyGoodsClassificationVO getFather() {
        return father;
    }

    public void setFather(MyGoodsClassificationVO father) {
        this.father = father;
    }

    public List<MyGoodsClassificationVO> getChildren() {
        return children;
    }

    public void setChildren(List<MyGoodsClassificationVO> children) {
        this.children = children;
    }

    public List<GoodsVO> getGoods() {
        return goods;
    }

    public void setGoods(List<GoodsVO> goods) {
        this.goods = goods;
    }
}
