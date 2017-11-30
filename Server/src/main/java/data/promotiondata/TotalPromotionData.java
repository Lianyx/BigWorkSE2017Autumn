package data.promotiondata;

import dataService.promotiondataService.PromotiondataService;
import mybatis.MyBatisUtil;
import org.apache.ibatis.binding.BindingException;
import org.apache.ibatis.session.SqlSession;
import po.TotalPromotionPO;
import util.ResultMessage;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class TotalPromotionData implements PromotiondataService<TotalPromotionPO>{
    @Override
    public int getDayId() {
        LocalDateTime today = LocalDateTime.of(LocalDate.now(), LocalTime.MIN);

        int resultID;
        try(SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            TotalPromotionPOMapper mapper = session.getMapper(TotalPromotionPOMapper.class);
            resultID = mapper.getDayId(today);
            // TODO add branch if resultID > 99999?
            session.commit();
        } catch (BindingException e) { // TODO This is aimed to handle the first call in a day. But I'm not sure about its correctness.
            return 0;
        }
        return resultID;
    }

    @Override
    public ResultMessage insert(TotalPromotionPO promotionPO) {
        promotionPO.setCreateTime(LocalDateTime.now());
        promotionPO.setLastModifiedTime(LocalDateTime.now());

        try(SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            TotalPromotionPOMapper mapper = session.getMapper(TotalPromotionPOMapper.class);
            mapper.insert(promotionPO);
            session.commit();
        }
        return ResultMessage.SUCCESS;
    }

    @Override
    public ResultMessage update(TotalPromotionPO promotionPO) {
        promotionPO.setLastModifiedTime(LocalDateTime.now());

        try(SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            TotalPromotionPOMapper mapper = session.getMapper(TotalPromotionPOMapper.class);
            mapper.update(promotionPO);
            session.commit();
        }
        return ResultMessage.SUCCESS;
    }

    @Override
    public ResultMessage delete(TotalPromotionPO promotionPO) {
        try(SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            TotalPromotionPOMapper mapper = session.getMapper(TotalPromotionPOMapper.class);
            mapper.delete(promotionPO);
            session.commit();
        }
        return ResultMessage.SUCCESS;
    }

    @Override
    public List<TotalPromotionPO> selectInEffect() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime nextDayZero = LocalDateTime.of(now.plusDays(1).toLocalDate(), LocalTime.MIN);

        List<TotalPromotionPO> resultList;

        try(SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            TotalPromotionPOMapper mapper = session.getMapper(TotalPromotionPOMapper.class);
            resultList = mapper.selectInEffect(now, nextDayZero);
            session.commit();
        }
        return resultList;
    }
}
