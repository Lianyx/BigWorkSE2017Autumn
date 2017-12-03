package data.checkdata;

import dataService.checkdataService.ReceiptDataService;
import mybatis.MyBatisUtil;
import org.apache.ibatis.binding.BindingException;
import org.apache.ibatis.session.SqlSession;
import po.ReceiptPO;
import util.ReceiptState;
import util.ResultMessage;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

// TODO 这个和promotionData重复太多了…想提一个父类来
public class ReceiptData<T extends ReceiptPO> implements ReceiptDataService<T> {
    private Class<? extends ReceiptPOMapper<T>> mapperClass = null;

    public ReceiptData(Class<? extends ReceiptPOMapper<T>> mapperClass) {
        this.mapperClass = mapperClass;
    }

    @Override
    public int getDayId() {
        LocalDateTime today = LocalDateTime.of(LocalDate.now(), LocalTime.MIN);

        int resultID;
        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            ReceiptPOMapper<T> mapper = session.getMapper(mapperClass);
            resultID = mapper.getDayId(today);
            session.commit();
        } catch (BindingException e) {
            return 0;
        }
        return resultID;
    }

    @Override
    public ResultMessage insert(T promotionPO) {
        promotionPO.setCreateTime(LocalDateTime.now());
        promotionPO.setLastModifiedTime(LocalDateTime.now());

        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            ReceiptPOMapper<T> mapper = session.getMapper(mapperClass);
            mapper.insert(promotionPO);
            session.commit();
        }
        return ResultMessage.SUCCESS;
    }

    @Override
    public ResultMessage update(T promotionPO) {
        promotionPO.setLastModifiedTime(LocalDateTime.now());

        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            ReceiptPOMapper<T> mapper = session.getMapper(mapperClass);
            mapper.update(promotionPO);
            session.commit();
        }
        return ResultMessage.SUCCESS;
    }

    @Override
    public ResultMessage delete(T promotionPO) {
        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            ReceiptPOMapper<T> mapper = session.getMapper(mapperClass);
            mapper.delete(promotionPO);
            session.commit();
        }
        return ResultMessage.SUCCESS;
    }

    @Override
    public List<T> selectBetween(LocalDateTime begin, LocalDateTime end) {
        List<T> resultList;

        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            ReceiptPOMapper<T> mapper = session.getMapper(mapperClass);
            resultList = mapper.selectBetween(begin, end);
            session.commit();
        }
        return resultList;
    }

    @Override
    public List<T> selectPending() {
        List<T> resultList;

        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            ReceiptPOMapper<T> mapper = session.getMapper(mapperClass);
            resultList = mapper.selectByState(ReceiptState.PENDING);
            session.commit();
        }
        return resultList;
    }
}
