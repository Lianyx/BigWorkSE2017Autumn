package data.promotiondata;

import dataService.promotiondataService.MemberPromotiondataService;
import mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import po.MemberPromotionPO;
import util.ResultMessage;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class MemberPromotionData implements MemberPromotiondataService {
    @Override
    public int getID() {
        return 0;
    }

    @Override
    public ResultMessage insert(MemberPromotionPO promotionPO) {
        promotionPO.setCreateTime(LocalDateTime.now());
        promotionPO.setLastModifiedTime(LocalDateTime.now());

        try(SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            MemberPromotionPOMapper mapper = session.getMapper(MemberPromotionPOMapper.class);
            mapper.insert(promotionPO);
            session.commit();
        }
        return ResultMessage.SUCCESS;
    }

    @Override
    public ResultMessage update(MemberPromotionPO promotionPO) {
        promotionPO.setLastModifiedTime(LocalDateTime.now());

        try(SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            MemberPromotionPOMapper mapper = session.getMapper(MemberPromotionPOMapper.class);
            mapper.update(promotionPO);
            session.commit();
        }
        return ResultMessage.SUCCESS;
    }

    @Override
    public ResultMessage delete(MemberPromotionPO promotionPO) {
        try(SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            MemberPromotionPOMapper mapper = session.getMapper(MemberPromotionPOMapper.class);
            mapper.delete(promotionPO);
            session.commit();
        }
        return ResultMessage.SUCCESS;
    }


    @Override
    public List<MemberPromotionPO> selectInEffect() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime nextDayZero = LocalDateTime.of(now.plusDays(1).toLocalDate(), LocalTime.MIN);

        List<MemberPromotionPO> resultList;

        try(SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            MemberPromotionPOMapper mapper = session.getMapper(MemberPromotionPOMapper.class);
            resultList = mapper.selectInEffect(now, nextDayZero);
            session.commit();
        }
        return resultList;
    }



    // for test
    public List<MemberPromotionPO> getAll() {
        List<MemberPromotionPO> resultList;
        try(SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            MemberPromotionPOMapper mapper = session.getMapper(MemberPromotionPOMapper.class);
            resultList = mapper.getAll();
            session.commit();
        }
        return resultList;
    }
}
