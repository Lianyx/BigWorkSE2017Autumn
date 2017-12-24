package data.promotiondata;

import dataService.promotiondataService.PromotionDataService;
import mapper.generic.PromotionPOMapper;
import mybatis.MyBatisUtil;
import org.apache.ibatis.binding.BindingException;
import org.apache.ibatis.session.SqlSession;
import po.promotionPO.PromotionPO;
import util.ResultMessage;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class PromotionData<T extends PromotionPO> extends UnicastRemoteObject implements PromotionDataService<T> {
    // because we cannot use T.class
    private Class<? extends PromotionPOMapper<T>> mapperClass = null;

    public PromotionData(Class<? extends PromotionPOMapper<T>> mapperClass) throws RemoteException {
        this.mapperClass = mapperClass;
    }

    @Override
    public int getDayId() throws RemoteException {
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
    public ResultMessage insert(T promotionPO) throws RemoteException {
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
    public ResultMessage update(T promotionPO) throws RemoteException {
        promotionPO.setLastModifiedTime(LocalDateTime.now());

        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            PromotionPOMapper<T> mapper = session.getMapper(mapperClass);
            mapper.update(promotionPO);
            session.commit();
        }
        return ResultMessage.SUCCESS;
    }

    @Override
    public ResultMessage delete(T promotionPO) throws RemoteException {
        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            PromotionPOMapper<T> mapper = session.getMapper(mapperClass);
            mapper.delete(promotionPO);
            session.commit();
        }
        return ResultMessage.SUCCESS;
    }

    @Override
    public ArrayList<T> selectInEffect() throws RemoteException {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime nextDayZero = LocalDateTime.of(now.plusDays(1).toLocalDate(), LocalTime.MIN);

        ArrayList<T> resultList;

        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            PromotionPOMapper<T> mapper = session.getMapper(mapperClass);
            resultList = mapper.selectInEffect(now, nextDayZero);
            session.commit();
        }
        return resultList;
    }
}
