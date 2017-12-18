package data.promotiondata;

import dataService.promotiondataService.PromotionDataService;
import mapper.generic.PromotionPOMapper;
import mybatis.MyBatisUtil;
import org.apache.ibatis.binding.BindingException;
import org.apache.ibatis.session.SqlSession;
import po.PromotionPO;
import util.ResultMessage;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class PromotionData<T extends PromotionPO> implements PromotionDataService<T> {
    // because we cannot use T.class
    private Class<? extends PromotionPOMapper<T>> mapperClass = null;

    public PromotionData(Class<? extends PromotionPOMapper<T>> mapperClass) {
        this.mapperClass = mapperClass;
    }

    @Override
    public int getDayId() {
        LocalDateTime today = LocalDateTime.of(LocalDate.now(), LocalTime.MIN);

        int resultID;
        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            PromotionPOMapper<T> mapper = session.getMapper(mapperClass);
            resultID = mapper.getDayId(today);
            // TODO add branch if resultID > 99999?
            session.commit();
        } catch (BindingException e) { // TODO This is aimed to handle the first call in a day. But I'm not sure about its correctness.
            return 0;
        }
        return resultID;
    }

    @Override
    public ResultMessage insert(T promotionPO) {
        promotionPO.setCreateTime(LocalDateTime.now());
        promotionPO.setLastModifiedTime(LocalDateTime.now());

        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            PromotionPOMapper<T> mapper = session.getMapper(mapperClass);
            mapper.insert(promotionPO);
            session.commit();
        }
        return ResultMessage.SUCCESS;
    }

    @Override
    public ResultMessage update(T promotionPO) {
        promotionPO.setLastModifiedTime(LocalDateTime.now());

        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            PromotionPOMapper<T> mapper = session.getMapper(mapperClass);
            mapper.update(promotionPO);
            session.commit();
        }
        return ResultMessage.SUCCESS;
    }

    @Override
    public ResultMessage delete(T promotionPO) {
        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            PromotionPOMapper<T> mapper = session.getMapper(mapperClass);
            mapper.delete(promotionPO);
            session.commit();
        }
        return ResultMessage.SUCCESS;
    }

    @Override
    public List<T> selectInEffect() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime nextDayZero = LocalDateTime.of(now.plusDays(1).toLocalDate(), LocalTime.MIN);

        List<T> resultList;

        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            PromotionPOMapper<T> mapper = session.getMapper(mapperClass);
            resultList = mapper.selectInEffect(now, nextDayZero);
            session.commit();
        }
        return resultList;
    }
}
